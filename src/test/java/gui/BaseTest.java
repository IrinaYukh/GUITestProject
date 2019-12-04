package gui;

import config.JsonConfig;
import gui.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    static WebDriver driver;

    public WebDriver getWebDriver()
    {
//        String browser = PropertiesFile.getProperties("browser");
        String browser = JsonConfig.getValue("gui","browser");
        driver = Driver.getDriver(browser);
        return driver;
    }

    @BeforeTest(alwaysRun = true)
    public void setUp()
    {
//        String url = PropertiesFile.getProperties("landingPage");
        String url = JsonConfig.getValue("gui","landingPage");
        getWebDriver();
        driver.get(url);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown()
    {
        Driver.closeDriver();
    }
}
