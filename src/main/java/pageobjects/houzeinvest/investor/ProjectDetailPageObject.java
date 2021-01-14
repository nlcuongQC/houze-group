package pageobjects.houzeinvest.investor;

import commons.AbstractPage;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;
import static pageuis.houzeinvest.investor.ProjectDetailPageUI.INVEST_NOW_BUTTON;
import static pageuis.houzeinvest.investor.ProjectDetailPageUI.PROJECT_INFO_SECTION;

public class ProjectDetailPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public ProjectDetailPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    private boolean isInvestNowButtonDisplayed() {
        waitElementVisible(driver, INVEST_NOW_BUTTON);
        return isElementDisplayed(driver, INVEST_NOW_BUTTON);
    }

    @Step("Click Invest now button")
    public void clickInvestNowButton() {
        scrollToElement(driver, INVEST_NOW_BUTTON);
        waitElementClickable(driver, INVEST_NOW_BUTTON);
        clickToElementByJS(driver, INVEST_NOW_BUTTON);
    }

    @Step("Verify Invest now button is displayed")
    public ProjectDetailPageObject verifyInvestNowButtonIsDisplayed() {
        scrollToElement(driver, INVEST_NOW_BUTTON);
        waitElementVisible(driver, INVEST_NOW_BUTTON);
        verify.verifyTrue(isElementDisplayed(driver, INVEST_NOW_BUTTON));
        return this;
    }
}
