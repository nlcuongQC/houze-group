package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houzeinvest.investor.WalletPageObject;

import static commons.PageGeneratorManager.HouzeInvest.getWalletPage;

public class Common_03_Create_Bank extends AbstractTest {
    public static String bankAccount, bankName;
    WebDriver        driver;
    DataHelper       data;
    WalletPageObject walletPage;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Create a bank")
    public void beforeTest(String browserName, String appUrl) {
        driver = Common_01_Register.driver;
        data   = DataHelper.getData();

        bankAccount = data.getBankAccount();
        bankName    = "NH Á Châu - ACB";

        walletPage = getWalletPage(driver);
        walletPage.navigateToPage().createABank(bankName, bankAccount);
    }

    @AfterTest(alwaysRun = true, description = "Close browser")
    public void afterTest() {
        closeBrowserAndDriver(driver);
    }
}
