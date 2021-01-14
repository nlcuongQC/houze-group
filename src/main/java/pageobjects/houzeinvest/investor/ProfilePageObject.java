package pageobjects.houzeinvest.investor;

import commons.AbstractPage;
import commons.Functions;
import commons.GlobalConstants;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import static pageuis.houzeinvest.investor.ProfilePageUI.*;

public class ProfilePageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;
    Functions    functions;

    public ProfilePageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
        functions   = Functions.getFunctions(driver);
    }

    @Step("Click to Begin Verify button")
    public ProfilePageObject clickToBeginVerifyButton() {
        waitElementClickable(driver, BEGIN_VERIFY_BUTTON);
        clickToElement(driver, BEGIN_VERIFY_BUTTON);
        return this;
    }

    private boolean isVerifySubmitButtonDisabled() {
        waitElementVisible(driver, VERIFY_SUBMIT_BUTTON);
        return !isElementEnabled(driver, VERIFY_SUBMIT_BUTTON);
    }

    @Step("Input Verify name with value: {0}")
    public ProfilePageObject inputToVerifyNameTextbox(String name) {
        waitElementVisible(driver, VERIFY_NAME_TEXTBOX);
        sendkeyToElement(driver, VERIFY_NAME_TEXTBOX, name);
        return this;
    }

    @Step("Choose gender with value: {gender}")
    public ProfilePageObject chooseGenderDropdown(String gender) {
        waitElementVisible(driver, GENDER_DROPDOWN);
        selectItemsInCustomDropdown(driver, GENDER_DROPDOWN, GENDER_DROPDOWN_ITEMS, gender);
        return this;
    }

    @Step("Input birthday with value: {birthday}")
    public ProfilePageObject inputToBirthdayTextbox(String birthday) {
        waitElementVisible(driver, BIRTHDAY_TEXTBOX);
        //        removeAttributeInDOM(driver, ProfilePageUI.BIRTHDAY_TEXTBOX, "readonly");
        sendkeyToElementByJS(driver, BIRTHDAY_TEXTBOX, birthday);
        return this;
    }

    @Step("Input address with value: {address}")
    public ProfilePageObject inputToAddressTextbox(String address) {
        waitElementVisible(driver, ADDRESS_TEXTBOX);
        sendkeyToElement(driver, ADDRESS_TEXTBOX, address);
        return this;
    }

    @Step("Input full address with value: {fullAddress}")
    public ProfilePageObject inputToFullAddressTextbox(String fullAddress) {
        waitElementVisible(driver, FULL_ADDRESS_TEXTBOX);
        sendkeyToElement(driver, FULL_ADDRESS_TEXTBOX, fullAddress);
        return this;
    }

    @Step("Input issue date with: {issueDate}")
    public ProfilePageObject inputToIssueDateTextbox(String issueDate) {
        try {
            waitElementVisible(driver, ISSUE_DATE_TEXTBOX);
            sendkeyToElement(driver, ISSUE_DATE_TEXTBOX, issueDate);
        } catch (TimeoutException e) {
            functions.saveScreenshot();
            e.printStackTrace();
        }
        return this;
    }

    @Step("Input issue place with: {issuePlace}")
    public ProfilePageObject inputToIssuePlaceTextbox(String issuePlace) {
        waitElementVisible(driver, ISSUE_PLACE_TEXTBOX);
        sendkeyToElement(driver, ISSUE_PLACE_TEXTBOX, issuePlace);
        return this;
    }

    @Step("Input issue number with: {issueNumber}")
    public ProfilePageObject inputToIssueNumberTextbox(String issueNumber) {
        waitElementVisible(driver, ISSUE_NUMBER_TEXTBOX);
        sendkeyToElement(driver, ISSUE_NUMBER_TEXTBOX, issueNumber);
        return this;
    }

    @Step("Choose type issue paper with value: {type}")
    public void clickChooseTypeIssuePaper(String type) {
        clickToElement(driver, ISSUE_PAPER_RADIO, type);
        if ("1".equals(type)) {
            verify.verifyTrue(isElementUndisplayed(driver, IMAGE_BACK_UPLOAD));
        } else if ("0".equals(type)) {
            verify.verifyTrue(isElementUndisplayed(driver, IMAGE_FRONT_UPLOAD));
        }
    }

    @Step("Upload front issue image")
    public ProfilePageObject uploadFrontIssueImage(String imageFront) {
        removeAttributeInDOM(driver, IMAGE_FRONT_UPLOAD, "class");
        uploadFile(driver, IMAGE_FRONT_UPLOAD, imageFront);
        verify.verifyTrue(isElementDisplayed(driver, FRONT_ISSUE_IMAGE));
        return this;
    }

    @Step("Upload back issue image")
    public ProfilePageObject uploadBackIssueImage(String imageBack) {
        removeAttributeInDOM(driver, IMAGE_BACK_UPLOAD, "class");
        uploadFile(driver, IMAGE_BACK_UPLOAD, imageBack);
        verify.verifyTrue(isElementDisplayed(driver, BACK_ISSUE_IMAGE));
        return this;
    }

    @Step("Click Verify submit button")
    public ProfilePageObject clickToVerifySubmitButton() {
        waitElementVisible(driver, VERIFY_SUBMIT_BUTTON);
        clickToElement(driver, VERIFY_SUBMIT_BUTTON);
        return this;
    }

    @Step("Click Done button in Verify popup")
    public ProfilePageObject clickToVerifyPopupDoneButton() {
        waitElementVisible(driver, VERIFY_POPUP_DONE_BUTTON);
        clickToElement(driver, VERIFY_POPUP_DONE_BUTTON);
        return this;
    }

    @Step("Go to profile page")
    public ProfilePageObject navigateToPage() {
        openPageUrl(driver, GlobalConstants.HI_URL + "/account/profile");
        return this;
    }

    @Step("Verify Verify Submit button is disabled")
    public ProfilePageObject verifyVerifySubmitBtnDisabled() {
        waitElementVisible(driver, VERIFY_SUBMIT_BUTTON);
        verify.verifyTrue(!isElementEnabled(driver, VERIFY_SUBMIT_BUTTON));
        return this;
    }

    @Step("Verify Verify popup title equal to: {message}")
    public ProfilePageObject verifyVerifyPopupTitleEqualTo(String message) {
        waitElementVisible(driver, VERIFY_POPUP_TITLE);
        verify.verifyEquals(getElementText(driver, VERIFY_POPUP_TITLE), message);
        return this;
    }

    @Step("Verify Status profile equal to: {status}")
    public void verifyStatusProfileEqualTo(String status) {
        waitElementVisible(driver, STATUS_PROFILE);
        verify.verifyEquals(getElementText(driver, STATUS_PROFILE), status);
    }

    @Step("Verify Verify submit button is enabled")
    public ProfilePageObject verifyVerifySubmitBtnEnabled() {
        verify.verifyFalse(isVerifySubmitButtonDisabled());
        return this;
    }

    @Step("Verify Begin verify button is displayed")
    public void verifyBeginVerifyBtnIsDisplayed() {
        verify.verifyTrue(isElementDisplayed(driver, BEGIN_VERIFY_BUTTON));
    }

    @Step("Click Update profile button")
    public ProfilePageObject clickToUpdateProfileBtn() {
        waitElementClickable(driver, UPDATE_PROFILE_BTN);
        clickToElement(driver, UPDATE_PROFILE_BTN);
        return this;
    }

    @Step("Verify Update profile button is disappeared")
    public ProfilePageObject verifyUpdateProfileBtnIsDisappeared() {
        waitElementInvisible(driver, UPDATE_PROFILE_BTN);
        verify.verifyTrue(isElementUndisplayed(driver, UPDATE_PROFILE_BTN));
        return this;
    }

    @Step("Verify Save button is displayed")
    public ProfilePageObject verifyUpdateProfileSaveBtnIsDisplayed() {
        waitElementVisible(driver, UPDATE_PROFILE_SAVE_BTN);
        verify.verifyTrue(isElementDisplayed(driver, UPDATE_PROFILE_SAVE_BTN));
        return this;
    }

    @Step("Verify Cancel button is displayed")
    public ProfilePageObject verifyUpdateProfileCancelBtnIsDisplayed() {
        waitElementVisible(driver, UPDATE_PROFILE_CANCEL_BTN);
        verify.verifyTrue(isElementDisplayed(driver, UPDATE_PROFILE_CANCEL_BTN));
        return this;
    }

    @Step("Verify Email field is enabled")
    public void verifyUpdateProfileEmailTxtbxIsNotDisabled() {
        waitElementVisible(driver, UPDATE_PROFILE_EMAIL_TXTBX);
        verify.verifyFalse(isElementDisabled(driver, UPDATE_PROFILE_EMAIL_TXTBX));
    }

    @Step("Click Save button")
    public ProfilePageObject clickToUpdateProfileSaveBtn() {
        waitElementClickable(driver, UPDATE_PROFILE_SAVE_BTN);
        clickToElement(driver, UPDATE_PROFILE_SAVE_BTN);
        return this;
    }

    @Step("Verify Save button is disappeared")
    public ProfilePageObject verifyUpdateProfileSaveBtnIsDisappeared() {
        verify.verifyTrue(isElementUndisplayed(driver, UPDATE_PROFILE_SAVE_BTN));
        return this;
    }

    @Step("Verify Cancel button is disappeared")
    public ProfilePageObject verifyUpdateProfileCancelBtnIsDisappeared() {
        verify.verifyTrue(isElementUndisplayed(driver, UPDATE_PROFILE_CANCEL_BTN));
        return this;
    }

    @Step("Verify Update profile button is displayed")
    public ProfilePageObject verifyUpdateProfileBtnIsDisplayed() {
        verify.verifyTrue(isElementDisplayed(driver, UPDATE_PROFILE_BTN));
        return this;
    }

    @Step("Verify Email value is: {0}")
    public ProfilePageObject verifyUpdateProfileEmailTxtbxValueEqualTo(String email) {
        verify.verifyEquals(getElementAttribute(driver, UPDATE_PROFILE_EMAIL_TXTBX, "value"), email);
        return this;
    }

    @Step("Upload avatar with: {0}")
    public ProfilePageObject uploadAvatar(String imgName) {
        removeAttributeInDOM(driver, UPDATE_PROFILE_AVATAR, "class");
        uploadFile(driver, UPDATE_PROFILE_AVATAR, imgName);
        return this;
    }

    @Step("Verify avatar is displayed")
    public ProfilePageObject verifyAvatarIsDisplayed() {
        waitElementVisible(driver, UPDATE_PROFILE_AVATAR_IMG);
        verify.verifyTrue(isElementDisplayed(driver, UPDATE_PROFILE_AVATAR_IMG));
        return this;
    }

    @Step("Click Cancel button")
    public ProfilePageObject clickToUpdateProfileCancelBtn() {
        waitElementClickable(driver, UPDATE_PROFILE_CANCEL_BTN);
        clickToElement(driver, UPDATE_PROFILE_CANCEL_BTN);
        return this;
    }

    @Step("Verify avatar isn't displayed")
    public ProfilePageObject verifyAvatarIsNotDisplayed() {
        waitElementInvisible(driver, UPDATE_PROFILE_AVATAR_IMG);
        verify.verifyTrue(isElementUndisplayed(driver, UPDATE_PROFILE_AVATAR_IMG));
        return this;
    }

    @Step("Input {0} to Email field")
    public ProfilePageObject inputToUpdateProfileEmailTxtbx(String email) {
        waitElementVisible(driver, UPDATE_PROFILE_EMAIL_TXTBX);
        sendkeyToElement(driver, UPDATE_PROFILE_EMAIL_TXTBX, email);
        return this;
    }

    @Step("Click Change password button")
    public ProfilePageObject clickToChangePwBtn() {
        waitElementClickable(driver, CHANGE_PW_BTN);
        clickToElement(driver, CHANGE_PW_BTN);
        return this;
    }

    @Step("Verify Change password button is disappeared")
    public ProfilePageObject verifyChangePwBtnIsDisappeared() {
        waitElementInvisible(driver, CHANGE_PW_BTN);
        verify.verifyTrue(isElementUndisplayed(driver, CHANGE_PW_BTN));
        return this;
    }

    @Step("Verify Save button is displayed")
    public ProfilePageObject verifyChangePwSaveBtnIsDisplayed() {
        waitElementVisible(driver, CHANGE_PW_SAVE_BTN);
        verify.verifyTrue(isElementDisplayed(driver, CHANGE_PW_SAVE_BTN));
        return this;
    }

    @Step("Verify Cancel button is displayed")
    public ProfilePageObject verifyChangePwCancelBtnIsDisplayed() {
        waitElementVisible(driver, CHANGE_PW_CANCEL_BTN);
        verify.verifyTrue(isElementDisplayed(driver, CHANGE_PW_CANCEL_BTN));
        return this;
    }

    @Step("Verify Filled Password is disappeared")
    public ProfilePageObject verifyChangePwFilledPwTxtbxIsDisappeared() {
        waitElementInvisible(driver, CHANGE_PW_FILLED_PW_TXTBX);
        verify.verifyTrue(isElementUndisplayed(driver, CHANGE_PW_FILLED_PW_TXTBX));
        return this;
    }

    @Step("Verify Current password field is displayed")
    public ProfilePageObject verifyChangePwCurrentPwTxtbxIsDisplayed() {
        waitElementVisible(driver, CHANGE_PW_CURRENT_PW_TXTBX);
        verify.verifyTrue(isElementDisplayed(driver, CHANGE_PW_CURRENT_PW_TXTBX));
        return this;
    }

    @Step("Verify New password field is displayed")
    public ProfilePageObject verifyChangePwNewPwTxtbxIsDisplayed() {
        waitElementVisible(driver, CHANGE_PW_NEW_PW_TXTBX);
        verify.verifyTrue(isElementDisplayed(driver, CHANGE_PW_NEW_PW_TXTBX));
        return this;
    }

    @Step("Verify Confirm new password field is displayed")
    public void verifyChangePwConfirmNewPwTxtbxIsDisplayed() {
        waitElementVisible(driver, CHANGE_PW_CONFIRM_NEW_PW_TXTBX);
        verify.verifyTrue(isElementDisplayed(driver, CHANGE_PW_CONFIRM_NEW_PW_TXTBX));
    }

    @Step("Verify Save button is disabled")
    public ProfilePageObject verifyChangePwSaveBtnIsDisabled() {
        waitElementVisible(driver, CHANGE_PW_SAVE_BTN);
        verify.verifyFalse(isElementEnabled(driver, CHANGE_PW_SAVE_BTN));
        return this;
    }

    @Step("Input {0} to Current password")
    public ProfilePageObject inputToChangePwCurrentPwTxtbx(String password) {
        waitElementVisible(driver, CHANGE_PW_CURRENT_PW_TXTBX);
        sendkeyToElement(driver, CHANGE_PW_CURRENT_PW_TXTBX, password);
        return this;
    }

    @Step("Verify Current password value is {0}")
    public ProfilePageObject verifyChangePwCurrentPwTxtbxValueEqualTo(String value) {
        waitElementVisible(driver, CHANGE_PW_CURRENT_PW_TXTBX);
        verify.verifyEquals(getElementAttribute(driver, CHANGE_PW_CURRENT_PW_TXTBX, "value"), value);
        return this;
    }

    @Step("Input {0} to New password")
    public ProfilePageObject inputToChangePwNewPwTxtbx(String password) {
        waitElementVisible(driver, CHANGE_PW_NEW_PW_TXTBX);
        sendkeyToElement(driver, CHANGE_PW_NEW_PW_TXTBX, password);
        return this;
    }

    @Step("Verify New password value is: {0}")
    public ProfilePageObject verifyChangePwNewPwTxtbxValueEqualTo(String password) {
        waitElementVisible(driver, CHANGE_PW_NEW_PW_TXTBX);
        verify.verifyEquals(getElementAttribute(driver, CHANGE_PW_NEW_PW_TXTBX, "value"), password);
        return this;
    }

    @Step("Verify Save button is enabled")
    public ProfilePageObject verifyChangePwSaveBtnIsEnabled() {
        waitElementClickable(driver, CHANGE_PW_SAVE_BTN);
        verify.verifyTrue(isElementEnabled(driver, CHANGE_PW_SAVE_BTN));
        return this;
    }

    @Step("Click Save button")
    public ProfilePageObject clickToChangePwSaveBtn() {
        waitElementClickable(driver, CHANGE_PW_SAVE_BTN);
        clickToElement(driver, CHANGE_PW_SAVE_BTN);
        return this;
    }

    @Step("Verify Email validate text equal to: ")
    public ProfilePageObject verifyEmailValidateTxtEqualTo(String text) {
        outFocusElement(driver);
        waitTextElementVisible(driver, UPDATE_PROFILE_EMAIL_VALIDATE_TXT, text);
        verify.verifyEquals(getElementText(driver, UPDATE_PROFILE_EMAIL_VALIDATE_TXT), text);
        return this;
    }

    public void verifyChangePwNewPwNotMatchErrorEqualTo(String txt) {
        waitElementVisible(driver, CHANGE_PW_NOT_MATCH_ERR);
        verify.verifyEquals(getElementText(driver, CHANGE_PW_NOT_MATCH_ERR), txt);
    }

    public ProfilePageObject verifyChangePwNewPwNotMatchErrorIsNotDisplayed() {
        verify.verifyTrue(isElementUndisplayed(driver, CHANGE_PW_NOT_MATCH_ERR));
        return this;
    }

    public ProfilePageObject inputToChangePwConfirmNewPwTxtbx(String pw) {
        waitElementVisible(driver, CHANGE_PW_CONFIRM_NEW_PW_TXTBX);
        sendkeyToElement(driver, CHANGE_PW_CONFIRM_NEW_PW_TXTBX, pw);
        return this;
    }

    public ProfilePageObject verifyChangePwConfirmNewPwTxtbxValueEqualTo(String pw) {
        waitElementVisible(driver, CHANGE_PW_CONFIRM_NEW_PW_TXTBX);
        verify.verifyEquals(getElementAttribute(driver, CHANGE_PW_CONFIRM_NEW_PW_TXTBX, "value"), pw);
        return this;
    }

    public void verifyChangePwBtnIsDisplayed() {
        waitElementVisible(driver, CHANGE_PW_BTN);
        verify.verifyTrue(isElementDisplayed(driver, CHANGE_PW_BTN));
    }
}
