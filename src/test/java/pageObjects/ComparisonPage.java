package pageObjects;


import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class ComparisonPage {

    String deleteSelectedProductLocator = "._1KpjX._2bqiO";
    String deleteAllProductsLocator = "._1KpjX._1KU3s";
    String allProductsLocator = ".zvRJM a";
    String allPricesLocator = "span[data-auto='mainPrice']";

    @Step("Получен список товаров с страницы сравнения.")
    public List<String> getListFromComparisonPage() {
        List<String> productNames  = $$(allProductsLocator).texts();
        return productNames;
    }

    @Step("Получена общая цена товаров со страницы сравнения.")
    public int getCommonPriceFromComparisonPage() {
        int commonPrice = 0;
        List<String> productsPrices  = $$(allPricesLocator).texts();
        for (String stringPrice : productsPrices) {
            String[] priceParts = stringPrice.split(" ");
            String priceValue = priceParts.length >= 2 ? priceParts[1].replaceAll("[^0-9]", "") :
                    priceParts[0].replaceAll("[^0-9]", "");
            int price = Integer.parseInt(priceValue);
            commonPrice += price;
        }
        return commonPrice;
    }

    @Step("Удален продукт бренда 'Whiskas' с страницы сравнения.")
    public void deleteSelectedProduct(int productId) {
        sleep(3000);
        $$(deleteSelectedProductLocator).get(productId).hover().click();
        //$$(deleteSelectedProductLocator).get(productId).click();
    }

    @Step("Удалены все продукты с страницы сравнения.")
    public void deleteAllProducts() {
        sleep(3000);
        $(deleteAllProductsLocator).hover().click();
    }

}
