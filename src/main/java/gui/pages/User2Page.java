package gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class User2Page extends BasePage {
    public User2Page(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    Actions action = new Actions(driver);

    @FindBy(xpath = "//a[contains(text(),'Taylor Holmes')]")
    WebElement user2Name;

    @FindBy(xpath = "//span[contains(text(),'Taylor')]")
    WebElement user2ProfileMenu;

    @FindBy(xpath = "//div[@class='pull-right']//a[@href='http://icehrm-open.gamonoid.com/logout.php']")
    WebElement signOutButton;


    public User2Page isOnUser2Page()
    {
        isElementPresent(user2Name);
        return this;
    }

    public boolean isUser2NameCorrect(String name)
    {
        waitUntilElementIsloaded(driver,user2Name,10);
        return user2Name.getText().equals(name);
    }

    public User2Page logoutFromUser1Page()
    {
        waitUntilElementIsloaded(driver,user2ProfileMenu,10);
        action.moveToElement(user2ProfileMenu).click().build().perform();
        action.moveToElement(signOutButton).click().build().perform();
        return this;
    }
}
