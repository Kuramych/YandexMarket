package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.*;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.Thread.sleep;

public class TestCatalog extends TestBase {

    HomePage homePage = new HomePage();

    @Test
    public void testZooCatalog() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Set<String> productsToCompare = new HashSet<String>();

        //Прошел в каталог Зоотовары в раздел Кошки
        MenuPage menuPage = homePage.goToNavigationMenu();
        CatalogPage catalogPage = menuPage.goToCatalog("Зоотовары");
        AnimalPage animalPage = catalogPage.selectAnimal("Кошки");

        //Установил фильтры для продукции, выбрал бренд Whiskas, выбрал первый продукт
        animalPage.setFiltersToAnimalProducts();
        animalPage.showAllBrands();
        animalPage.selectBrandName("Whiskas");
        ProductPage productPage = animalPage.selectProduct(0);
        switchTo().window(1);

        //Получил имя первого продукта Whiskas, добавил его в сравнение
        String FirstProductName = productPage.getProductName();
        productsToCompare.add(FirstProductName);
        productPage.setProductComparison();
        closeWindow();
        switchTo().window(0);

        //Убрал галочку с Whiskas, поставил на Мнямс, выбрал второй продукт Мнямса
        animalPage.selectBrandName("Whiskas");
        animalPage.selectBrandName("Мнямс");
        animalPage.selectProduct(1);
        switchTo().window(1);

        //Получил имя второго продукта Мнямс, добавил его в сравнение, инициализировал страницу сравнений
        String SecondProductName = productPage.getProductName();
        productsToCompare.add(SecondProductName);
        productPage.setProductComparison();
        ComparisonPage comparisonPage = productPage.compareSeveralProducts();

        //Проверка на соответствие выбранных продуктов
        List<String> productsFromComparisonPage= comparisonPage.getListFromComparisonPage();
        Set<String> modifyProductsFromComparisonPage = new HashSet<>(productsFromComparisonPage);
        softAssert.assertEquals(modifyProductsFromComparisonPage, productsToCompare,
                "Имена товаров на странице сравнения не соответствуют заданным");

        //Проверка на то, что сумма товаров меньше 300 рублей
        int commonProductsPrice = comparisonPage.getCommonPriceFromComparisonPage();
        softAssert.assertTrue(commonProductsPrice < 300);

        //Удалил продукс Whiskas, убедился, что все прошло успешно
        comparisonPage.deleteSelectedProduct(1);
        List<String> productsFromComparisonPageAfterDeletionItem= comparisonPage.getListFromComparisonPage();
        boolean checkFirstDeletion = productsFromComparisonPageAfterDeletionItem.contains("Whiskas");
        softAssert.assertFalse(checkFirstDeletion, "Список не содержит компанию Whiskas, удаление провалилось.");

        //Удалил оставшиеся товары, убедился, что все прошло успешно
        comparisonPage.deleteAllProducts();
        List<String> productsFromComparisonPageAfterDeletionItems= comparisonPage.getListFromComparisonPage();
        closeWindow();
        boolean checkFullDeletion = productsFromComparisonPageAfterDeletionItems.contains("Whiskas") &&
                productsFromComparisonPageAfterDeletionItem.contains("Мнямс");
        softAssert.assertFalse(checkFullDeletion, "Список содержит компанию Whiskas и Мнямс, полное удаление провалилось.");
        switchTo().window(0);
    }
}
