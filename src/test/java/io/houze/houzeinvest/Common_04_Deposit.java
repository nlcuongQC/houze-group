package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houzeinvest.admin.BaseAdminPageObject;
import pageobjects.houzeinvest.admin.LoginAdminPageObject;
import pageobjects.houzeinvest.admin.TransactionOrderPageObject;
import pageobjects.houzeinvest.investor.WalletPageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

public class Common_04_Deposit extends AbstractTest {
    public static String amount, bankName;
    WebDriver  driver;
    DataHelper data;

    WalletPageObject           walletPage;
    LoginAdminPageObject       loginAdminPage;
    BaseAdminPageObject        baseAdminPage;
    TransactionOrderPageObject transactionOrderPage;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Create a bank")
    public void beforeTest(String browserName, String appUrl) {
        driver = Common_01_Register.driver;
        data   = DataHelper.getData();

        amount   = "10000000";
        bankName = "TECHCOMBANK";

        walletPage = getWalletPage(driver);
        walletPage.navigateToPage().createDepositOrder(bankName, amount);

        loginAdminPage = getLoginAdminPageObject(driver);
        loginAdminPage.navigateToPage();

        baseAdminPage = getBaseAdminPageObject(driver);
        baseAdminPage.clickToNotificationButton().clickToTransactionLink();

        transactionOrderPage = getTransactionOrderPage(driver);
        transactionOrderPage.clickToDynamicActionBtn(Common_01_Register.name)
                            .clickToApproveOrder()
                            .clickToDialogApproveBtn()
                            .verifyDynamicCustomerIsDisappeared(Common_01_Register.name);
    }

    @AfterTest(alwaysRun = true, description = "Close browser")
    public void afterTest() {
        closeBrowserAndDriver(driver);
    }
}
