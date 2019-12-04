package gui;

import config.JsonConfig;
import gui.pageManager.PageObjectManager;
import gui.pages.admin.AdminPage;
import gui.pages.LoginPage;
import gui.pages.ManagerPage;
import models.UserData;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ManagerPageTest extends BaseTest
{
    private PageObjectManager pageManager;
    private LoginPage loginPage;
    private ManagerPage managerPage;
    private AdminPage adminPage;

    private String username = JsonConfig.getValue("manager","username");
    private String pass = JsonConfig.getValue("manager","password");

    UserData user = new UserData(username,pass);

    @BeforeTest
    public void initPages() {
        pageManager = new PageObjectManager(getWebDriver());
        loginPage = pageManager.getLoginPage();
        managerPage = pageManager.getManagerPage();
        adminPage = pageManager.getAdminPage();

    }

    @Test
    public void manager_LoginTest()
    {
        loginPage.loginToAccount(user);
        managerPage.isOnManagerPage();
        adminPage.changeLanguage("English");

    }
}
