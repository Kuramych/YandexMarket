package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.Thread.sleep;

public class AnimalPage {

    String startPriceLocator = "//input[contains(@class, '_3qxDp _37_h4')]";
    String endPriceLocator = "//input[contains(@class, '_3qxDp _1R_cW')]";
    String selectProductLocator = "//div[@class='_1E7GZ']";

    @Step("Получен {0} в списке товар.")
    public ProductPage selectProduct(int productIndex) throws InterruptedException {
        ElementsCollection products = $$(By.xpath(selectProductLocator));
        products.get(productIndex).click();
        sleep(3000);
        return new ProductPage();
    }

    public void showAllBrands () throws InterruptedException {
        sleep(5000);
        $(By.xpath("//span[contains(text(),'Показать всё')]")).click();
    }

    @Step("Выбран бренд продукции '{0}'.")
    public void selectBrandName(String brandName) throws InterruptedException {
        sleep(3000);
        $(By.xpath("//span[@class='_1ZDAA' and text()='" + brandName + "']")).shouldBe(Condition.exist).click();
        sleep(1000);
    }

    @Step("Выбран способ доставки '{0}'.")
    public void selectDeliveryMethod(String deliveryType) throws InterruptedException {
        $(By.xpath("//span[contains(text(),'"+deliveryType+"')]")).click();
        sleep(3000);
    }

    @Step("Установлен ценовой промежуток от {0} до {1}.")
    public void setPriceGap(String startPriceValue, String endPriceValue) {
        ElementsCollection startPrice = $$(By.xpath(startPriceLocator));
        startPrice.get(0).click();
        startPrice.get(0).sendKeys(startPriceValue);
        ElementsCollection endPrice = $$(By.xpath(endPriceLocator));
        endPrice.get(0).click();
        endPrice.get(0).sendKeys(endPriceValue);
    }

    @Step("Выбран тип продукта '{0}'")
    public void selectProductCategory(String productType) {
        $(By.xpath("//span[contains(text(),'"+productType+"')]")).click();
    }


    public void setFiltersToAnimalProducts() throws InterruptedException {
        selectProductCategory("Лакомства для кошек");
        setPriceGap("50", "150");
        selectDeliveryMethod("Курьером");
    }

}
