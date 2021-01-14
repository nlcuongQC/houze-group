package io.houze.houzeinvest;

import commons.AbstractTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.houzeinvest.investor.*;

import static commons.PageGeneratorManager.HouzeInvest.*;

@Epic("Invest")
public class Test_Invest_01_Invest extends AbstractTest {
    WebDriver driver;

    BasePageObject          basePage;
    HomePageObject          homePage;
    ProjectPageObject       projectPage;
    ProjectDetailPageObject projectDetailPage;
    CheckoutPageObject      checkoutPage;
    PortfolioPageObject     portfolioPage;

    String projectName, phone, password, otp, invalidOTP;
    int profit, hybrid, fixed;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Open HI Page")
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);

        basePage = getBasePageObject(driver);
        basePage.verifyLoginButtonIsDisplayed();

        projectName = "Kỳ Co Gateway 3";
        profit      = 2;
        hybrid      = 3;
        fixed       = 4;
        phone       = "0932228888";
        password    = "123456";
        invalidOTP  = "111111";
        otp         = "123456";
    }

    @BeforeMethod(description = "Go to Homepage")
    public void beforeMethod() {
        homePage = getHomePageObject(driver);
        homePage.navigateToHomepage();
    }

    @AfterClass(alwaysRun = true, description = "Close browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Invest 01: Without login")
    public void TC_01_Invest_Without_Login() {
        homePage = getHomePageObject(driver);
        homePage.clickToInvestNowButton();

        projectPage = getProjectPageObject(driver);
        projectPage.verifyProjectsAreDisplayed().clickToProject(projectName);

        projectDetailPage = getProjectDetailPageObject(driver);
        projectDetailPage.verifyInvestNowButtonIsDisplayed().clickInvestNowButton();

        checkoutPage = getCheckoutPageObject(driver);
        checkoutPage.verifyRequestLoginMessageIsDisplayed();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Invest 02: With Login")
    public void TC_02_Invest_With_Login() {
        basePage = getBasePageObject(driver);
        basePage.loginToAnAccount(phone, password)
                .verifyAlertMessageEqualTo("Đăng nhập thành công")
                .verifyLoginButtonIsDisappeared();

        homePage = getHomePageObject(driver);
        homePage.clickToInvestNowButton();

        projectPage = getProjectPageObject(driver);
        projectPage.verifyProjectsAreDisplayed();
        projectPage.clickToProject(projectName);

        projectDetailPage = getProjectDetailPageObject(driver);
        projectDetailPage.verifyInvestNowButtonIsDisplayed().clickInvestNowButton();

        checkoutPage = getCheckoutPageObject(driver);
        checkoutPage.verifyNextButtonIsDisabled()
                    .inputToDynamicItemTypeTextbox(String.valueOf(profit), "Gói linh hoạt")
                    .inputToDynamicItemTypeTextbox(String.valueOf(hybrid), "Gói kết hợp")
                    .inputToDynamicItemTypeTextbox(String.valueOf(fixed), "Gói cố định");

        int profitPrice = checkoutPage.getDynamicPrice("Gói linh hoạt");
        int hybridPrice = checkoutPage.getDynamicPrice("Gói kết hợp");
        int fixedPrice  = checkoutPage.getDynamicPrice("Gói cố định");
        int totalPrice  = profitPrice + hybridPrice + fixedPrice;
        int totalItems  = profit + hybrid + fixed;

        checkoutPage.verifyTotalItemsEqualTo(totalItems)
                    .verifyTotalPriceEqualTo(totalPrice)
                    .clickToNextButton()
                    .verifyDynamicItemAmount("Gói linh hoạt", profit)
                    .verifyDynamicItemAmount("Gói kết hợp", hybrid)
                    .verifyDynamicItemAmount("Gói cố định", fixed)
                    .verifyDynamicItemPrice("Gói linh hoạt", profitPrice)
                    .verifyDynamicItemPrice("Gói kết hợp", hybridPrice)
                    .verifyDynamicItemPrice("Gói cố định", fixedPrice)
                    .verifyTotalItemsEqualTo(totalItems)
                    .verifyTotalPriceEqualTo(totalPrice)
                    .verifyNextButtonIsDisabled()
                    .checkToContractCheckbox()
                    .verifyNextButtonIsEnabled()
                    .clickToNextButton()
                    .verifySubmitButtonIsDisabled()
                    .inputOTP(invalidOTP)
                    .clickToSubmitButton()
                    .verifyFailedPopupIsDisplayed()
                    .clickToDoAgainButton()
                    .inputOTP(otp)
                    .clickToSubmitButton()
                    .verifySuccessfullyPopupIsDisplayed()
                    .clickToViewPortfolioButton();

        portfolioPage = getPortfolioPageObject(driver);
        portfolioPage.verifyDynamicProjectAmount(projectName, 3);
    }

}
