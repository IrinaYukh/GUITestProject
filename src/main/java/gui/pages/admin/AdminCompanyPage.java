package gui.pages.admin;

import gui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AdminCompanyPage extends BasePage
{
    public AdminCompanyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    Actions action = new Actions(driver);

    @FindBy(xpath = "//h1[contains(text(),'Company Structure')]")
    WebElement companyManageTitle;

    @FindBy(xpath = "//button[@class='btn btn-small btn-primary']")
    WebElement button_AddNew;

    @FindBy(xpath = "//label[@class='col-sm-3 control-label']")
    WebElement newCompanyForm;

    @FindBy(xpath = "//input[@id='title']")
    WebElement input_companyName;

    @FindBy(xpath = "//textarea[@id='description']")
    WebElement input_companyDetails;

    @FindBy(xpath = "//textarea[@id='address']")
    WebElement input_companyAddress;

    @FindBy(xpath = "//span[@id='select2-chosen-2']")
    WebElement countryField;

    @FindBy(xpath = "//input[@id='s2id_autogen2_search']")
    WebElement input_country;

    @FindBy(xpath = "//span[@id='select2-chosen-3']")
    WebElement timeZoneField;

    @FindBy(xpath = "//input[@id='s2id_autogen3_search']")
    WebElement input_timeZone;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement button_Save;

    @FindBy(xpath = "//h3[@id='deleteModelLabel']")
    WebElement deleteConfirmMessage;

    @FindBy(xpath = "//button[contains(text(),'Delete')]")
    WebElement button_Delete;



    public AdminCompanyPage isOnCompanyManagePage()
    {
        waitUntilElementIsloaded(driver,companyManageTitle,10);
        isElementPresent(companyManageTitle);
        return this;
    }

    public boolean isOnCompanyManagepage()
    {
        return isElementPresent(companyManageTitle);
    }

    public AdminCompanyPage isOnAddNewCompanyForm()
    {
        waitUntilElementIsloaded(driver,newCompanyForm,10);
        isElementPresent(newCompanyForm);
        return this;
    }

    public AdminCompanyPage clickAddNewButton()
    {
        waitUntilElementIsClickable(driver,button_AddNew,20);
        button_AddNew.click();
        return this;
    }

    // ---------- Fill new company Form ---------------------------------
    public AdminCompanyPage inputCompanyName(String name)
    {
        action.moveToElement(input_companyName).build().perform();
        type(input_companyName,name);
        return this;
    }

    public AdminCompanyPage inputCompanyDetails()
    {
        action.moveToElement(input_companyDetails).build().perform();
        type(input_companyDetails,"Test Company");
        return this;
    }

    public AdminCompanyPage inputCompanyAddress(String address)
    {
        action.moveToElement(input_companyAddress).build().perform();
        type(input_companyAddress,address);
        return this;
    }

    public AdminCompanyPage selectCompanyType(String value)
    {
        Select companyType = new Select(driver.findElement(By.id("type")));
        companyType.selectByValue(value);
        return this;
    }

    public AdminCompanyPage inputCountry(String country)  {
        action.moveToElement(countryField).click().build().perform();
        List<WebElement> countries = driver.findElements(By.xpath("//div[@class='select2-result-label']"));
        try {
            Thread.sleep(500);
            for (WebElement e: countries)
            {
                String tmp = e.getText();
                if(tmp.equals(country))
                {
                    e.click();
                    return this;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public AdminCompanyPage inputTimeZone(String zone)
    {
        waitUntilElementIsVisible(driver,timeZoneField,10);
        action.moveToElement(timeZoneField).build().perform();
        timeZoneField.click();

        List<WebElement> timeZones = driver.findElements(By.xpath("//div[@class='select2-result-label']"));
        try {
            Thread.sleep(500);
            for (WebElement e: timeZones)
            {
                String tmp = e.getText();
                if(tmp.equals(zone))
                {
                    e.click();
                    return this;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this;
    }

    public AdminCompanyPage pressSaveButton()
    {
        action.moveToElement(button_Save).click().build().perform();
        return this;
    }

    public boolean isCompanyExist(String companyName)
    {
        return isElementPresent(By.xpath("//td[contains(text(),companyName)]"));
    }

    public AdminCompanyPage deleteCompany(String companyName)
    {
        int count = 0;
        if(isCompanyExist(companyName))
        {
            List<WebElement> names = driver.findElements(By.xpath("//tr//td[1]"));
            try {
                Thread.sleep(500);
                for (WebElement e: names)
                {
                    Thread.sleep(500);
                    String tmp = e.getText();
                    count ++;
                    if(tmp.equals(companyName))
                    {
                        WebElement companyDelete = driver.findElement(By.xpath("//tr["+count+"]//td[7]//div[1]//img[2]"));
                        companyDelete.click();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Can't identify Company into the Companies Table");
        }
        return this;
    }

    public AdminCompanyPage isOnDeleteAlertMessage()
    {
        isElementPresent(deleteConfirmMessage);
        return this;
    }

    public AdminCompanyPage clickDeleteButton()
    {
        waitUntilElementIsClickable(driver,button_Delete,10);
        button_Delete.click();
        waitUntilElementIsloaded(driver,companyManageTitle,50);
        return this;
    }

    public boolean isCompanyCreated(String companyName)
    {
        List<WebElement>rows = driver.findElements(By.xpath("//table[@id='grid']//tbody//tr"));
        for (WebElement row:rows)
        {
            List<WebElement>cells = row.findElements(By.xpath("//td"));
            String name = cells.get(0).getText();
            System.out.println(name);
            if(cells.get(0).getText().equals(companyName))
            {
                return true;
            }
        }
        return false;
    }

}
