package gui;

import config.JsonConfig;
import gui.pageManager.PageObjectManager;
import gui.pages.LoginPage;
import gui.pages.ManagerPage;
import gui.pages.User1Page;
import gui.pages.User2Page;
import gui.pages.admin.AdminPage;
import models.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

//        private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
//        private LoginPage loginPage = new LoginPage(getWebDriver());

    private static final Logger logger = LoggerFactory.getLogger(LoginPageTest.class);

    // Pages initialization
    private PageObjectManager pageManager;
    private LoginPage loginPage;
    private AdminPage adminPage;
    private ManagerPage managerPage;
    private User1Page user1Page;
    private User2Page user2Page;

    private String admin_username = JsonConfig.getValue("admin", "username");
    private String admin_pass = JsonConfig.getValue("admin", "password");

    private String manager_username = JsonConfig.getValue("manager","username");
    private String manager_pass = JsonConfig.getValue("manager", "password");

    private String user1_username = JsonConfig.getValue("user1","username");
    private String user1_pass = JsonConfig.getValue("user1","password");

    private String user2_username = JsonConfig.getValue("user2","username");
    private String user2_pass = JsonConfig.getValue("user2","password");


    @BeforeClass
    public void initPages() {
        pageManager = new PageObjectManager(getWebDriver());
        loginPage = pageManager.getLoginPage();
        adminPage = pageManager.getAdminPage();
        managerPage = pageManager.getManagerPage();
        user1Page = pageManager.getUser1Page();
        user2Page = pageManager.getUser2Page();
    }

    // Verify successful login to the system with Admin values
    @Test
    public void admin_LoginTest() {
        logger.info("Admin creation with param {} {}", admin_username, admin_pass);
        UserData admin = new UserData(admin_username, admin_pass);

        loginPage.loginToAccount(admin);
        adminPage.isOnPage()
                .changeLanguage("English");
        Assert.assertTrue(adminPage.english_isDashboardTitleCorrect("Dashboard Admin  "));
        adminPage.logoutAdmin();
        Assert.assertTrue(loginPage.isOnPage());
    }

    // Verify successful login to the system with Manager values
    @Test
    public void manager_LoginTest()
    {
        UserData manager = new UserData(manager_username,manager_pass);

        loginPage.loginToAccount(manager);
        managerPage.isOnManagerPage();
        adminPage.changeLanguage("English");
        Assert.assertTrue(managerPage.isManagerNameCorrect("Lala Lamees"));
        managerPage.logoutFromManagerPage();
        Assert.assertTrue(loginPage.isOnPage());
    }

    // Verify successful login to the system with User1 values
    @Test
    public void user1_LoginTest()
    {
        UserData user1 = new UserData(user1_username,user1_pass);

        loginPage.loginToAccount(user1);
        user1Page.isOnUserPage();
        adminPage.changeLanguage("English");
        Assert.assertTrue(user1Page.isUser1NameCorrect("Sofia"));
        user1Page.logoutFromUser1Page();
        Assert.assertTrue(loginPage.isOnPage());
    }

    // Verify successful login to the system with User2 values
    @Test
    public void user2_LoginTest()
    {
        UserData user2 = new UserData(user2_username,user2_pass);

        loginPage.loginToAccount(user2);
        user2Page.isOnUser2Page();
        adminPage.changeLanguage("English");
        Assert.assertTrue(user2Page.isUser2NameCorrect("Taylor Holmes"));
        user2Page.logoutFromUser1Page();
        Assert.assertTrue(loginPage.isOnPage());
    }

    // Check the inability to log into the system with the wrong password for Admin
    @Test
    public void admin_loginWithWrongPassword() {
        String pass = "user";
        UserData admin = new UserData(admin_username, pass);
        loginPage.loginToAccount(admin)
                .isOnLoginPage();
        Assert.assertTrue(loginPage.isErrorAlertExist());
    }

    // Check the inability to log into the system with wrong user for Admin
    @Test
    public void admin_loginWithWrongUsername()
    {
        String username = "user";
        UserData admin = new UserData(username, admin_pass);

        loginPage.loginToAccount(admin)
                    .isOnLoginPage();
        Assert.assertTrue(loginPage.isErrorAlertExist());
    }

    // Check the inability to log into the system with the wrong password for Manager
    @Test
    public void manager_loginWithWrongPassword() {
        String pass = "user";
        UserData manager = new UserData(manager_username, pass);
        loginPage.loginToAccount(manager)
                .isOnLoginPage();
        Assert.assertTrue(loginPage.isErrorAlertExist());
    }

    // Check the inability to log into the system with wrong user for Manager
    @Test
    public void manager_loginWithWrongUsername()
    {
        String username = "user";
        UserData manager = new UserData(username, manager_pass);

        loginPage.loginToAccount(manager)
                .isOnLoginPage();
        Assert.assertTrue(loginPage.isErrorAlertExist());
    }

    // Check the inability to log into the system with the wrong password for User1
    @Test
    public void user1_loginWithWrongPassword() {
        String pass = "user";
        UserData user1 = new UserData(user1_username, pass);
        loginPage.loginToAccount(user1)
                .isOnLoginPage();
        Assert.assertTrue(loginPage.isErrorAlertExist());
    }

    // Check the inability to log into the system with wrong user for User1
    @Test
    public void user1_loginWithWrongUsername()
    {
        String username = "user";
        UserData user1 = new UserData(username, user1_pass);

        loginPage.loginToAccount(user1)
                .isOnLoginPage();
        Assert.assertTrue(loginPage.isErrorAlertExist());
    }

    // Check the inability to log into the system with the wrong password for User2
    @Test
    public void user2_loginWithWrongPassword() {
        String pass = "user";
        UserData user2 = new UserData(user2_username, pass);
        loginPage.loginToAccount(user2)
                .isOnLoginPage();
        Assert.assertTrue(loginPage.isErrorAlertExist());
    }

    // Check the inability to log into the system with wrong user for User2
    @Test
    public void user2_loginWithWrongUsername()
    {
        String username = "user";
        UserData user2 = new UserData(username, user2_pass);

        loginPage.loginToAccount(user2)
                .isOnLoginPage();
        Assert.assertTrue(loginPage.isErrorAlertExist());
    }

}
