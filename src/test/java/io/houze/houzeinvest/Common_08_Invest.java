package io.houze.houzeinvest;

import commons.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houzeinvest.investor.*;

import static commons.PageGeneratorManager.HouzeInvest.*;

public class Common_08_Invest extends AbstractTest {
    static int profit, hybrid, fixed;
    WebDriver driver;
    BasePageObject          basePage;
    HomePageObject          homePage;
    ProjectPageObject       projectPage;
    ProjectDetailPageObject projectDetailPage;
    CheckoutPageObject      checkoutPage;
    PortfolioPageObject     portfolioPage;
    String projectName, phone, password, otp, invalidOTP;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Invest a property")
    public void beforeTest(String browserName, String appUrl) {
        driver = Common_01_Register.driver;

        basePage = getBasePageObject(driver);
        basePage.verifyLoginButtonIsDisplayed();

        projectName = Common_06_Create_Project.prjName;
        profit      = 1;
        hybrid      = 1;
        fixed       = 1;
        phone       = Common_01_Register.phone;
        password    = Common_01_Register.password;
        invalidOTP  = "111111";
        otp         = "123456";

        homePage = getHomePageObject(driver);
        homePage.navigateToHomepage();

        basePage = getBasePageObject(driver);
        basePage.loginToAnAccount(phone, password);


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

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
