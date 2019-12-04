package gui;

import config.JsonConfig;
import gui.pageManager.PageObjectManager;
import gui.pages.LoginPage;
import gui.pages.admin.AdminCompanyPage;
import gui.pages.admin.AdminPage;
import models.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AdminPageTest extends BaseTest {

    // Pages initialization
    private PageObjectManager pageManager;
    private LoginPage loginPage;
    private AdminPage adminPage;
    private AdminCompanyPage adminCompanyPage;

    private String username = JsonConfig.getValue("admin", "username");
    private String pass = JsonConfig.getValue("admin", "password");

    UserData admin = new UserData(username,pass);

    // Generate value for test
    String number = String.valueOf(System.currentTimeMillis()/10000000);
    String name = "Test Company"+ number;

    @BeforeClass
    public void initPages() {
        pageManager = new PageObjectManager(getWebDriver());
        loginPage = pageManager.getLoginPage();
        adminPage = pageManager.getAdminPage();
        adminCompanyPage = pageManager.getAdminCompanyPage();

        loginPage.loginToAccount(admin);
    }


    // Verify successful changing languages on Admin Page with translation verifications
    @Test (priority = 1)
    public void languageChangesTest ()
    {
        adminPage.isOnPage()
                .changeLanguage("France");
        Assert.assertTrue(adminPage.france_isDashboardTitleCorrect("Tableau de bord Admin  "));

        adminPage.changeLanguage("German");
        Assert.assertTrue(adminPage.german_isDashboardTitleCorrect("Übersicht Administrator  "));

        adminPage.changeLanguage("English");
        Assert.assertTrue(adminPage.english_isDashboardTitleCorrect("Dashboard Admin  "));
    }

    /* Test. Verify successful creation new Company during Company Manage
            - create new company
            - verify that new company exist in Companies Table
     */
    @Test (priority = 2)
    public void addNewCompany()
    {
        adminPage.isOnPage()
                .initCompanyManagePage();
        adminCompanyPage.isOnCompanyManagePage()
                .clickAddNewButton()
                .isOnAddNewCompanyForm()
                .inputCompanyName(name)
                .inputCompanyDetails()
                .inputCompanyAddress("Tel Aviv")
                .selectCompanyType("Department")
                .inputCountry("Israel")
                .inputTimeZone("(GMT+02:00) Asia/Jerusalem")
                .pressSaveButton();
        Assert.assertTrue(adminCompanyPage.isCompanyExist(name));

    }

    @Test(priority = 3)
    public void deleteCompany()
    {
//        adminPage.isOnPage()
//                .initCompanyManagePage();
        adminCompanyPage.isOnCompanyManagePage()
                .deleteCompany("Test Company157479")
                .isOnDeleteAlertMessage()
                .clickDeleteButton();
        Assert.assertTrue(adminCompanyPage.isOnCompanyManagepage());

    }
}
