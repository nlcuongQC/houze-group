package io.houze.houzeinvest;

import commons.AbstractTest;
import commons.DataHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.houzeinvest.admin.BaseAdminPageObject;
import pageobjects.houzeinvest.admin.LoginAdminPageObject;
import pageobjects.houzeinvest.admin.PollPageObject;

import static commons.PageGeneratorManager.HouzeInvest.*;

@Feature("Poll")
@Story("Create")
public class Test_Poll_01_Create extends AbstractTest {
    WebDriver  driver;
    DataHelper data;

    String prjName, prjCode, startDay, endDay, resultDay, defaultVote;

    BaseAdminPageObject  baseAdminPage;
    LoginAdminPageObject loginAdminPage;
    PollPageObject       pollPage;

    @Parameters({"browser", "url"})
    @BeforeClass(groups = "smoke", description = "Login with a verified account")
    public void beforeClass(String browserName, String appUrl) {
        data   = DataHelper.getData();
        driver = getBrowserDriver(browserName, appUrl);

        prjName     = Common_14_Create_Approved_Project.prjName;
        prjCode     = Common_14_Create_Approved_Project.prjCode;
        startDay    = "1";
        endDay      = "20";
        resultDay   = "25";
        defaultVote = "Từ chối";

        loginAdminPage = getLoginAdminPage(driver);
        loginAdminPage.navigateToPage().loginToAdmin();
    }

    @BeforeMethod(description = "Open change password form")
    public void beforeMethod() {
        baseAdminPage = getBaseAdminPage(driver);
        baseAdminPage.clickToDynamicMenu("Biểu quyết");

        pollPage = getPollPage(driver);
        pollPage.clickToCreatePollBtn();
    }

    @Test
    public void TC_01_Create_Poll_With_Valid_Data() {
        pollPage = getPollPage(driver);
        pollPage.choosePrjCombobx(prjName)
                .chooseDynamicDate("start", startDay)
                .chooseDynamicDate("end", endDay)
                .chooseDynamicDate("result", resultDay)
                .chooseDefaultVoteDdl(defaultVote)
                .inputToDescriptionTxtbx("Test description")
                .clickToSaveBtn()
                .verifyDynamicPollIsCreated(prjCode);
    }

    @AfterClass
    public void afterTest() {
        pollPage = getPollPage(driver);
        pollPage.clickToDynamicActionBtn(prjCode)
                .clickToDynamicActionItem("Duyệt biểu quyết")
                .clickToSubmitBtn()
                .verifyDynamicPollStatusEqualsTo(prjCode, "Trong tiến trình")
                .clickToDynamicActionBtn(prjCode)
                .clickToDynamicActionItem("Hủy bỏ")
                .clickToSubmitBtn()
                .verifyDynamicPollStatusEqualsTo(prjCode, "Đã hủy");
    }

    @AfterTest(alwaysRun = true, description = "Close Browser")
    public void afterClass() {
        closeBrowserAndDriver(driver);
    }
}
