package pageobjects.houzeinvest.investor;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static pageuis.houzeinvest.investor.BasePageUI.*;

public class BasePageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        verify      = new VerifyHelper(driver);
//        verify      = VerifyHelper.getVerify(driver);
    }

    private boolean isLoginButtonDisplayed() {
        return isElementDisplayed(driver, LOGIN_BUTTON);
    }

    @Step("Click to Submit Login button")
    public BasePageObject clickToSubmitLoginButton() {
        waitElementClickable(driver, SUBMIT_LOGIN_BUTTON);
        clickToElement(driver, SUBMIT_LOGIN_BUTTON);
        return this;
    }

    @Step("Click to Login button")
    public BasePageObject clickToLoginButton() {
        waitElementClickable(driver, LOGIN_BUTTON);
        clickToElement(driver, LOGIN_BUTTON);
        return this;
    }

    @Step("Input to Login phone textbox")
    public BasePageObject inputToPhoneTextbox(String phone) {
        waitElementVisible(driver, PHONE_TEXTBOX);
        sendkeyToElement(driver, PHONE_TEXTBOX, phone);
        verify.verifyTrue(getValuePhoneTextbox().equals(phone));
        return this;
    }

    @Step("Input password with: {password}")
    public BasePageObject inputToPasswordTextbox(String password) {
        waitElementVisible(driver, PASSWORD_TEXTBOX);
        sendkeyToElement(driver, PASSWORD_TEXTBOX, password);
        verify.verifyTrue(getValuePasswordTextbox().equals(password));
        return this;
    }

    private Object getValuePhoneTextbox() {
        return getElementAttribute(driver, PHONE_TEXTBOX, "value");
    }

    private Object getValuePasswordTextbox() {
        return getElementAttribute(driver, PASSWORD_TEXTBOX, "value");
    }

    public boolean isLoginButtonUndisplayed() {
        return isElementUndisplayed(driver, LOGIN_BUTTON);
    }

    public Object getAlertMessage() {
        return getElementText(driver, ALERT_MESSAGE);
    }


    @Step("Click to Register button")
    public BasePageObject clickToRegisterButton() {
        waitElementClickable(driver, REGISTER_BUTTON);
        clickToElement(driver, REGISTER_BUTTON);
        return this;
    }

    public boolean isRegisterSubmitButtonDisabled() {
        return !isElementEnabled(driver, REGISTER_SUBMIT_BUTTON);
    }

    @Step("Input to Register name with value: {0}")
    public BasePageObject inputToRegisterNameTextbox(String name) {
        waitElementVisible(driver, REGISTER_NAME_TEXTBOX);
        sendkeyToElement(driver, REGISTER_NAME_TEXTBOX, name);
        return this;
    }

    @Step("Input to Register phone with value: {0}")
    public BasePageObject inputToRegisterPhoneTextbox(String phone) {
        waitElementVisible(driver, REGISTER_PHONE_TEXTBOX);
        sendkeyToElement(driver, REGISTER_PHONE_TEXTBOX, phone);
        return this;
    }

    @Step("Input to Register email with value: {0}")
    public BasePageObject inputToRegisterEmailTextbox(String email) {
        waitElementVisible(driver, REGISTER_EMAIL_TEXTBOX);
        sendkeyToElement(driver, REGISTER_EMAIL_TEXTBOX, email);
        return this;
    }

    @Step("Input to Register password with value: {0}")
    public BasePageObject inputToRegisterPasswordTextbox(String password) {
        waitElementVisible(driver, REGISTER_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, REGISTER_PASSWORD_TEXTBOX, password);
        return this;
    }

    @Step("Check into Condition checkbox")
    public BasePageObject checkToConditionCheckbox() {
        checkTheCheckbox(driver, REGISTER_CONDITION_CHECKBOX);
        return this;
    }

    @Step("Get phone error text")
    public String getPhoneErrorText() {
        waitElementVisible(driver, REGISTER_PHONE_ERROR_TEXT);
        return getElementText(driver, REGISTER_PHONE_ERROR_TEXT);
    }

    @Step("Click to Submit Register button")
    public BasePageObject clickToSubmitRegisterButton() {
        waitElementClickable(driver, REGISTER_SUBMIT_BUTTON);
        clickToElement(driver, REGISTER_SUBMIT_BUTTON);
        return this;
    }

    @Step("Input OTP")
    public BasePageObject inputToOTPTextbox(int otp) {
        waitElementsVisible(driver, REGISTER_OTP_TEXTBOXES);
        List<String>     otpList      = Arrays.asList(String.valueOf(otp).split(""));
        List<WebElement> otpTextboxes = finds(driver, REGISTER_OTP_TEXTBOXES);
        for (int i = 0; i < otpTextboxes.size(); i++) {
            sendkeyToElement(driver, otpTextboxes.get(i), otpList.get(i));
        }
        return this;
    }

    public String getOTPErrorText() {
        waitElementVisible(driver, REGISTER_OTP_ERROR_TEXT);
        return getElementText(driver, REGISTER_OTP_ERROR_TEXT);
    }

    public String getEmailErrorText() {
        waitElementVisible(driver, REGISTER_EMAIL_ERROR_TEXT);
        return getElementText(driver, REGISTER_EMAIL_ERROR_TEXT);
    }

    @Step("Login to account {phone} with {password}")
    public BasePageObject loginToAnAccount(String phone, String password) {
        boolean flag = isElementDisplayed(driver, LOGIN_BUTTON);
        if (flag) {
            PageGeneratorManager.HouzeInvest.getBasePageObject(driver);
            clickToLoginButton();
            inputToPhoneTextbox(phone);
            inputToPasswordTextbox(password);
            clickToSubmitLoginButton();
            verifyAlertMessageEqualTo("Đăng nhập thành công");
            verifyLoginButtonIsDisappeared();
        }
        return this;
    }

    @Step("Verify OTP is disappeared")
    public void verifyOTPIsDisappeared() {
        waitElementInvisible(driver, REGISTER_OTP_TEXTBOXES);
        verify.verifyTrue(isElementUndisplayed(driver, REGISTER_OTP_TEXTBOXES));
    }

    @Step("Verify Login button is Displayed")
    public BasePageObject verifyLoginButtonIsDisplayed() {
        verify.verifyTrue(isLoginButtonDisplayed());
        return this;
    }

    @Step("Verify Register submit button is disabled")
    public void verifyRegisterSubmitButtonIsDisabled() {
        verify.verifyTrue(isRegisterSubmitButtonDisabled());
    }

    @Step("Verify Login button is disappeared")
    public BasePageObject verifyLoginButtonIsDisappeared() {
        waitElementInvisible(driver, LOGIN_BUTTON);
        verify.verifyTrue(isElementUndisplayed(driver, LOGIN_BUTTON));
        return this;
    }

    @Step("Verify alert message equal to: {message}")
    public BasePageObject verifyAlertMessageEqualTo(String message) {
        waitElementVisible(driver, ALERT_MESSAGE);
        verify.verifyEquals(getElementText(driver, ALERT_MESSAGE), message);
        return this;
    }

    @Step("Verify OTP error: {0}")
    public void verifyOTPErrorEqualTo(String message) {
        verify.verifyEquals(getOTPErrorText(), message);
    }

    @Step("Verify Submit button is enabled")
    public BasePageObject verifyRegisterSubmitButtonIsEnabled() {
        verify.verifyFalse(isRegisterSubmitButtonDisabled());
        return this;
    }

    @Step("Verify Phone error: {0}")
    public BasePageObject verifyPhoneErrorEqualTo(String value) {
        verify.verifyEquals(getPhoneErrorText(), value);
        return this;
    }

    @Step("Verify Email error: {0}")
    public BasePageObject verifyEmailErrorEqualTo(String value) {
        verify.verifyEquals(getEmailErrorText(), value);
        return this;
    }
}
