package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.WalletPageObject;

import static commons.PageGeneratorManager.HouzeInvest.getBasePageObject;
import static commons.PageGeneratorManager.HouzeInvest.getWalletPage;

@Epic("Wallet")
@Feature("Bank")
@Story("Create")
public class Test_Wallet_01_Create_Bank extends AbstractTest {
    WebDriver  driver;
    DataHelper data;

    BasePageObject   basePage;
    WalletPageObject walletPage;

    String accountNumber, otpValidate, alertBankSuccess, bankName;
    int otp, invalidOTP;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Login with a verified account")
    public void beforeClass(String browserName, String appUrl) {
        data             = DataHelper.getData();
        accountNumber    = data.getBankAccount();
        otp              = 123456;
        invalidOTP       = 654321;
        otpValidate      = "Mã OTP không hợp lệ.";
        alertBankSuccess = "Thêm tài khoản ngân hàng thành công";
        bankName         = "NH Á Châu - ACB";

        driver   = getBrowserDriver(browserName, appUrl);
        basePage = getBasePageObject(driver);
        basePage.loginToAnAccount(Common_01_Register.phone, Common_01_Register.password)
                .verifyAlertMessageEqualTo("Đăng nhập thành công")
                .verifyLoginButtonIsDisappeared();
    }

    @BeforeMethod(description = "Open form Add Bank")
    public void beforeMethod() {
        walletPage = getWalletPage(driver);
        walletPage.navigateToPage().clickToAddBankBtn().verifyAddBankAccountNumberIsDisplayed();
    }

    @AfterClass(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Create Bank 01: Create with blank form")
    public void TC_01_Create_With_Blank_Form() {
        walletPage.verifyAddBankNextBtnIsDisabled();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Create Bank 02: Create bank wrong OTP")
    public void TC_02_Create_With_Wrong_OTP() {
        walletPage.verifyAddBankNextBtnIsDisabled()
                  .inputToAddBankAccountNumberTxtbx(accountNumber)
                  .verifyAddBankNextBtnEnabled()
                  .clickToAddBankNextBtn()
                  .inputOTP(invalidOTP)
                  .verifyOTPValidateMessageEqualTo(otpValidate);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Create Bank 03: Create bank valid data")
    public void TC_03_Create_With_Valid_OTP() {
        walletPage.verifyAddBankNextBtnIsDisabled()
                  .verifyAddBankOwner(Common_01_Register.name)
                  .selectBank("NH Á Châu - ACB")
                  .inputToAddBankAccountNumberTxtbx(accountNumber)
                  .verifyAddBankNextBtnEnabled()
                  .clickToAddBankNextBtn()
                  .inputOTP(otp)
                  .verifyAlertMessageEqualTo(alertBankSuccess)
                  .verifyOTPFormIsDisappeared()
                  .verifyBankInfoEqualTo(bankName, Common_01_Register.name, accountNumber);
    }
}
