package io.houze.houzesuper.webadmin;

import commons.AbstractTest;
import commons.DataHelper;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houze.BasePageObject;
import pageobjects.houze.LoginPageObject;

public class Common_01_Create_Apartment extends AbstractTest {
    WebDriver       driver;
    BasePageObject  basePage;
    LoginPageObject loginPage;
    DataHelper      data;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke")
    public void beforeTest(String browserName, String appUrl) {
        driver    = getBrowserDriver(browserName, appUrl);
        data      = DataHelper.getData();
        loginPage = PageGeneratorManager.Houze.getLoginPage(driver);
        loginPage.inputToUsernameTextbox(GlobalConstants.USERNAME_ADMIN_HOUZE_PROD);
        loginPage.inputToPasswordTextbox(GlobalConstants.PASSWORD_ADMIN_HOUZE_PROD);

    }
    @AfterTest(alwaysRun = true)
    public void afterTest() {
        closeBrowserAndDriver(driver);
    }
}
