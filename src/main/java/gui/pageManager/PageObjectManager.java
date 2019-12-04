package gui.pageManager;

import gui.pages.*;
import gui.pages.admin.AdminCompanyPage;
import gui.pages.admin.AdminPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager extends BasePage {

    private WebDriver driver;
    private LoginPage loginPage;
    private AdminPage adminPage;
    private AdminCompanyPage adminCompanyPage;
    private ManagerPage managerPage;
    private User1Page user1Page;
    private User2Page user2Page;

    public PageObjectManager (WebDriver driver)
    {
        super(driver);
        this.driver=driver;
    }

    public LoginPage getLoginPage()
    {
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public AdminPage getAdminPage()
    {
        return (adminPage == null) ? adminPage = new AdminPage(driver) : adminPage;
    }

    public ManagerPage getManagerPage()
    {
        return (managerPage == null) ? managerPage = new ManagerPage(driver) : managerPage;
    }

    public User1Page getUser1Page()
    {
        return (user1Page == null) ? user1Page = new User1Page(driver) : user1Page;
    }

    public User2Page getUser2Page()
    {
        return (user2Page == null) ? user2Page = new User2Page(driver) : user2Page;
    }

    public AdminCompanyPage getAdminCompanyPage()
    {
        return (adminCompanyPage == null) ? adminCompanyPage = new AdminCompanyPage(driver) : adminCompanyPage;
    }
}
