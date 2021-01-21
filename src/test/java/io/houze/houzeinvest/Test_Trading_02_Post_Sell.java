package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.houzeinvest.admin.LoginAdminPageObject;
import pageobjects.houzeinvest.admin.PropertyDetailPageObject;
import pageobjects.houzeinvest.admin.PropertyPageObject;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.TradingPageObject;
import pageobjects.houzeinvest.investor.TradingSellPageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

@Epic("Trading")
@Feature("Post")
@Story("Sell")
public class Test_Trading_02_Post_Sell extends AbstractTest {
    WebDriver  driver;
    DataHelper data;
    String     prjName;

    BasePageObject           basePage;
    TradingPageObject        tradingPage;
    TradingSellPageObject    tradingSellPage;
    LoginAdminPageObject     loginAdminPage;
    PropertyPageObject       propertyPage;
    PropertyDetailPageObject propertyDetailPage;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Login with an invested account")
    public void beforeClass(String browserName, String appUrl) {
        data   = DataHelper.getData();
        driver = getBrowserDriver(browserName, appUrl);

        prjName = Common_05_Create_Project.prjName;

        basePage = getBasePageObject(driver);
        basePage.loginToAnAccount(Common_01_Register.phone, Common_01_Register.password);
    }

    @BeforeMethod(description = "Open Trading Sell page")
    public void beforeMethod() {
        tradingPage = getTradingPage(driver);
        tradingPage.navigateToPage().verifyPostBtnTxtIsChangedTo("Bán").clickToPostBtn();

        tradingSellPage = getTradingSellPage(driver);
        tradingSellPage.verifyTradingSellPageIsOpened();
    }

    @AfterClass(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        loginAdminPage = getLoginAdminPageObject(driver);
        loginAdminPage.navigateToPage().loginToAdmin();

        propertyPage = getPropertyPage(driver);
        propertyPage.openPropertyDetail(prjName);

        propertyDetailPage = getPropertyDetailPage(driver);
        propertyDetailPage.finishPrj().verifyPropertyStatusEqualTo("Hoàn tất đầu tư");
        closeBrowserAndDriver(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Post Sell 01: Post sell without amount and price")
    public void TC_01_Post_Sell_Without_Amount_And_Price() {
        tradingSellPage = getTradingSellPage(driver);
        tradingSellPage.choosePrj(prjName).chooseItemType("Gói kết hợp").verifyCreatePostSellBtnIsDisabled();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Post Sell 02: Post sell without price")
    public void TC_02_Post_Sell_Without_Price() {
        tradingSellPage = getTradingSellPage(driver);
        tradingSellPage.choosePrj(prjName)
                       .chooseItemType("Gói linh hoạt")
                       .inputToItemAmountTxtbx("5")
                       .verifyCreatePostSellBtnIsDisabled();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Post Sell 03: Post sell without amount")
    public void TC_03_Post_Sell_Without_Amount() {
        tradingSellPage = getTradingSellPage(driver);
        tradingSellPage.choosePrj(prjName)
                       .chooseItemType("Gói linh hoạt")
                       .inputToItemAmountTxtbx("5")
                       .verifyCreatePostSellBtnIsDisabled();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Post Sell 04: Post Sell with valid data")
    public void TC_04_Post_Buy_With_Valid_Data() {
        tradingSellPage = getTradingSellPage(driver);
        tradingSellPage.choosePrj(prjName)
                       .chooseItemType("Gói linh hoạt")
                       .inputToItemAmountTxtbx("1")
                       .inputToExpectedPrice("5000000")
                       .verifyCreatePostSellBtnIsEnabled()
                       .clickToCreatePostSellBtn()
                       .verifyDynamicPopupIsDisplayed("thành công");
    }
}
