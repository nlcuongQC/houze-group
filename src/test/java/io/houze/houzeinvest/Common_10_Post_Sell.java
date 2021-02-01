package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import commons.Functions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houzeinvest.investor.TradingPageObject;
import pageobjects.houzeinvest.investor.TradingSellPageObject;

import static commons.PageGeneratorManager.HouzeInvest.getTradingPage;
import static commons.PageGeneratorManager.HouzeInvest.getTradingSellPage;

public class Common_10_Post_Sell extends AbstractTest {
    String     prjName;
    WebDriver  driver;
    DataHelper data;
    Functions  functions;

    TradingPageObject     tradingPage;
    TradingSellPageObject tradingSellPage;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Post sell")
    public void beforeTest(String browserName, String appUrl) {
        driver    = Common_06_Create_Project.driver;
        data      = DataHelper.getData();
        functions = Functions.getFunctions(driver);

        prjName = Common_06_Create_Project.prjName;

        tradingPage = getTradingPage(driver);
        tradingPage.navigateToPage().verifyPostBtnTxtIsChangedTo("Bán").clickToPostBtn();

        tradingSellPage = getTradingSellPage(driver);
        tradingSellPage.verifyTradingSellPageIsOpened()
                       .choosePrj(prjName)
                       .chooseItemType("Gói linh hoạt")
                       .inputToItemAmountTxtbx("1")
                       .inputToExpectedPrice("5000000")
                       .verifyCreatePostSellBtnIsEnabled()
                       .clickToCreatePostSellBtn()
                       .verifyDynamicPopupIsDisplayed("thành công");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
