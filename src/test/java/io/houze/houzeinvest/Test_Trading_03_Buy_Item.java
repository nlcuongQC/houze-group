package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.houzeinvest.admin.*;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.CheckoutPageObject;
import pageobjects.houzeinvest.investor.PortfolioPageObject;
import pageobjects.houzeinvest.investor.TradingPageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

@Epic("Trading")
@Feature("Post")
@Story("Sell")
public class Test_Trading_03_Buy_Item extends AbstractTest {
    WebDriver  driver;
    DataHelper data;
    String     prjName, itemCode;
    int buyAmount;

    BasePageObject           basePage;
    TradingPageObject        tradingPage;
    CheckoutPageObject       checkoutPage;
    PortfolioPageObject      portfolioPage;
    LoginAdminPageObject     loginAdminPage;
    PropertyPageObject       propertyPage;
    PropertyDetailPageObject propertyDetailPage;
    BaseAdminPageObject      baseAdminPage;
    PaymentOrderPageObject   paymentOrderPage;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Login with an invested account")
    public void beforeClass(String browserName, String appUrl) {
        data   = DataHelper.getData();
        driver = getBrowserDriver(browserName, appUrl);

        prjName   = Common_02_Register_Account_With_A_Post_Sell.prjName;
        itemCode  = Common_02_Register_Account_With_A_Post_Sell.prjCode + ".P";
        buyAmount = 1;

        basePage = getBasePageObject(driver);
        basePage.loginToAnAccount(Common_11_Register_Account_With_Money.phone,
                                  Common_11_Register_Account_With_Money.password);
    }

    @BeforeMethod(description = "Open Buy item checkout page")
    public void beforeMethod() {
        tradingPage = getTradingPage(driver);
        tradingPage.navigateToPage().clickToDynamicBuyNowBtn(prjName);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Trading Buy 01: Buy with 0 item")
    public void TC_01_Buy_With_0_Item() {
        checkoutPage = getCheckoutPageObject(driver);
        checkoutPage.verifyNextButtonIsDisabled();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Trading Buy 02: Buy not enough money")
    public void TC_02_Buy_Not_Enough_Money() {
        checkoutPage = getCheckoutPageObject(driver);
        checkoutPage.verifyNextButtonIsDisabled()
                    .inputToDynamicItemTypeTextbox("2", "Gói linh hoạt")
                    .clickToNextButton()
                    .verifyNotEnoughPopupIsDisplayed();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Trading Buy 03: Buy with valid data")
    public void TC_03_Buy_With_Valid() {
        checkoutPage = getCheckoutPageObject(driver);
        checkoutPage.verifyNextButtonIsDisabled()
                    .inputToDynamicItemTypeTextbox(String.valueOf(buyAmount), "Gói linh hoạt");

        int profitPrice = checkoutPage.getDynamicPrice("Gói linh hoạt");
        checkoutPage.verifyTotalItemsEqualTo(1)
                    .verifyTotalPriceEqualTo(profitPrice)
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

    @AfterTest
    public void afterTest() {
        loginAdminPage = getLoginAdminPage(driver);
        loginAdminPage.navigateToPage().loginToAdmin();

        propertyPage = getPropertyPage(driver);
        propertyPage.openPropertyDetail(prjName);

        propertyDetailPage = getPropertyDetailPage(driver);
        propertyDetailPage.finishPrj().verifyPropertyStatusEqualTo("Hoàn tất đầu tư");

        baseAdminPage = getBaseAdminPage(driver);
        baseAdminPage.clickToDynamicMenu("Lệnh thanh toán");

        paymentOrderPage = getPaymentOrderPage(driver);
        paymentOrderPage.createPaymentOrder("Hoàn vốn", itemCode + ".P", "100", "30")
                        .createPaymentOrder("Hoàn vốn", itemCode + ".H", "100", "30")
                        .createPaymentOrder("Hoàn vốn", itemCode + ".F", "100", "30");

    }

    @AfterClass(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
