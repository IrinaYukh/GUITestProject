package gui.driver;

import config.JsonConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    static WebDriver driver = null;

    public static WebDriver getDriver(String browser)
    {
        String path = System.getProperty("user.dir");

        // Getting values from config.properties file
//        int impWait = Integer.parseInt(PropertiesFile.getProperties("impWait"));

        //Getting values from config.json file
        int impWait = Integer.parseInt((JsonConfig.getValue("gui","impWait")));

        if (browser.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", path + "/src/webdrivers/chromedriver");
            if (driver == null)
            {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(impWait, TimeUnit.MICROSECONDS);
//                driver.get("https://www.google.com");
            }
        }
        else if (browser.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver",path + "/src/webdrivers/geckodriver");
            if (driver == null)
            {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(impWait, TimeUnit.MICROSECONDS);
//                driver.get("https://www.google.com");
            }
        }
        else{
            System.out.println("Error! Wrong browser name! Choose or chrome or firefox browser.");
        }
        return driver;
    }

    public static void closeDriver()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }

}
