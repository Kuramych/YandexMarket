package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    @Step("Меню навигации открыто.")
    public MenuPage goToNavigationMenu() {
        $(By.id("hamburger")).click();
        return new MenuPage();
    }
}
