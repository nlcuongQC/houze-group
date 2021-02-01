package pageobjects.houzeinvest.investor;

import commons.AbstractPage;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static pageuis.houzeinvest.investor.CheckoutPageUI.*;

public class CheckoutPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public CheckoutPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    private boolean isRequestLoginMessageDisplayed() {
        return isElementDisplayed(driver, REQUEST_LOGIN_MESSAGE);
    }

    private boolean isNextButtonDisabled() {
        return getElementAttribute(driver, NEXT_BUTTON, "class").contains("undefined");
    }

    @Step("Input to {itemType} with: {value}")
    public CheckoutPageObject inputToDynamicItemTypeTextbox(String value, String itemType) {
        boolean flag = getElementAttribute(driver, DYNAMIC_ITEMTYPE_TEXTBOX, "value", itemType).equals(value);
        while (!flag) {
            waitElementVisible(driver, DYNAMIC_ITEMTYPE_TEXTBOX, itemType);
            sendkeyToElement(driver, DYNAMIC_ITEMTYPE_TEXTBOX, value, itemType);
            flag = getElementAttribute(driver, DYNAMIC_ITEMTYPE_TEXTBOX, "value", itemType).equals(value);
        }
        return this;
    }

    private int getTotalItems() {
        waitElementVisible(driver, TOTAL_ITEMS);
        return Integer.parseInt(getElementText(driver, TOTAL_ITEMS));
    }

    public int getDynamicPrice(String itemType) {
        waitTextElementChanged(driver, DYNAMIC_ITEMTYPE_PRICE, "0 đ", itemType);
        return getConvertPrice(driver, DYNAMIC_ITEMTYPE_PRICE, itemType);
    }

    private int getTotalPrice() {
        return getConvertPrice(driver, TOTAL_PRICE);
    }

    @Step("Click Next button")
    public CheckoutPageObject clickToNextButton() {
        waitElementClickable(driver, NEXT_BUTTON);
        clickToElementByJS(driver, NEXT_BUTTON);
        return this;
    }

    private int getDynamicItemAmount(String itemType) {
        waitElementVisible(driver, DYNAMIC_ITEM_AMOUNT, itemType);
        String amount = getElementText(driver, DYNAMIC_ITEM_AMOUNT, itemType).substring(1);
        return Integer.parseInt(amount);
    }

    private int getDynamicItemPrice(String itemType) {
        waitElementVisible(driver, DYNAMIC_CONFIRM_ITEMTYPE_PRICE, itemType);
        return getConvertPrice(driver, DYNAMIC_CONFIRM_ITEMTYPE_PRICE, itemType);
    }

    @Step("Check Contract checkbox")
    public CheckoutPageObject checkToContractCheckbox() {
        waitElementClickable(driver, CONTRACT_CHECKBOX);
        scrollToElement(driver, CONTRACT_CHECKBOX);
        waitElementClickable(driver, CONTRACT_CHECKBOX);
        checkTheCheckbox(driver, CONTRACT_CHECKBOX);
        return this;
    }

    @Step("Input OTP")
    public CheckoutPageObject inputOTP(String otp) {
        List<String>     otpList   = Arrays.asList(otp.split(""));
        List<WebElement> otpFields = finds(driver, OTP_TEXTBOXES);

        while (!otpFields.get(0).getAttribute("value").equals("")) {
            sendKeyboardToElement(driver, otpFields.get(0), Keys.BACK_SPACE);
        }

        for (int i = 0; i < otpFields.size(); i++) {
            sendkeyToElement(driver, otpFields.get(i), otpList.get(i));
        }
        return this;
    }

    private boolean isSubmitButtonDisabled() {
        waitElementVisible(driver, SUBMIT_INVEST_BUTTON);
        return getElementAttribute(driver, SUBMIT_INVEST_BUTTON, "class").contains("undefined");
    }

    @Step("Click Submit button")
    public CheckoutPageObject clickToSubmitButton() {
        waitElementClickable(driver, SUBMIT_INVEST_BUTTON);
        clickToElementByJS(driver, SUBMIT_INVEST_BUTTON);
        return this;
    }

    @Step("Click View portfolio button")
    public void clickToViewPortfolioButton() {
        waitElementClickable(driver, VIEW_PORTFOLIO_BUTTON);
        clickToElement(driver, VIEW_PORTFOLIO_BUTTON);
    }

    private boolean isFailedPopupDisplayed() {
        waitElementVisible(driver, POPUP_FAILED_INVEST_SUBMIT_TITLE);
        return isElementDisplayed(driver, POPUP_FAILED_INVEST_SUBMIT_TITLE);
    }

    @Step("Click Do again button")
    public CheckoutPageObject clickToDoAgainButton() {
        waitElementClickable(driver, DO_AGAIN_BUTTON);
        clickToElement(driver, DO_AGAIN_BUTTON);
        return this;
    }

    @Step("Verify request login message is displayed")
    public void verifyRequestLoginMessageIsDisplayed() {
        verify.verifyTrue(isRequestLoginMessageDisplayed());
    }

    @Step("Verify Next button is disabled")
    public CheckoutPageObject verifyNextButtonIsDisabled() {
        verify.verifyTrue(isNextButtonDisabled());
        return this;
    }

    @Step("Verify total items equal to {totalItems}")
    public CheckoutPageObject verifyTotalItemsEqualTo(int totalItems) {
        verify.verifyTrue(getTotalItems() == totalItems);
        return this;
    }

    @Step("Verify total price equal to {totalItems}")
    public CheckoutPageObject verifyTotalPriceEqualTo(int totalPrice) {
        verify.verifyTrue(getTotalPrice() == totalPrice);
        return this;
    }

    @Step("Verify {type} amount equal to {amount}")
    public CheckoutPageObject verifyDynamicItemAmount(String type, int amount) {
        verify.verifyEquals(getDynamicItemAmount(type), amount);
        return this;
    }

    @Step("Verify {type} price equal to {price}")
    public CheckoutPageObject verifyDynamicItemPrice(String type, int price) {
        verify.verifyEquals(getDynamicItemPrice(type), price);
        return this;
    }

    @Step("Verify Next button is enabled")
    public CheckoutPageObject verifyNextButtonIsEnabled() {
        verify.verifyFalse(isNextButtonDisabled());
        return this;
    }

    @Step("Verify Submit button is disabled")
    public CheckoutPageObject verifySubmitButtonIsDisabled() {
        verify.verifyTrue(isSubmitButtonDisabled());
        return this;
    }

    @Step("Verify Failed popup is displayed")
    public CheckoutPageObject verifyFailedPopupIsDisplayed() {
        verify.verifyTrue(isFailedPopupDisplayed());
        return this;
    }

    @Step("Verify successfully popup is displayed")
    public CheckoutPageObject verifySuccessfullyPopupIsDisplayed() {
        waitElementVisible(driver, POPUP_INVEST_SUBMIT_TITLE);
        verify.verifyTrue(isElementDisplayed(driver, POPUP_INVEST_SUBMIT_TITLE));
        return this;
    }

    public void verifyNotEnoughPopupIsDisplayed() {
    }
}
