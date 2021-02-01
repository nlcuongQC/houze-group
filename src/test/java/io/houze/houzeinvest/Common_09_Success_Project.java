package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import commons.Functions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houzeinvest.admin.LoginAdminPageObject;
import pageobjects.houzeinvest.admin.PropertyDetailPageObject;
import pageobjects.houzeinvest.admin.PropertyPageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

public class Common_09_Success_Project extends AbstractTest {
    String     prjName;
    WebDriver  driver;
    DataHelper data;
    Functions  functions;

    PropertyDetailPageObject propertyDetailPage;
    LoginAdminPageObject     loginAdminPage;
    PropertyPageObject       propertyPage;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Change status of property to success")
    public void beforeTest(String browserName, String appUrl) {
        driver    = Common_06_Create_Project.driver;
        data      = DataHelper.getData();
        functions = Functions.getFunctions(driver);

        prjName = Common_06_Create_Project.prjName;

        loginAdminPage = getLoginAdminPage(driver);
        loginAdminPage.navigateToPage();

        propertyPage = getPropertyPage(driver);
        propertyPage.openPropertyDetail(prjName);

        propertyDetailPage = getPropertyDetailPage(driver);
        propertyDetailPage.successProject();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
