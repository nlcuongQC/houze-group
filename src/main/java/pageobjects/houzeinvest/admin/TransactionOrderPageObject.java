package pageobjects.houzeinvest.admin;

import commons.AbstractPage;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageuis.houzeinvest.admin.TransactionOrderPageUI;

import static pageuis.houzeinvest.admin.TransactionOrderPageUI.DIALOG_APPROVE_BTN;
import static pageuis.houzeinvest.admin.TransactionOrderPageUI.DYNAMIC_ACTION_BTN;

public class TransactionOrderPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public TransactionOrderPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    @Step("Click to action button of Customer: {name}")
    public TransactionOrderPageObject clickToDynamicActionBtn(String name) {
        waitElementClickable(driver, DYNAMIC_ACTION_BTN, name);
        clickToElement(driver, DYNAMIC_ACTION_BTN, name);
        return this;
    }

    @Step("Click Approve order")
    public TransactionOrderPageObject clickToApproveOrder() {
        waitElementClickable(driver, TransactionOrderPageUI.APPROVE_ORDER_BTN);
        clickToElement(driver, TransactionOrderPageUI.APPROVE_ORDER_BTN);
        return this;
    }

    @Step("Click approve button in dialog")
    public TransactionOrderPageObject clickToDialogApproveBtn() {
        waitElementClickable(driver, DIALOG_APPROVE_BTN);
        clickToElement(driver, DIALOG_APPROVE_BTN);
        return this;
    }

    @Step("Verify {name} customer is disappeared")
    public void verifyDynamicCustomerIsDisappeared(String name) {
        waitElementInvisible(driver, DYNAMIC_ACTION_BTN, name);
        verify.verifyTrue(isElementUndisplayed(driver, DYNAMIC_ACTION_BTN, name));
    }

    public TransactionOrderPageObject verifyDialogApproveIsDisappeared() {
        waitElementInvisible(driver, DIALOG_APPROVE_BTN);
        verify.verifyTrue(isElementUndisplayed(driver, DIALOG_APPROVE_BTN));
        return this;
    }
}
