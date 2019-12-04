package gui.pages;

import gui.pageManager.PageObjectManager;
import gui.pages.admin.AdminPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerPage extends BasePage
{
    private AdminPage adminPage;
    private PageObjectManager pageManager;


    public ManagerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    Actions action = new Actions(driver);

    @FindBy(xpath = "//a[contains(text(),'Lala Lamees')]")
    WebElement managerName;

    @FindBy(xpath = "//h1[contains(text(),'Dashboard')]")
    WebElement dashboardTitle_English;

    @FindBy(xpath = "//h1[contains(text(),'Übersicht')]")
    WebElement dashboardTitle_German;

    @FindBy(xpath = "//h1[contains(text(),'Tableau de bord')]")
    WebElement dashboardTitle_France;

    @FindBy(xpath = "//li[contains(@class,'dropdown user user-menu')]//a[contains(@class,'dropdown-toggle')]")
    WebElement managerProfileMenu;

    @FindBy(xpath = "//div[@class='pull-right']//a[@href='http://icehrm-open.gamonoid.com/logout.php']")
    WebElement signOutButton;


    public ManagerPage isOnManagerPage()
    {
        waitUntilElementIsVisible(driver,managerName,10);
        isElementPresent(managerName);
        return this;
    }

    public boolean isManagerNameCorrect(String name)
    {
        waitUntilElementIsVisible(driver,managerName,10);
        action.moveToElement(managerName).build().perform();
        return managerName.getText().equals(name);
    }

    public ManagerPage logoutFromManagerPage()
    {
        waitUntilElementIsloaded(driver,managerProfileMenu,10);
        action.moveToElement(managerProfileMenu).click().build().perform();
        action.moveToElement(signOutButton).build().perform();
        signOutButton.click();
        return this;
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
        action.moveToElement(dashboardTitle_English).build().perform();
        return dashboardTitle_English.getText().equals(text);
    }
}
