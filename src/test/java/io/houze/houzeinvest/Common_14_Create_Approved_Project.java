package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import commons.Functions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houzeinvest.admin.LoginAdminPageObject;
import pageobjects.houzeinvest.admin.PropertyDetailPageObject;
import pageobjects.houzeinvest.admin.PropertyPageObject;
import pageobjects.houzeinvest.investor.BasePageObject;
import pageobjects.houzeinvest.investor.TradingBuyPageObject;
import pageobjects.houzeinvest.investor.TradingPageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

public class Common_14_Create_Approved_Project extends AbstractTest {
    static String prjName, prjCode;
    WebDriver  driver;
    DataHelper data;
    Functions  functions;

    LoginAdminPageObject     loginAdminPage;
    PropertyPageObject       propertyPage;
    PropertyDetailPageObject propertyDetailPage;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Create approved project")
    public void beforeTest(String browser, String url) {
        driver    = getBrowserDriver(browser, url);
        data      = DataHelper.getData();
        functions = Functions.getFunctions(driver);

        prjName = data.getTitle();
        prjCode = functions.getUpperCase(prjName) + "AT" + data.getNumber(999);

        loginAdminPage = getLoginAdminPage(driver);
        loginAdminPage.navigateToPage().loginToAdmin();

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
                          .verifyImagesAreUploaded()
                          .acceptPrj()
                          .verifyPropertyStatusEqualTo("Đang chờ duyệt")
                          .approvePrj()
                          .verifyPropertyStatusEqualTo("Đang gọi vốn");
    }

    @AfterTest(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
