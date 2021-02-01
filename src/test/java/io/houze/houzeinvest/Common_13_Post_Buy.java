package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import commons.Functions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.TradingBuyPageObject;
import pageobjects.houzeinvest.investor.TradingPageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

public class Common_13_Post_Buy extends AbstractTest {
    String     prjName;
    WebDriver  driver;
    DataHelper data;
    Functions  functions;

    BasePageObject       basePage;
    TradingPageObject    tradingPage;
    TradingBuyPageObject tradingBuyPage;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Post sell")
    public void beforeTest(String browser, String url) {
        driver    = getBrowserDriver(browser, url);
        data      = DataHelper.getData();
        functions = Functions.getFunctions(driver);

        prjName = Common_12_Register_Account_Invested_Project.prjName;

        basePage = getBasePageObject(driver);
        basePage.verifyLoginButtonIsDisplayed()
                .loginToAnAccount(Common_11_Register_Account_With_Money.phone,
                                  Common_11_Register_Account_With_Money.password);

        tradingPage = getTradingPage(driver);
        tradingPage.navigateToPage()
                   .clickToDynamicChannelBtn("Mua")
                   .verifyPostBtnTxtIsChangedTo("Mua")
                   .clickToPostBtn();

        tradingBuyPage = getTradingBuyPage(driver);
        tradingBuyPage.verifyTradingBuyPageIsOpened().postBuy(prjName, "Gói linh hoạt", "1", "5000000");
    }

    @AfterTest(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
