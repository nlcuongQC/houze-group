package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import commons.PageGeneratorManager;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.HomePageObject;

import static commons.PageGeneratorManager.HouzeInvest.getBasePageObject;

@Feature("Register")
public class Test_Register_01_Register_From_Button extends AbstractTest {
    WebDriver      driver;
    BasePageObject basePage;
    HomePageObject homePage;
    DataHelper     data;
    String         name, phone, invalidPhone, duplicatePhone, invalidPhoneError, email, invalidEmail, duplicateEmail,
            invalidEmailError, password, otpErrorMessage;
    int wrongOTP, otp;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Prepare data to test")
    public void beforeClass(String browserName, String appUrl) {
        driver   = getBrowserDriver(browserName, appUrl);
        data     = DataHelper.getData();
        basePage = getBasePageObject(driver);
        basePage.verifyLoginButtonIsDisplayed();
        name              = data.getFullname();
        phone             = data.getPhone();
        invalidPhone      = "test";
        invalidPhoneError = "Không thể sử dụng số điện thoại này";
        duplicatePhone    = "0932228888";
        email             = data.getEmail();
        duplicateEmail    = "mail5@gmail.com";
        invalidEmailError = "Không thể sử dụng email này";
        invalidEmail      = "test";
        password          = "123456";
        wrongOTP          = 111111;
        otp               = 123456;
        otpErrorMessage   = "Mã OTP không hợp lệ.";
    }

    @AfterMethod(alwaysRun = true, description = "Go to homepage")
    public void afterMethod() {
        homePage = PageGeneratorManager.HouzeInvest.getHomePageObject(driver);
        homePage.navigateToHomepage();
    }

    @AfterClass(description = "Close browser")
    public void afterClass() {
        driver.quit();
    }

    @Test(description = "TC 01: Register with blank")
    public void TC_01_Register_With_Blank() {
        basePage = getBasePageObject(driver);
        basePage.clickToRegisterButton().verifyRegisterSubmitButtonIsDisabled();
    }

    @Test(description = "TC 02: Register with invalid phone")
    public void TC_02_Register_With_Invalid_Phone() {
        basePage = getBasePageObject(driver);
        basePage.clickToRegisterButton()
                .inputToRegisterNameTextbox(name)
                .inputToRegisterPhoneTextbox(invalidPhone)
                .inputToRegisterEmailTextbox(email)
                .inputToRegisterPasswordTextbox(password)
                .checkToConditionCheckbox()
                .verifyPhoneErrorEqualTo(invalidPhoneError)
                .verifyRegisterSubmitButtonIsDisabled();
    }

    @Test(description = "TC 03: Register with duplicated phone")
    public void TC_03_Register_With_Duplicated_Phone() {
        basePage = getBasePageObject(driver);
        basePage.clickToRegisterButton()
                .inputToRegisterNameTextbox(name)
                .inputToRegisterPhoneTextbox(duplicatePhone)
                .inputToRegisterEmailTextbox(email)
                .inputToRegisterPasswordTextbox(password)
                .checkToConditionCheckbox()
                .verifyPhoneErrorEqualTo(invalidPhoneError)
                .verifyRegisterSubmitButtonIsDisabled();
    }

    @Test(description = "TC 04: Register with invalid email")
    public void TC_04_Register_With_Invalid_Email() {
        basePage = getBasePageObject(driver);
        basePage.clickToRegisterButton()
                .inputToRegisterNameTextbox(name)
                .inputToRegisterPhoneTextbox(phone)
                .inputToRegisterEmailTextbox(invalidEmail)
                .inputToRegisterPasswordTextbox(password)
                .checkToConditionCheckbox()
                .verifyEmailErrorEqualTo(invalidEmailError)
                .verifyRegisterSubmitButtonIsDisabled();
    }

    @Test(description = "TC 05: Register with existed email")
    public void TC_05_Register_With_Duplicated_Email() {
        basePage = getBasePageObject(driver);
        basePage.clickToRegisterButton()
                .inputToRegisterNameTextbox(name)
                .inputToRegisterPhoneTextbox(phone)
                .inputToRegisterEmailTextbox(duplicateEmail)
                .inputToRegisterPasswordTextbox(password)
                .checkToConditionCheckbox()
                .verifyEmailErrorEqualTo(invalidEmailError)
                .verifyRegisterSubmitButtonIsDisabled();
    }

    @Test(description = "TC 06: Register but don't agree condition")
    public void TC_06_Register_With_Unchecked_Condition() {
        basePage = getBasePageObject(driver);
        basePage.clickToRegisterButton()
                .inputToRegisterNameTextbox(name)
                .inputToRegisterPhoneTextbox(phone)
                .inputToRegisterEmailTextbox(email)
                .inputToRegisterPasswordTextbox(password)
                .verifyRegisterSubmitButtonIsDisabled();
    }

    @Test(description = "TC 07: Register with wrong OTP")
    public void TC_07_Register_With_Wrong_OTP() {
        basePage = getBasePageObject(driver);
        basePage.clickToRegisterButton()
                .inputToRegisterNameTextbox(name)
                .inputToRegisterPhoneTextbox(phone)
                .inputToRegisterEmailTextbox(email)
                .inputToRegisterPasswordTextbox(password)
                .checkToConditionCheckbox()
                .verifyRegisterSubmitButtonIsEnabled()
                .clickToSubmitRegisterButton()
                .inputToOTPTextbox(wrongOTP)
                .verifyOTPErrorEqualTo(otpErrorMessage);
    }

    @Test(description = "TC 08: Register with valid OTP")
    public void TC_08_Register_With_Valid_OTP() {
        basePage = getBasePageObject(driver);
        basePage.clickToRegisterButton()
                .inputToRegisterNameTextbox(name)
                .inputToRegisterPhoneTextbox(phone)
                .inputToRegisterEmailTextbox(email)
                .inputToRegisterPasswordTextbox(password)
                .checkToConditionCheckbox()
                .verifyRegisterSubmitButtonIsEnabled()
                .clickToSubmitRegisterButton()
                .inputToOTPTextbox(otp);
    }


}
