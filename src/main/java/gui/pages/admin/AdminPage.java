package gui.pages.admin;

import gui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends BasePage {

    public AdminPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    Actions action = new Actions(driver);


    @FindBy(xpath = "//font[contains(text(),'administrator')]")
    WebElement admin;

    @FindBy(xpath = "//a[contains(text(),'IceHrm Employee')]")
    WebElement adminName;

    @FindBy(xpath = "//a[@class='dropdown-toggle']//span//span")
    WebElement flagIcon;

    @FindBy(xpath = "//ul[@class='dropdown-menu language-list']//span[@class='flag-icon flag-icon-gb']")
    WebElement englishFlag;

    @FindBy(xpath = "//ul[contains(@class,'dropdown-menu language-list')]//span[contains(@class,'flag-icon flag-icon-de')]")
    WebElement germanFlag;

    @FindBy(xpath = "//span[contains(@class,'flag-icon flag-icon-fr')]")
    WebElement franceFlag;

    @FindBy(xpath = "//h1[contains(text(),'Übersicht')]")
    WebElement dashboardTitle_German;

    @FindBy(xpath = "//h1[contains(text(),'Tableau de bord')]")
    WebElement dashboardTitle_France;

    @FindBy(xpath = "//h1[contains(text(),'Dashboard')]")
    WebElement dashboardTitle_English;

    @FindBy(xpath = "//li[@class='treeview active']//span[contains(text(),'Admin')]")
    WebElement menuTitle;

    @FindBy(xpath = "//a[@class='dropdown-toggle']//span[contains(text(),'IceHrm')]")
    WebElement profileMenu;

    @FindBy(xpath = "//div[@class='pull-right']/a[@href='http://icehrm-open.gamonoid.com/logout.php']")
    WebElement button_logout;

    @FindBy(xpath = "//a[@class='logo']")
    WebElement homeScreen;

    @FindBy(xpath = "//a[@id='companyLink']")
    WebElement manageCompanyMenu;

    public AdminPage isOnPage()
    {
        isElementPresent(adminName);
        return this ;
    }

    // HOME element. Using for return from different screens to Admin Dashboard Screen
    public AdminPage returnOnAdminDashboardScreen()
    {
        action.moveToElement(homeScreen).click().build().perform();
        return this;
    }

    public AdminPage changeLanguage(String language)
    {
        if (language.equals("English")) {
            action.moveToElement(flagIcon).click().build().perform();
            action.moveToElement(englishFlag).build().perform();
            englishFlag.click();
        }
        else if (language.equals("France")) {
            action.moveToElement(flagIcon).click().build().perform();
            action.moveToElement(franceFlag).build().perform();
            franceFlag.click();
        }
        else if (language.equals("German")) {
            action.moveToElement(flagIcon).click().build().perform();
            action.moveToElement(germanFlag).build().perform();
            germanFlag.click();
        }
        return this;
    }

    public boolean isAdminNameCorrect(String text)
    {
        return adminName.getText().equals(text);
    }

    public boolean german_isDashboardTitleCorrect(String text)
    {
        waitUntilElementIsVisible(driver,dashboardTitle_German,20);
        return dashboardTitle_German.getText().equals(text);
    }

    public boolean france_isDashboardTitleCorrect(String text)
    {
        waitUntilElementIsVisible(driver,dashboardTitle_France,20);
        return dashboardTitle_France.getText().equals(text);
    }

    public boolean english_isDashboardTitleCorrect(String text)
    {
        waitUntilElementIsVisible(driver,dashboardTitle_English,20);
        return dashboardTitle_English.getText().equals(text);
    }

    public AdminPage logoutAdmin()
    {
        waitUntilElementIsClickable(driver, profileMenu,10);
        profileMenu.click();
        action.moveToElement(button_logout).click().build().perform();
        return this;
    }

    public AdminPage initCompanyManagePage()
    {
        action.moveToElement(manageCompanyMenu).click().build().perform();
        return this;
    }






}
