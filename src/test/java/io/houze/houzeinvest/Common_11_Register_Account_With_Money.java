package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import commons.Functions;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houzeinvest.admin.*;
import pageobjects.houzeinvest.investor.AccountPageObject;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.ProfilePageObject;
import pageobjects.houzeinvest.investor.WalletPageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

public class Common_11_Register_Account_With_Money extends AbstractTest {
    public static String password;
    public static String name;
    public static String phone;
    public static String email;
    public static String address;
    public static String fullAddress;
    public static String amount;

    public static WebDriver driver;

    BasePageObject             basePage;
    AccountPageObject          accountPage;
    ProfilePageObject          profilePage;
    LoginAdminPageObject       loginAdminPage;
    BaseAdminPageObject        baseAdminPage;
    CustomerPageObject         customerAdminPage;
    CustomerDetailPageObject   customerDetailAdminPage;
    WalletPageObject           walletPage;
    TransactionOrderPageObject transactionOrderPage;

    DataHelper data;
    Functions  functions;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Register an account")
    public void beforeTest(String browserName, String appUrl) {
        driver    = getBrowserDriver(browserName, appUrl);
        data      = DataHelper.getData();
        functions = Functions.getFunctions(driver);

        name        = data.getFullname();
        phone       = data.getPhone();
        email       = data.getEmail();
        password    = "123456";
        address     = data.getAddress();
        fullAddress = data.getAddress();
        amount      = "7000000";

        //Register new account and login
        basePage = getBasePageObject(driver);
        basePage.verifyLoginButtonIsDisplayed()
                .registerAccount(name, phone, email, password)
                .loginToAnAccount(phone, password);

        accountPage = getAccountPageObject(driver);
        accountPage.navigateToPage().clickToVerifyButton();

        //Request Ekyc
        profilePage = getProfilePageObject(driver);
        profilePage.requestEkyc(name, address, fullAddress);

        loginAdminPage = getLoginAdminPage(driver);
        loginAdminPage.navigateToPage().loginToAdmin();

        baseAdminPage = getBaseAdminPage(driver);
        baseAdminPage.clickToNotificationButton();

        baseAdminPage.clickToEKYCLink();
        customerAdminPage = getCustomerAdminPage(driver);
        customerAdminPage.clickToDynamicCustomerName(name);

        //Approve Ekyc
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
        customerAdminPage.verifyCustomerStatusByDynamicName(name, "Đã xác thực");

        //Deposit
        walletPage = getWalletPage(driver);
        walletPage.navigateToPage().createDepositOrder("TECHCOMBANK", amount);

        loginAdminPage = getLoginAdminPage(driver);
        loginAdminPage.navigateToPage();

        baseAdminPage = getBaseAdminPage(driver);
        baseAdminPage.clickToNotificationButton().clickToTransactionLink();

        transactionOrderPage = getTransactionOrderPage(driver);
        transactionOrderPage.clickToDynamicActionBtn(name)
                            .clickToApproveOrder()
                            .clickToDialogApproveBtn()
                            .verifyDynamicCustomerIsDisappeared(name);
    }

    @AfterTest(alwaysRun = true, description = "Close browsers")
    public void afterTest() {
        closeBrowserAndDriver(driver);
    }
}
