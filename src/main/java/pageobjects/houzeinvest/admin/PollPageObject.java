package pageobjects.houzeinvest.admin;

import commons.AbstractPage;
import commons.VerifyHelper;
import org.openqa.selenium.WebDriver;

import static pageuis.houzeinvest.admin.PollPageUI.*;

public class PollPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public PollPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }


    public PollPageObject clickToCreatePollBtn() {
        waitElementClickable(driver, CREATE_POLL_BTN);
        clickToElement(driver, CREATE_POLL_BTN);
        verify.verifyTrue(isElementDisplayed(driver, CREATE_POLL_FORM));
        return this;
    }

    public PollPageObject choosePrjCombobx(String prj) {
        waitElementVisible(driver, PRJ_COMBOBX);
        sendkeyToElement(driver, PRJ_COMBOBX, prj);
        waitElementVisible(driver, DYNAMIC_PRJ_ITEM, prj);
        clickToElementByJS(driver, DYNAMIC_PRJ_ITEM, prj);
        verify.verifyEquals(getElementAttribute(driver, PRJ_COMBOBX, "value"), prj);
        return this;
    }

    public PollPageObject chooseDynamicDate(String dateName, String day) {
        clickToDynamicDatePickerBtn(dateName);
        clickToDynamicDay(day);
        clickToOkBtn();
        return this;
    }

    private void clickToOkBtn() {
        waitElementClickable(driver, OK_BTN);
        clickToElement(driver, OK_BTN);
    }

    private void clickToDynamicDay(String day) {
        waitElementClickable(driver, DYNAMIC_DAY, day);
        clickToElement(driver, DYNAMIC_DAY, day);
    }

    private void clickToDynamicDatePickerBtn(String dateName) {
        waitElementClickable(driver, DYNAMIC_DATEPICKER_BTN, dateName);
        clickToElement(driver, DYNAMIC_DATEPICKER_BTN, dateName);
    }

    public PollPageObject chooseDefaultVoteDdl(String defaultVote) {
        selectItemsInCustomDropdown(driver, DEFAULT_VOTE_DDL_PARENT, DEFAULT_VOTE_DDL_ITEMS, defaultVote);
        verify.verifyEquals(getElementText(driver, DEFAULT_VOTE_DDL_PARENT), defaultVote);
        return this;
    }

    public PollPageObject inputToDescriptionTxtbx(String value) {
        waitElementVisible(driver, DESCRIPTION_TXTBX);
        find(driver, DESCRIPTION_TXTBX).sendKeys(value);
        return this;
    }

    public PollPageObject clickToSaveBtn() {
        waitElementClickable(driver, SAVE_BTN);
        clickToElement(driver, SAVE_BTN);
        return this;
    }

    public void verifyDynamicPollIsCreated(String prjCode) {
        waitTextElementVisible(driver, DYNAMIC_POLL, prjCode);
        verify.verifyTrue(isElementDisplayed(driver, DYNAMIC_POLL, prjCode));
    }

    public PollPageObject clickToDynamicActionBtn(String prj) {
        waitElementClickable(driver, DYNAMIC_ACTION_BTN, prj);
        clickToElement(driver, DYNAMIC_ACTION_BTN, prj);
        return this;
    }

    public PollPageObject clickToDynamicActionItem(String action) {
        waitElementClickable(driver, DYNAMIC_ACTION_ITEM, action);
        clickToElement(driver, DYNAMIC_ACTION_ITEM, action);
        return this;
    }

    public PollPageObject clickToSubmitBtn() {
        waitElementClickable(driver, SUBMIT_BTN);
        clickToElement(driver, SUBMIT_BTN);
        return this;
    }

    public PollPageObject verifyDynamicPollStatusEqualsTo(String prj, String status) {
        waitTextElementVisible(driver, DYNAMIC_POLL_STATUS, status, prj);
        verify.verifyEquals(getElementText(driver, DYNAMIC_POLL_STATUS, prj), status);
        return this;
    }
}
