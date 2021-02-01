package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import commons.Functions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.houzeinvest.admin.PropertyDetailPageObject;

import static commons.PageGeneratorManager.HouzeInvest.getPropertyDetailPage;

public class Common_07_Approve_Project extends AbstractTest {
    WebDriver driver;
    String    prjName;
    DataHelper data;
    Functions  functions;

    PropertyDetailPageObject propertyDetailPage;

    @Parameters({"browser", "url"})
    @BeforeTest(groups = "smoke", description = "Create a property")
    public void beforeTest(String browserName, String appUrl) {
        driver    = Common_06_Create_Project.driver;
        data      = DataHelper.getData();
        functions = Functions.getFunctions(driver);

        prjName = Common_06_Create_Project.prjName;

        propertyDetailPage = getPropertyDetailPage(driver);
        propertyDetailPage.acceptPrj()
                          .verifyPropertyStatusEqualTo("Đang chờ duyệt")
                          .approvePrj()
                          .verifyPropertyStatusEqualTo("Đang gọi vốn");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
