package pageobjects.houzeinvest.admin;

import commons.AbstractPage;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static pageuis.houzeinvest.admin.CustomerDetailPageUI.*;

public class CustomerDetailPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public CustomerDetailPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    @Step("Click reject button")
    public CustomerDetailPageObject clickToRejectButton() {
        waitElementClickable(driver, REJECT_BUTTON);
        clickToElement(driver, REJECT_BUTTON);
        return this;
    }

    @Step("Click Approve button")
    public CustomerDetailPageObject clickToApproveButton() {
        waitElementClickable(driver, APPROVE_BUTTON);
        clickToElement(driver, APPROVE_BUTTON);
        return this;
    }

    @Step("Click Verify submit button")
    public void clickToVerifySubmitButton() {
        waitElementVisible(driver, VERIFY_SUBMIT_BUTTON);
        clickToElement(driver, VERIFY_SUBMIT_BUTTON);
    }

    @Step("Click breadcrumb menu {menu}")
    public void clickToDynamicBreadcrumbs(String menu) {
        waitElementClickable(driver, DYNAMIC_BREADCRUMBS, menu);
        clickToElement(driver, DYNAMIC_BREADCRUMBS, menu);
    }

    @Step("Refresh page")
    public void refreshPage() {
        refresh(driver);
    }

    @Step("Verify Approve button is disappeared")
    public CustomerDetailPageObject verifyApproveBtnIsDisappeared() {
        waitElementInvisible(driver, APPROVE_BUTTON);
        verify.verifyTrue(isElementUndisplayed(driver, APPROVE_BUTTON));
        return this;
    }

    @Step("Verify Reject button is disappeared")
    public void verifyRejectBtnIsDisappeared() {
        verify.verifyTrue(isElementUndisplayed(driver, REJECT_BUTTON));
    }
}
