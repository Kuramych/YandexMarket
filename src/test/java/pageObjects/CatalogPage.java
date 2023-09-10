package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CatalogPage {

    @Step("Выбран раздел с животными '{0}'")
    public AnimalPage selectAnimal(String animal) {
        $(By.xpath("//div[contains(text(),'" + animal + "')]")).click();
        return new AnimalPage();
    }

}
