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
@Story("Delete")
public class Test_Wallet_02_Delete_Bank extends AbstractTest {
    WebDriver        driver;
    DataHelper       data;
    BasePageObject   basePage;
    WalletPageObject walletPage;
    String           accountNumber, deleteBankSuccess;
    int otp, invalidOTP;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Login with a verified account")
    public void beforeClass(String browserName, String appUrl) {
        data              = DataHelper.getData();
        accountNumber     = data.getBankAccount();
        otp               = 123456;
        invalidOTP        = 654321;
        deleteBankSuccess = "Xoá thành công";

        driver   = getBrowserDriver(browserName, appUrl);
        basePage = getBasePageObject(driver);
        basePage.loginToAnAccount(Common_01_Register.phone, Common_01_Register.password)
                .verifyAlertMessageEqualTo("Đăng nhập thành công")
                .verifyLoginButtonIsDisappeared();
    }

    @BeforeMethod(description = "Open Delete bank form")
    public void beforeMethod() {
        walletPage = getWalletPage(driver);
        walletPage.navigateToPage().clickToDeleteBankBtn().verifyDeleteBankFormIsDisplayed();
    }

    @AfterClass(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Delete Bank 01: Click back")
    public void TC_01_Click_Back_In_Delete_Form() {
        walletPage.clickToDeleteBankBackBtn()
                  .verifyDeleteBankFormIsDisappeared()
                  .verifyBankInfoEqualTo(Common_04_Create_Bank.bankName, Common_01_Register.name,
                                         Common_04_Create_Bank.bankAccount);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Delete Bank 02: Delete bank")
    public void TC_02_Click_Back_In_Delete_Form() {
        walletPage.clickToDeleteBankSubmitBtn();

        basePage = getBasePageObject(driver);
        basePage.verifyAlertMessageEqualTo(deleteBankSuccess);

        walletPage = getWalletPage(driver);
        walletPage.verifyBankIsDeleted();
    }
}
