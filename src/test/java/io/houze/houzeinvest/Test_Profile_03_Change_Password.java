package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.houzeinvest.investor.AccountPageObject;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.ProfilePageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

@Epic("Account")
@Feature("Profile")
@Story("Update")
public class Test_Profile_03_Change_Password extends AbstractTest {
    WebDriver  driver;
    DataHelper data;
    String     email, newEmail, currentPw, invalidCurrentPw, notMatchErr, newPW, invalidConfirmNewPw;

    BasePageObject    basePage;
    ProfilePageObject profilePage;
    AccountPageObject accountPage;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Login with a verified account")
    public void beforeClass(String browserName, String appUrl) {
        data = DataHelper.getData();

        email               = Common_01_Register.email;
        newEmail            = data.getEmail();
        currentPw           = Common_01_Register.password;
        invalidCurrentPw    = "invalidpw";
        notMatchErr         = "Mật khẩu chưa trùng khớp";
        newPW               = "654321";
        invalidConfirmNewPw = "111111";

        driver   = getBrowserDriver(browserName, appUrl);
        basePage = getBasePageObject(driver);
        basePage.verifyLoginButtonIsDisplayed()
                .loginToAnAccount(Common_01_Register.phone, Common_01_Register.password)
                .verifyAlertMessageEqualTo("Đăng nhập thành công")
                .verifyLoginButtonIsDisappeared();
    }

    @BeforeMethod(description = "Open change password form")
    public void beforeMethod() {
        profilePage = getProfilePageObject(driver);
        profilePage.navigateToPage()
                   .clickToChangePwBtn()
                   .verifyChangePwBtnIsDisappeared()
                   .verifyChangePwSaveBtnIsDisplayed()
                   .verifyChangePwCancelBtnIsDisplayed()
                   .verifyChangePwFilledPwTxtbxIsDisappeared()
                   .verifyChangePwCurrentPwTxtbxIsDisplayed()
                   .verifyChangePwNewPwTxtbxIsDisplayed()
                   .verifyChangePwConfirmNewPwTxtbxIsDisplayed();
    }

    @AfterClass(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Change password 01: Change password with no changes")
    public void TC_01_Change_Pw_With_No_Changes() {
        profilePage.verifyChangePwSaveBtnIsDisabled();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Change password 02: Change password with invalid current password")
    public void TC_02_Change_Pw_With_Invalid_Current_Pw() {
        profilePage.verifyChangePwSaveBtnIsDisabled()
                   .inputToChangePwCurrentPwTxtbx(invalidCurrentPw)
                   .verifyChangePwCurrentPwTxtbxValueEqualTo(invalidCurrentPw)
                   .inputToChangePwNewPwTxtbx(newPW)
                   .verifyChangePwNewPwTxtbxValueEqualTo(newPW)
                   .inputToChangePwConfirmNewPwTxtbx(newPW)
                   .verifyChangePwConfirmNewPwTxtbxValueEqualTo(newPW)
                   .verifyChangePwSaveBtnIsEnabled()
                   .clickToChangePwSaveBtn();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Change password 03: Change password with confirm new password not match")
    public void TC_03_Change_Pw_With_Confirm_New_Password_Not_Match() {
        profilePage.verifyChangePwSaveBtnIsDisabled()
                   .inputToChangePwCurrentPwTxtbx(currentPw)
                   .verifyChangePwCurrentPwTxtbxValueEqualTo(currentPw)
                   .inputToChangePwNewPwTxtbx(newPW)
                   .verifyChangePwNewPwTxtbxValueEqualTo(newPW)
                   .inputToChangePwConfirmNewPwTxtbx(invalidConfirmNewPw)
                   .verifyChangePwConfirmNewPwTxtbxValueEqualTo(invalidConfirmNewPw)
                   .verifyChangePwSaveBtnIsDisabled()
                   .verifyChangePwNewPwNotMatchErrorEqualTo(notMatchErr);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Change password 04: Change password with valid data")
    public void TC_04_Change_Pw_With_Valid_Data() {
        profilePage.verifyChangePwSaveBtnIsDisabled()
                   .inputToChangePwCurrentPwTxtbx(currentPw)
                   .verifyChangePwCurrentPwTxtbxValueEqualTo(currentPw)
                   .inputToChangePwNewPwTxtbx(newPW)
                   .verifyChangePwNewPwTxtbxValueEqualTo(newPW)
                   .inputToChangePwConfirmNewPwTxtbx(newPW)
                   .verifyChangePwConfirmNewPwTxtbxValueEqualTo(newPW)
                   .verifyChangePwSaveBtnIsEnabled()
                   .verifyChangePwNewPwNotMatchErrorIsNotDisplayed()
                   .clickToChangePwSaveBtn().verifyChangePwBtnIsDisplayed();

        accountPage = getAccountPageObject(driver);
        accountPage.navigateToPage().logout();

        basePage.verifyAlertMessageEqualTo("Đã đăng xuất")
                .loginToAnAccount(Common_01_Register.phone, newPW)
                .verifyAlertMessageEqualTo("Đăng nhập thành công")
                .verifyLoginButtonIsDisappeared();
    }
}
