package gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class User1Page extends BasePage {
    public User1Page(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    Actions action = new Actions(driver);

    @FindBy(xpath = "//a[contains(text(),\"Sofia O'Sullivan\")]")
    WebElement user1Name;

    @FindBy(xpath = "//span[contains(text(),'Sofia')]")
    WebElement user1ProfileMenu;

    @FindBy(xpath = "//div[@class='pull-right']//a[@href='http://icehrm-open.gamonoid.com/logout.php']")
    WebElement signOutButton;

    public User1Page isOnUserPage()
    {
        isElementPresent(user1Name);
        return this;
    }

    public boolean isUser1NameCorrect(String name)

    {
        waitUntilElementIsloaded(driver,user1ProfileMenu,10);
        return user1ProfileMenu.getText().equals(name);
    }
    public User1Page logoutFromUser1Page()
    {
        waitUntilElementIsloaded(driver,user1ProfileMenu,10);
        action.moveToElement(user1ProfileMenu).click().build().perform();
        action.moveToElement(signOutButton).click().build().perform();
        return this;
    }
}
