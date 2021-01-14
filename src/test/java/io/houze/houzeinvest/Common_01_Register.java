package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houzeinvest.investor.BasePageObject;

import static commons.PageGeneratorManager.HouzeInvest.getBasePageObject;

public class Common_01_Register extends AbstractTest {
    public static String name, phone, email, password;
    public static int       otp;
    public static WebDriver driver;
    BasePageObject basePage;
    DataHelper     data;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Register an account")
    public void beforeTest(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        data   = DataHelper.getData();

        basePage = getBasePageObject(driver);
        basePage.verifyLoginButtonIsDisplayed();
        name     = data.getFullname();
        phone    = data.getPhone();
        email    = data.getEmail();
        password = "123456";
        otp      = 123456;

        basePage = getBasePageObject(driver);
        basePage.clickToRegisterButton()
                .inputToRegisterNameTextbox(name)
                .inputToRegisterPhoneTextbox(phone)
                .inputToRegisterEmailTextbox(email)
                .inputToRegisterPasswordTextbox(password)
                .checkToConditionCheckbox()
                .verifyRegisterSubmitButtonIsEnabled()
                .clickToSubmitRegisterButton()
                .inputToOTPTextbox(otp)
                .verifyOTPIsDisappeared();
    }

    @AfterTest(alwaysRun = true, description = "Close browsers")
    public void afterTest() {
        closeBrowserAndDriver(driver);
    }
}
