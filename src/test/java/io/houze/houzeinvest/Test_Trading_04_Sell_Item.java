package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.CheckoutPageObject;
import pageobjects.houzeinvest.investor.PortfolioPageObject;
import pageobjects.houzeinvest.investor.TradingPageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

@Epic("Trading")
@Feature("Post")
@Story("Sell")
public class Test_Trading_04_Sell_Item extends AbstractTest {
    WebDriver  driver;
    DataHelper data;
    String     prjName, itemCode;
    int buyAmount;

    BasePageObject      basePage;
    TradingPageObject   tradingPage;
    CheckoutPageObject  checkoutPage;
    PortfolioPageObject portfolioPage;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Login with an invested account")
    public void beforeClass(String browserName, String appUrl) {
        data   = DataHelper.getData();
        driver = getBrowserDriver(browserName, appUrl);

        prjName   = Common_12_Register_Account_Invested_Project.prjName;
        itemCode  = Common_12_Register_Account_Invested_Project.prjCode + ".P";
        buyAmount = 1;

        basePage = getBasePageObject(driver);
        basePage.loginToAnAccount(Common_12_Register_Account_Invested_Project.phone,
                                  Common_12_Register_Account_Invested_Project.password);
    }

    @BeforeMethod(description = "Open Sell item checkout page")
    public void beforeMethod() {
        tradingPage = getTradingPage(driver);
        tradingPage.navigateToPage().clickToDynamicChannelBtn("Mua").clickToDynamicBuyNowBtn(prjName);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Trading Sell 01: Sell with 0 item")
    public void TC_01_Sell_With_0_Item() {
        checkoutPage = getCheckoutPageObject(driver);
        checkoutPage.verifyNextButtonIsDisabled();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Trading Sell 02: Sell with valid data")
    public void TC_02_Sell_With_Valid() {
        checkoutPage = getCheckoutPageObject(driver);
        checkoutPage.verifyNextButtonIsDisabled()
                    .inputToDynamicItemTypeTextbox(String.valueOf(buyAmount), "Gói linh hoạt");

        int profitPrice = checkoutPage.getDynamicPrice("Gói linh hoạt");
        checkoutPage.verifyTotalItemsEqualTo(1)
                    .clickToNextButton()
                    .verifyDynamicItemAmount("Gói linh hoạt", buyAmount)
                    .verifyDynamicItemPrice("Gói linh hoạt", profitPrice)
                    .verifyNextButtonIsDisabled()
                    .checkToContractCheckbox()
                    .verifyNextButtonIsEnabled()
                    .clickToNextButton()
                    .inputOTP("123456")
                    .clickToSubmitButton()
                    .verifySuccessfullyPopupIsDisplayed()
                    .clickToViewPortfolioButton();

        portfolioPage = getPortfolioPageObject(driver);
        portfolioPage.verifyDynamicProjectAmount(prjName, 1);
    }

    @AfterClass(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
