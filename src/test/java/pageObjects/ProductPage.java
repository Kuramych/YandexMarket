package pageObjects;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.Thread.sleep;

public class ProductPage {

    String productNameLocator = "//div[@data-baobab-name='spec']//h1[@data-additional-zone='title']";
    String productComparisonLocator = "//div[@class='_2et7a _2eWFm i-rtO d2YiJ']";
    String setProductsComparison = "//div[@class='_1cFTt']";


    @Step("Продукт добавлен в сравнение.")
    public void setProductComparison() {
        $(By.xpath(productComparisonLocator)).shouldBe(Condition.visible).click();
    }

    @Step("Получено имя выбранного продукта.")
    public String getProductName() throws InterruptedException {
        sleep(3000);
        String productName = $(By.xpath(productNameLocator)).getText();
        return productName;
    }

    @Step("Переход в раздел сравнения выбранных товаров.")
    public ComparisonPage compareSeveralProducts() throws InterruptedException {
        sleep(2000);
        $(By.xpath(setProductsComparison)).click();
        return new ComparisonPage();
    }


}
