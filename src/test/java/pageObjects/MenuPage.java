package pageObjects;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.Thread.sleep;


public class MenuPage {

    @Step("Открыт каталог '{0}'.")
    public CatalogPage goToCatalog(String catalogName) {
        $(By.xpath("//span[text() = '"+catalogName+"']")).click();
        return new CatalogPage();
    }
}
