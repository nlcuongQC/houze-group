package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.houzeinvest.admin.BaseAdminPageObject;
import pageobjects.houzeinvest.admin.LoginAdminPageObject;
import pageobjects.houzeinvest.admin.TransactionOrderPageObject;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.WalletPageObject;

import static commons.Functions.convertMoneyAmount;
import static commons.PageGeneratorManager.HouzeInvest.*;

@Epic("Wallet")
@Story("Deposit")
public class Test_Wallet_03_Deposit extends AbstractTest {
    WebDriver  driver;
    DataHelper data;

    BasePageObject             basePage;
    WalletPageObject           walletPage;
    LoginAdminPageObject       loginAdminPage;
    BaseAdminPageObject        baseAdminPage;
    TransactionOrderPageObject transactionOrderPage;

    String accountNumber, depositAmount, depositBank, customerName, oldAvailableBalance;
    int otp, invalidOTP;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Login with a verified account")
    public void beforeClass(String browserName, String appUrl) {
        data          = DataHelper.getData();
        accountNumber = data.getBankAccount();
        otp           = 123456;
        invalidOTP    = 654321;
        depositAmount = "10000000";
        depositBank   = "TECHCOMBANK";
        customerName  = Common_01_Register.name;

        driver   = getBrowserDriver(browserName, appUrl);
        basePage = getBasePageObject(driver);
        basePage.loginToAnAccount(Common_01_Register.phone, Common_01_Register.password)
                .verifyAlertMessageEqualTo("Đăng nhập thành công")
                .verifyLoginButtonIsDisappeared();
    }

    @BeforeMethod(description = "Open Wallet page")
    public void beforeMethod() {
        walletPage = getWalletPage(driver);
        walletPage.navigateToPage().verifyWalletPageIsDisplayed();
    }

    @AfterClass(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Deposit 01: Deposit with blank form")
    public void TC_01_Deposit_With_Blank_Form() {
        walletPage.verifyCreateDepositOrderBtnIsDisabled();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Deposit 02: Deposit with valid data")
    public void TC_02_Deposit_With_Valid_Data() {
        oldAvailableBalance = walletPage.getAvailableBalance();
        walletPage.verifyCreateDepositOrderBtnIsDisabled()
                  .inputToDepositTxtbx(depositAmount)
                  .selectDepositBank(depositBank)
                  .clickToCreateDepositOrderBtn()
                  .verifyDepostitPopupIsDisplayed()
                  .verifyBankAccountNameDepositPopupEqualTo(depositBank)
                  .verifyBankAccountAmountDepositPopupEqualTo(convertMoneyAmount(depositAmount));

        loginAdminPage = getLoginAdminPage(driver);
        loginAdminPage.navigateToPage().loginToAdmin();

        baseAdminPage = getBaseAdminPage(driver);
        baseAdminPage.clickToNotificationButton().clickToTransactionLink();

        transactionOrderPage = getTransactionOrderPage(driver);
        transactionOrderPage.clickToDynamicActionBtn(customerName)
                            .clickToApproveOrder()
                            .clickToDialogApproveBtn()
                            .verifyDynamicCustomerIsDisappeared(customerName);

        walletPage = getWalletPage(driver);
        walletPage.navigateToPage().verifyAvailableBalance(oldAvailableBalance, depositAmount).verifyTotalBalance();
    }
}
