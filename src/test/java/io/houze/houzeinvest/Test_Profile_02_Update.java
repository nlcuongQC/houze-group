package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.ProfilePageObject;

import static commons.PageGeneratorManager.HouzeInvest.getBasePageObject;
import static commons.PageGeneratorManager.HouzeInvest.getProfilePageObject;

@Epic("Account")
@Feature("Profile")
@Story("Update")
public class Test_Profile_02_Update extends AbstractTest {
    WebDriver  driver;
    DataHelper data;
    String     email, newEmail, validateText;

    BasePageObject    basePage;
    ProfilePageObject profilePage;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Login with a verified account")
    public void beforeClass(String browserName, String appUrl) {
        data = DataHelper.getData();

        email        = Common_01_Register.email;
        newEmail     = data.getEmail();
        validateText = "Email được sử dụng";

        driver   = getBrowserDriver(browserName, appUrl);
        basePage = getBasePageObject(driver);
        basePage.verifyLoginButtonIsDisplayed()
                .loginToAnAccount(Common_01_Register.phone, Common_01_Register.password)
                .verifyAlertMessageEqualTo("Đăng nhập thành công")
                .verifyLoginButtonIsDisappeared();
    }

    @BeforeMethod(description = "Open update profile form")
    public void beforeMethod() {
        profilePage = getProfilePageObject(driver);
        profilePage.navigateToPage()
                   .verifyUpdateProfileBtnIsDisplayed()
                   .clickToUpdateProfileBtn()
                   .verifyUpdateProfileBtnIsDisappeared()
                   .verifyUpdateProfileSaveBtnIsDisplayed()
                   .verifyUpdateProfileCancelBtnIsDisplayed()
                   .verifyUpdateProfileEmailTxtbxIsNotDisabled();
    }

    @AfterClass(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Profile Update 01: Update with no changes")
    public void TC_01_Update_With_No_Changes() {
        profilePage.clickToUpdateProfileSaveBtn()
                   .verifyUpdateProfileSaveBtnIsDisappeared()
                   .verifyUpdateProfileCancelBtnIsDisappeared()
                   .verifyUpdateProfileBtnIsDisplayed()
                   .verifyUpdateProfileEmailTxtbxValueEqualTo(email);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Profile Update 02: Update avatar and cancel")
    public void TC_02_Update_Avatar_And_Cancel() {
        profilePage.uploadAvatar("imageA.jpg")
                   .verifyAvatarIsDisplayed()
                   .clickToUpdateProfileCancelBtn()
                   .verifyUpdateProfileSaveBtnIsDisappeared()
                   .verifyUpdateProfileCancelBtnIsDisappeared()
                   .verifyUpdateProfileBtnIsDisplayed()
                   .verifyAvatarIsNotDisplayed()
                   .verifyUpdateProfileEmailTxtbxValueEqualTo(email);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Profile Update 03: Update avatar")
    public void TC_03_Update_Avatar() {
        profilePage.uploadAvatar("imageA.jpg")
                   .verifyAvatarIsDisplayed()
                   .clickToUpdateProfileSaveBtn()
                   .verifyUpdateProfileSaveBtnIsDisappeared()
                   .verifyUpdateProfileCancelBtnIsDisappeared()
                   .verifyUpdateProfileBtnIsDisplayed()
                   .verifyAvatarIsDisplayed()
                   .verifyUpdateProfileEmailTxtbxValueEqualTo(email);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Profile Update 04: Update email and cancel")
    public void TC_04_Update_Email_And_Cancel() {
        profilePage.inputToUpdateProfileEmailTxtbx(newEmail)
                   .verifyUpdateProfileEmailTxtbxValueEqualTo(newEmail)
                   .clickToUpdateProfileCancelBtn()
                   .verifyUpdateProfileSaveBtnIsDisappeared()
                   .verifyUpdateProfileCancelBtnIsDisappeared()
                   .verifyUpdateProfileBtnIsDisplayed()
                   .verifyUpdateProfileEmailTxtbxValueEqualTo(email);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Profile Update 05: Update email")
    public void TC_05_Update_Email() {
        profilePage.inputToUpdateProfileEmailTxtbx(newEmail)
                   .verifyEmailValidateTxtEqualTo(validateText)
                   .verifyUpdateProfileEmailTxtbxValueEqualTo(newEmail)
                   .clickToUpdateProfileSaveBtn()
                   .verifyUpdateProfileSaveBtnIsDisappeared()
                   .verifyUpdateProfileCancelBtnIsDisappeared()
                   .verifyUpdateProfileBtnIsDisplayed()
                   .verifyUpdateProfileEmailTxtbxValueEqualTo(newEmail);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Profile Update 06: Update avatar and email")
    public void TC_06_Update_Avatar_And_Email() {
        profilePage.uploadAvatar("imageA.jpg")
                   .verifyAvatarIsDisplayed()
                   .inputToUpdateProfileEmailTxtbx(newEmail)
                   .verifyUpdateProfileEmailTxtbxValueEqualTo(newEmail)
                   .clickToUpdateProfileSaveBtn()
                   .verifyUpdateProfileSaveBtnIsDisappeared()
                   .verifyUpdateProfileCancelBtnIsDisappeared()
                   .verifyUpdateProfileBtnIsDisplayed()
                   .verifyAvatarIsDisplayed()
                   .verifyUpdateProfileEmailTxtbxValueEqualTo(newEmail);
    }
}
