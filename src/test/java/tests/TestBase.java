package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import property.PropertiesHelper;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    PropertiesHelper propertiesHelper = PropertiesHelper.getInstance();

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        Configuration.browser = "edge";
        Configuration.baseUrl = propertiesHelper.getProperty("marketUrl");
        open("");
        Configuration.holdBrowserOpen = true;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeWindow();
    }
}
