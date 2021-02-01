package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import commons.Functions;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houzeinvest.admin.*;
import pageobjects.houzeinvest.investor.*;

import static commons.PageGeneratorManager.HouzeInvest.*;

public class Common_02_Register_Account_With_A_Post_Sell extends AbstractTest {
    public static String password;
    public static String name;
    public static String phone;
    public static String email;
    public static String address;
    public static String fullAddress;
    public static String amount;
    public static String prjName;
    public static String prjCode;

    public static int profit;
    public static int hybrid;
    public static int fixed;

    public static WebDriver driver;

    BasePageObject             basePage;
    AccountPageObject          accountPage;
    ProfilePageObject          profilePage;
    LoginAdminPageObject       loginAdminPage;
    BaseAdminPageObject        baseAdminPage;
    CustomerPageObject         customerAdminPage;
    CustomerDetailPageObject   customerDetailAdminPage;
    WalletPageObject           walletPage;
    TransactionOrderPageObject transactionOrderPage;
    PropertyPageObject         propertyPage;
    PropertyDetailPageObject   propertyDetailPage;
    HomePageObject             homePage;
    ProjectPageObject          projectPage;
    ProjectDetailPageObject    projectDetailPage;
    CheckoutPageObject         checkoutPage;
    TradingPageObject          tradingPage;
    TradingSellPageObject      tradingSellPage;

    DataHelper data;
    Functions  functions;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Register an account")
    public void beforeTest(String browserName, String appUrl) {
        driver    = getBrowserDriver(browserName, appUrl);
        data      = DataHelper.getData();
        functions = Functions.getFunctions(driver);

        name        = data.getFullname();
        phone       = data.getPhone();
        email       = data.getEmail();
        password    = "123456";
        address     = data.getAddress();
        fullAddress = data.getAddress();
        amount      = "1000000000";
        prjName     = data.getTitle();
        prjCode     = functions.getUpperCase(prjName) + "AT" + data.getNumber(999);
        profit      = 2;
        hybrid      = 2;
        fixed       = 2;

        //Register new account and login
        basePage = getBasePageObject(driver);
        basePage.verifyLoginButtonIsDisplayed()
                .registerAccount(name, phone, email, password)
                .loginToAnAccount(phone, password);

        accountPage = getAccountPageObject(driver);
        accountPage.navigateToPage().clickToVerifyButton();

        //Request Ekyc
        profilePage = getProfilePageObject(driver);
        profilePage.requestEkyc(name, address, fullAddress);

        loginAdminPage = getLoginAdminPage(driver);
        loginAdminPage.navigateToPage().loginToAdmin();

        baseAdminPage = getBaseAdminPage(driver);
        baseAdminPage.clickToNotificationButton();

        baseAdminPage.clickToEKYCLink();
        customerAdminPage = getCustomerAdminPage(driver);
        customerAdminPage.clickToDynamicCustomerName(name);

        //Approve Ekyc
        customerDetailAdminPage = getCustomerDetailAdminPage(driver);
        customerDetailAdminPage.clickToApproveButton().clickToVerifySubmitButton();
        try {
            customerDetailAdminPage = getCustomerDetailAdminPage(driver);
            customerDetailAdminPage.verifyApproveBtnIsDisappeared().verifyRejectBtnIsDisappeared();
        } catch (StaleElementReferenceException e) {
            customerDetailAdminPage = getCustomerDetailAdminPage(driver);
            customerDetailAdminPage.verifyApproveBtnIsDisappeared().verifyRejectBtnIsDisappeared();
        }
        customerDetailAdminPage.clickToDynamicBreadcrumbs("Danh sách khách hàng");

        customerAdminPage = getCustomerAdminPage(driver);
        customerAdminPage.verifyCustomerStatusByDynamicName(name, "Đã xác thực");

        //Create Project
        loginAdminPage = getLoginAdminPage(driver);
        loginAdminPage.navigateToPage();

        propertyPage = getPropertyPage(driver);
        propertyPage.clickToCreatePropertyBtn()
                    .verifyCreatePropertyFormIsDisplayed()
                    .createAPrj(prjName, prjCode)
                    .verifyCreatePropertyFormIsDisappeared()
                    .openPropertyDetail(prjName);

        propertyDetailPage = getPropertyDetailPage(driver);
        propertyDetailPage.addItemType("Cố định", "15")
                          .addItemType("Kết hợp", "15", "15")
                          .addItemType("Linh hoạt", "35")
                          .addAvatar()
                          .verifyAvatarIsUploaded()
                          .addImages()
                          .verifyImagesAreUploaded();

        //Approve project
        propertyDetailPage = getPropertyDetailPage(driver);
        propertyDetailPage.acceptPrj()
                          .verifyPropertyStatusEqualTo("Đang chờ duyệt")
                          .approvePrj()
                          .verifyPropertyStatusEqualTo("Đang gọi vốn");

        //Deposit
        walletPage = getWalletPage(driver);
        walletPage.navigateToPage().createDepositOrder("TECHCOMBANK", amount);

        loginAdminPage = getLoginAdminPage(driver);
        loginAdminPage.navigateToPage();

        baseAdminPage = getBaseAdminPage(driver);
        baseAdminPage.clickToNotificationButton().clickToTransactionLink();

        transactionOrderPage = getTransactionOrderPage(driver);
        transactionOrderPage.clickToDynamicActionBtn(name)
                            .clickToApproveOrder()
                            .clickToDialogApproveBtn()
                            .verifyDynamicCustomerIsDisappeared(name);

        //Invest prj
        homePage = getHomePageObject(driver);
        homePage.navigateToHomepage().clickToInvestNowButton();

        projectPage = getProjectPageObject(driver);
        projectPage.verifyProjectsAreDisplayed();
        projectPage.clickToProject(prjName);

        projectDetailPage = getProjectDetailPageObject(driver);
        projectDetailPage.verifyInvestNowButtonIsDisplayed().clickInvestNowButton();

        checkoutPage = getCheckoutPageObject(driver);
        checkoutPage.verifyNextButtonIsDisabled()
                    .inputToDynamicItemTypeTextbox(String.valueOf(profit), "Gói linh hoạt")
                    .inputToDynamicItemTypeTextbox(String.valueOf(hybrid), "Gói kết hợp")
                    .inputToDynamicItemTypeTextbox(String.valueOf(fixed), "Gói cố định")
                    .clickToNextButton()
                    .checkToContractCheckbox()
                    .clickToNextButton()
                    .inputOTP("123456")
                    .clickToSubmitButton()
                    .verifySuccessfullyPopupIsDisplayed();

        //Success prj
        loginAdminPage = getLoginAdminPage(driver);
        loginAdminPage.navigateToPage();

        propertyPage = getPropertyPage(driver);
        propertyPage.openPropertyDetail(prjName);

        propertyDetailPage = getPropertyDetailPage(driver);
        propertyDetailPage.successProject();

        //Post sell
        tradingPage = getTradingPage(driver);
        tradingPage.navigateToPage().verifyPostBtnTxtIsChangedTo("Bán").clickToPostBtn();

        tradingSellPage = getTradingSellPage(driver);
        tradingSellPage.postSell(prjName, "Gói linh hoạt", String.valueOf(profit), "5000000");
    }

    @AfterTest(alwaysRun = true, description = "Close browsers")
    public void afterTest() {
        closeBrowserAndDriver(driver);
    }
}
