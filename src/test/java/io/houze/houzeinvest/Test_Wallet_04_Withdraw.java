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

import static commons.PageGeneratorManager.HouzeInvest.*;

@Epic("Wallet")
@Story("Withdraw")
public class Test_Wallet_04_Withdraw extends AbstractTest {
    WebDriver  driver;
    DataHelper data;

    BasePageObject             basePage;
    WalletPageObject           walletPage;
    LoginAdminPageObject       loginAdminPage;
    BaseAdminPageObject        baseAdminPage;
    TransactionOrderPageObject transactionOrderPage;

    String accountNumber, withdrawAmount, depositAmount, invalidWithdrawAmount, withdrawBank, depositBank, customerName;
    int otp, invalidOTP;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Login with a verified account")
    public void beforeClass(String browserName, String appUrl) {
        data                  = DataHelper.getData();
        accountNumber         = data.getBankAccount();
        otp                   = 123456;
        invalidOTP            = 654321;
        withdrawAmount        = "1000000";
        depositAmount         = "20000000";
        invalidWithdrawAmount = "100000000";
        depositBank           = "TECHCOMBANK";
        withdrawBank          = "NH Á Châu - ACB";
        customerName          = Common_01_Register.name;

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
    @Test(description = "Withdraw 01: Withdraw with blank form")
    public void TC_01_Withdraw_With_Blank_Form() {
        walletPage.verifyCreateWithdrawOrderBtnIsDisabled();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Withdraw 02: Withdraw without choosing bank")
    public void TC_02_Withdraw_Without_Choosing_Bank() {
        walletPage.verifyCreateWithdrawOrderBtnIsDisabled()
                  .inputToWithdrawTxtbx(withdrawAmount)
                  .verifyCreateWithdrawOrderBtnIsDisabled();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Withdraw 03: Withdraw with the money amount larger than current balance")
    public void TC_03_Withdraw_Larger_Than_Current() {
        walletPage = getWalletPage(driver);
        walletPage.inputToWithdrawTxtbx(invalidWithdrawAmount)
                  .selectWithdrawBank()
                  .clickToCreateWithdrawBtn()
                  .clickToApproveCreateWithdrawBtn();

        basePage = getBasePageObject(driver);
        basePage.verifyAlertMessageEqualTo("Số dư tài khoản không đủ để thực hiện rút tiền.");

        walletPage = getWalletPage(driver);
        walletPage.verifyCreateWithdrawOrderApproveBtnIsNotDisappeared();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Withdraw 04: Withdraw with the money amount smaller than current balance")
    public void TC_04_Withdraw_Valid_Data() {
        walletPage = getWalletPage(driver);
        walletPage.inputToWithdrawTxtbx(withdrawAmount)
                  .selectWithdrawBank()
                  .clickToCreateWithdrawBtn()
                  .clickToApproveCreateWithdrawBtn();

        basePage = getBasePageObject(driver);
        basePage.verifyAlertMessageEqualTo("Tạo lệnh thành công");

        walletPage = getWalletPage(driver);
        walletPage.verifyCreateWithdrawOrderApproveBtnIsDisappeared()
                  .verifyWithdrawInfoAmountEqualTo(withdrawAmount)
                  .navigateToPage()
                  .verifyWithdrawProcessingAmountEqualTo(withdrawAmount)
                  .verifyTotalBalance();

        loginAdminPage = getLoginAdminPageObject(driver);
        loginAdminPage.navigateToPage().loginToAdmin();

        baseAdminPage = getBaseAdminPageObject(driver);
        baseAdminPage.clickToNotificationButton().clickToTransactionLink();

        transactionOrderPage = getTransactionOrderPage(driver);
        transactionOrderPage.clickToDynamicActionBtn(customerName)
                            .clickToApproveOrder()
                            .clickToDialogApproveBtn()
                            .verifyDialogApproveIsDisappeared()
                            .verifyDynamicCustomerIsDisappeared(customerName);

        walletPage.navigateToPage().verifyWithdrawProcessingAmountEqualTo("0").verifyTotalBalance();
    }
}
