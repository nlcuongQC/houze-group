package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houzeinvest.admin.BaseAdminPageObject;
import pageobjects.houzeinvest.admin.CustomerDetailPageObject;
import pageobjects.houzeinvest.admin.CustomerPageObject;
import pageobjects.houzeinvest.admin.LoginAdminPageObject;
import pageobjects.houzeinvest.investor.AccountPageObject;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.ProfilePageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

public class Common_02_Verify_With_EKYC extends AbstractTest {
    public static String gender, birthday, successPopupMessage, inprogressStatus, address, fullAddress, issueDate,
            issuePlace, issueNumber, imageFront, imageBack;
    WebDriver                driver;
    BasePageObject           basePage;
    AccountPageObject        accountPage;
    ProfilePageObject        profilePage;
    LoginAdminPageObject     loginAdminPage;
    BaseAdminPageObject      baseAdminPage;
    CustomerPageObject       customerAdminPage;
    CustomerDetailPageObject customerDetailAdminPage;
    DataHelper               data;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Verify account")
    public void beforeTest(String browserName, String appUrl) {
        driver   = Common_01_Register.driver;
        data     = DataHelper.getData();
        basePage = getBasePageObject(driver);
        basePage.verifyLoginButtonIsDisplayed();

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

        basePage.loginToAnAccount(Common_01_Register.phone, Common_01_Register.password)
                .verifyAlertMessageEqualTo("Đăng nhập thành công")
                .verifyLoginButtonIsDisappeared();

        accountPage = getAccountPageObject(driver);
        accountPage.navigateToPage().clickToVerifyButton();

        profilePage = getProfilePageObject(driver);
        profilePage.clickToBeginVerifyButton()
                   .inputToVerifyNameTextbox(Common_01_Register.name)
                   .chooseGenderDropdown(gender)
                   .inputToAddressTextbox(address)
                   .inputToFullAddressTextbox(fullAddress)
                   .inputToIssuePlaceTextbox(issuePlace)
                   .inputToIssueNumberTextbox(issueNumber)
                   .uploadFrontIssueImage(imageFront)
                   .uploadBackIssueImage(imageBack)
                   .verifyVerifySubmitBtnEnabled()
                   .clickToVerifySubmitButton()
                   .verifyVerifyPopupTitleEqualTo(successPopupMessage)
                   .clickToVerifyPopupDoneButton()
                   .verifyStatusProfileEqualTo(inprogressStatus);

        loginAdminPage = PageGeneratorManager.HouzeInvest.getLoginAdminPageObject(driver);
        loginAdminPage.navigateToPage()
                      .inputToUsernameTextbox(GlobalConstants.USERNAME_ADMIN_HI)
                      .inputToPasswordTextbox(GlobalConstants.PASSWORD_ADMIN_HI)
                      .clickToLoginButton();

        baseAdminPage = getBaseAdminPageObject(driver);
        baseAdminPage.clickToNotificationButton();

        baseAdminPage.clickToEKYCLink();
        customerAdminPage = getCustomerAdminPage(driver);
        customerAdminPage.clickToDynamicCustomerName(Common_01_Register.name);

        customerDetailAdminPage = getCustomerDetailAdminPage(driver);
        customerDetailAdminPage.clickToApproveButton().clickToVerifySubmitButton();
        try {
            customerDetailAdminPage = getCustomerDetailAdminPage(driver);
            customerDetailAdminPage.verifyApproveBtnIsDisappeared().verifyRejectBtnIsDisappeared();
        } catch (StaleElementReferenceException e) {
            customerDetailAdminPage = getCustomerDetailAdminPage(driver);
            customerDetailAdminPage.verifyApproveBtnIsDisappeared().verifyRejectBtnIsDisappeared();
        }
        customerDetailAdminPage.clickToDynamicBreadcrumbs("Danh sách khách hàng");

        customerAdminPage = getCustomerAdminPage(driver);
        customerAdminPage.verifyCustomerStatusByDynamicName(Common_01_Register.name, "Đã xác thực");

        accountPage = getAccountPageObject(driver);
        accountPage.navigateToPage().verifyStatusEqualTo("Đã xác thực").verifyDepositBtnIsDisplayed();

        profilePage = getProfilePageObject(driver);
        profilePage.navigateToPage().verifyStatusProfileEqualTo("Xác thực thành công");
    }

    @AfterTest(alwaysRun = true, description = "Close browser")
    public void afterTest() {
        closeBrowserAndDriver(driver);
    }
}
