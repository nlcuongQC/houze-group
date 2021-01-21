package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import commons.Functions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.houzeinvest.admin.LoginAdminPageObject;
import pageobjects.houzeinvest.admin.PropertyDetailPageObject;
import pageobjects.houzeinvest.admin.PropertyPageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

public class Common_05_Create_Project extends AbstractTest {
    static String prjName, prjCode;
    static WebDriver driver;
    DataHelper data;
    Functions  functions;

    LoginAdminPageObject     loginAdminPage;
    PropertyPageObject       propertyPage;
    PropertyDetailPageObject propertyDetailPage;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Create a property")
    public void beforeTest(String browserName, String appUrl) {
        driver    = Common_01_Register.driver;
        data      = DataHelper.getData();
        functions = Functions.getFunctions(driver);

        prjName = data.getTitle();
        prjCode = functions.getUpperCase(prjName) + "AT" + data.getNumber(999);

        loginAdminPage = getLoginAdminPageObject(driver);
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
                          .addImages()
                          .verifyImagesAreUploaded();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
