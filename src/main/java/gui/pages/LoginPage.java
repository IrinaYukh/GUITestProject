package gui.pages;

import models.UserData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='login-form']//h2//img")
    WebElement siteLogo;

    @FindBy(xpath = "//input[@id='username']")
    WebElement input_Username;

    @FindBy(xpath = "//input[@id='password']")
    WebElement input_Password;

    @FindBy(xpath = "//button[contains(text(),'Sign in')]")
    WebElement button_loginSubmit;

    @FindBy(xpath = "//div[@class='clearfix alert alert-error']")
    WebElement errorAlert;



    public LoginPage isOnLoginPage() {
        isElementPresent(siteLogo);
        return this;
    }

    public boolean isOnPage()
    {
        return isElementPresent(siteLogo);
    }

    public LoginPage inputUser(UserData user)
    {
        type(input_Username, user.username);
        type(input_Password, user.password);
        return this;
    }

    public LoginPage submitUserLogin()
    {
        button_loginSubmit.click();
        return this;
    }


    public LoginPage loginToAccount(UserData user)
    {
        isOnLoginPage();
        inputUser(user);
        submitUserLogin();
        return this;
    }

    public boolean isErrorAlertExist()
    {
        return isElementPresent(errorAlert);
    }


}
