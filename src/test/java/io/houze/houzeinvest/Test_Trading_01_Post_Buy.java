package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.TradingBuyPageObject;
import pageobjects.houzeinvest.investor.TradingPageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

@Epic("Trading")
@Feature("Post")
@Story("Buy")
public class Test_Trading_01_Post_Buy extends AbstractTest {
    WebDriver  driver;
    DataHelper data;
    String project;

    BasePageObject       basePage;
    TradingPageObject    tradingPage;
    TradingBuyPageObject tradingBuyPage;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Login with a verified account")
    public void beforeClass(String browserName, String appUrl) {
        data = DataHelper.getData();
        project = "Economix";

        driver   = getBrowserDriver(browserName, appUrl);
        basePage = getBasePageObject(driver);
        basePage.verifyLoginButtonIsDisplayed()
                .loginToAnAccount(Common_01_Register.phone, Common_01_Register.password)
                .verifyAlertMessageEqualTo("Đăng nhập thành công")
                .verifyLoginButtonIsDisappeared();
    }

    @BeforeMethod(description = "Open Trading Buy page")
    public void beforeMethod() {
        tradingPage = getTradingPage(driver);
        tradingPage.navigateToPage()
                   .clickToDynamicChannelBtn("Mua")
                   .verifyPostBtnTxtIsChangedTo("Mua")
                   .clickToPostBtn();

        tradingBuyPage = getTradingBuyPage(driver);
        tradingBuyPage.verifyTradingBuyPageIsOpened();
    }

    @AfterClass(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Post Buy 01: Post buy without amount and price")
    public void TC_01_Post_Buy_Without_Amount_And_Price() {
        tradingBuyPage = getTradingBuyPage(driver);
        tradingBuyPage.choosePrj("Economix").chooseItemType("Gói kết hợp").verifyCreatePostBuyBtnIsDisabled();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Post Buy 02: Post buy without price")
    public void TC_02_Post_Buy_Without_Price() {
        tradingBuyPage = getTradingBuyPage(driver);
        tradingBuyPage.choosePrj("Amelie Villa")
                      .chooseItemType("Gói linh hoạt")
                      .inputToItemAmountTxtbx("5")
                      .verifyCreatePostBuyBtnIsDisabled();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Post Buy 03: Post buy with price more than current balance")
    public void TC_03_Post_Buy_With_Invalid_Price() {
        tradingBuyPage = getTradingBuyPage(driver);
        tradingBuyPage.choosePrj("Amelie Villa")
                      .chooseItemType("Gói linh hoạt")
                      .inputToItemAmountTxtbx("5")
                      .inputToExpectedPrice("5000000")
                      .verifyCreatePostBuyBtnIsEnabled()
                      .clickToCreatePostBuyBtn()
                      .verifyDynamicPopupIsDisplayed("thất bại");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Post Buy 04: Post buy with valid data")
    public void TC_04_Post_Buy_With_Valid_Data() {
        tradingBuyPage = getTradingBuyPage(driver);
        tradingBuyPage.choosePrj("Economix")
                      .chooseItemType("Gói linh hoạt")
                      .inputToItemAmountTxtbx("1")
                      .inputToExpectedPrice("5000000")
                      .verifyCreatePostBuyBtnIsEnabled()
                      .clickToCreatePostBuyBtn()
                      .verifyDynamicPopupIsDisplayed("thành công");
    }
}
