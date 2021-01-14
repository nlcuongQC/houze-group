package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.*;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.houzeinvest.admin.BaseAdminPageObject;
import pageobjects.houzeinvest.admin.CustomerDetailPageObject;
import pageobjects.houzeinvest.admin.CustomerPageObject;
import pageobjects.houzeinvest.admin.LoginAdminPageObject;
import pageobjects.houzeinvest.investor.AccountPageObject;
import pageobjects.houzeinvest.investor.ProfilePageObject;

import static commons.PageGeneratorManager.HouzeInvest.getBasePageObject;

@Epic("Account")
@Feature("Profile")
@Story("EKYC")
public class Test_Profile_01_EKYC extends AbstractTest {
    WebDriver                                       driver;
    pageobjects.houzeinvest.investor.BasePageObject basePage;
    AccountPageObject                               accountPage;
    ProfilePageObject                               profilePage;
    LoginAdminPageObject loginAdminPage;
    BaseAdminPageObject  baseAdminPage;
    CustomerPageObject   customerAdminPage;
    CustomerDetailPageObject                        customerDetailAdminPage;
    DataHelper                                      data;
    String                                          gender, birthday, successPopupMessage, inprogressStatus, address,
            fullAddress, issueDate, issuePlace, issueNumber, imageFront, imageBack;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Login to an account")
    public void beforeClass(String browserName, String appUrl) {
        data                = DataHelper.getData();
        gender              = "Nam";
        birthday            = data.getBirthday("dd/MM/yyyy");
        successPopupMessage = "Đã gửi xác thực";
        inprogressStatus    = "Đang chờ kết quả";
        issueDate           = data.getBirthday("dd/MM/yyyy");
        issuePlace          = "TPHCM";
        issueNumber         = "025344555";
        imageFront          = "imageA.jpg";
        imageBack           = "imageB.jpg";
        address             = data.getAddress();
        fullAddress         = data.getAddress();

        driver   = getBrowserDriver(browserName, appUrl);
        basePage = getBasePageObject(driver);
        basePage.verifyLoginButtonIsDisplayed()
                .loginToAnAccount(Common_01_Register.phone, Common_01_Register.password)
                .verifyLoginButtonIsDisappeared()
                .verifyAlertMessageEqualTo("Đăng nhập thành công");
    }

    @BeforeMethod(description = "Open verify form")
    public void beforeMethod() {
        accountPage = PageGeneratorManager.HouzeInvest.getAccountPageObject(driver);
        accountPage.navigateToPage().clickToVerifyButton();
        profilePage = PageGeneratorManager.HouzeInvest.getProfilePageObject(driver);
        profilePage.verifyBeginVerifyBtnIsDisplayed();
    }

    @AfterClass(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "EKYC 01: Verify with blank form")
    public void TC_01_Verify_With_Blank_Form() {
        profilePage.clickToBeginVerifyButton().verifyVerifySubmitBtnDisabled();
    }

    @Test(description = "EKYC 02 - Verify with id card")
    public void TC_02_Verify_With_ID_Card() {
        profilePage.clickToBeginVerifyButton()
                   .inputToVerifyNameTextbox(Common_01_Register.name)
                   .chooseGenderDropdown(gender)
                   .inputToBirthdayTextbox(birthday)
                   .inputToAddressTextbox(address)
                   .inputToFullAddressTextbox(fullAddress)
                   .inputToIssueDateTextbox(issueDate)
                   .inputToIssuePlaceTextbox(issuePlace)
                   .inputToIssueNumberTextbox(issueNumber)
                   .uploadFrontIssueImage(imageFront);

        profilePage.uploadBackIssueImage(imageBack)
                   .verifyVerifySubmitBtnDisabled()
                   .clickToVerifySubmitButton()
                   .verifyVerifyPopupTitleEqualTo(successPopupMessage)
                   .clickToVerifyPopupDoneButton()
                   .verifyStatusProfileEqualTo(inprogressStatus);

        loginAdminPage = PageGeneratorManager.HouzeInvest.getLoginAdminPageObject(driver);
        loginAdminPage.navigateToPage();
        loginAdminPage.inputToUsernameTextbox(GlobalConstants.USERNAME_ADMIN_HI);
        loginAdminPage.inputToPasswordTextbox(GlobalConstants.PASSWORD_ADMIN_HI);
        loginAdminPage.clickToLoginButton();

        baseAdminPage = PageGeneratorManager.HouzeInvest.getBaseAdminPageObject(driver);
        baseAdminPage.clickToNotificationButton().clickToEKYCLink();

        customerAdminPage = PageGeneratorManager.HouzeInvest.getCustomerAdminPage(driver);
        customerAdminPage.clickToDynamicCustomerName(Common_01_Register.name);

        customerDetailAdminPage = PageGeneratorManager.HouzeInvest.getCustomerDetailAdminPage(driver);
        customerDetailAdminPage.clickToRejectButton().clickToVerifySubmitButton();
        try {
            customerDetailAdminPage = PageGeneratorManager.HouzeInvest.getCustomerDetailAdminPage(driver);
            customerDetailAdminPage.verifyApproveBtnIsDisappeared().verifyRejectBtnIsDisappeared();
        } catch (StaleElementReferenceException e) {
            customerDetailAdminPage = PageGeneratorManager.HouzeInvest.getCustomerDetailAdminPage(driver);
            customerDetailAdminPage.verifyApproveBtnIsDisappeared().verifyRejectBtnIsDisappeared();
        }
        customerDetailAdminPage.clickToDynamicBreadcrumbs("Danh sách khách hàng");
        customerAdminPage = PageGeneratorManager.HouzeInvest.getCustomerAdminPage(driver);
        customerAdminPage.verifyCustomerStatusByDynamicName(Common_01_Register.name, "Đã từ chối");

        profilePage = PageGeneratorManager.HouzeInvest.getProfilePageObject(driver);
        profilePage.navigateToPage().verifyBeginVerifyBtnIsDisplayed();
    }

    @Test(description = "EKYC 03: Verify with passport")
    public void TC_03_Verify_With_Passport() {
        profilePage.clickToBeginVerifyButton()
                   .inputToVerifyNameTextbox(Common_01_Register.name)
                   .chooseGenderDropdown(gender)
                   .inputToBirthdayTextbox(birthday)
                   .inputToAddressTextbox(address)
                   .inputToFullAddressTextbox(fullAddress)
                   .inputToIssueDateTextbox(issueDate)
                   .inputToIssuePlaceTextbox(issuePlace)
                   .inputToIssueNumberTextbox(issueNumber)
                   .clickChooseTypeIssuePaper("1");


        profilePage.uploadFrontIssueImage(imageFront)
                   .verifyVerifySubmitBtnEnabled()
                   .clickToVerifySubmitButton()
                   .verifyVerifyPopupTitleEqualTo(successPopupMessage)
                   .clickToVerifyPopupDoneButton()
                   .verifyStatusProfileEqualTo(inprogressStatus);

        baseAdminPage = PageGeneratorManager.HouzeInvest.getBaseAdminPageObject(driver);
        baseAdminPage.navigateToPage().clickToNotificationButton().clickToEKYCLink();

        customerAdminPage = PageGeneratorManager.HouzeInvest.getCustomerAdminPage(driver);
        customerAdminPage.clickToDynamicCustomerName(Common_01_Register.name);

        customerDetailAdminPage = PageGeneratorManager.HouzeInvest.getCustomerDetailAdminPage(driver);
        customerDetailAdminPage.clickToApproveButton().clickToVerifySubmitButton();
        try {
            customerDetailAdminPage = PageGeneratorManager.HouzeInvest.getCustomerDetailAdminPage(driver);
            customerDetailAdminPage.verifyApproveBtnIsDisappeared().verifyRejectBtnIsDisappeared();
        } catch (StaleElementReferenceException e) {
            customerDetailAdminPage.refreshPage();
            customerDetailAdminPage = PageGeneratorManager.HouzeInvest.getCustomerDetailAdminPage(driver);
            customerDetailAdminPage.verifyApproveBtnIsDisappeared().verifyRejectBtnIsDisappeared();
        }
        customerDetailAdminPage.clickToDynamicBreadcrumbs("Danh sách khách hàng");
        customerAdminPage = PageGeneratorManager.HouzeInvest.getCustomerAdminPage(driver);
        customerAdminPage.verifyCustomerStatusByDynamicName(Common_01_Register.name, "Đã xác thực");

        accountPage = PageGeneratorManager.HouzeInvest.getAccountPageObject(driver);
        accountPage.navigateToPage().verifyStatusEqualTo("Đã xác thực").verifyDepositBtnIsDisplayed();
        accountPage.clickToProfileMenu();

        profilePage = PageGeneratorManager.HouzeInvest.getProfilePageObject(driver);
        profilePage.verifyStatusProfileEqualTo("Xác thực thành công");
    }
}
