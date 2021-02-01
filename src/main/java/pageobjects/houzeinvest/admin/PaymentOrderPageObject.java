package pageobjects.houzeinvest.admin;

import commons.AbstractPage;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static pageuis.houzeinvest.admin.PaymentOrderPageUI.*;

public class PaymentOrderPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public PaymentOrderPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    @Step("Create payment order with {type} type, item code {itemCode} with rate {rate}")
    public PaymentOrderPageObject createPaymentOrder(String type, String itemCode, String rate, String paymentDate) {
        clickToCreatePaymentOrderBtn();
        choosePaymentOrderType(type);
        chooseItemCode(itemCode);
        inputToRateTxtbx(rate);
        choosePaymentDate(paymentDate);
        clickSubmitBtn();
        verifyPaymentOrderStatusEqualTo(itemCode, "Chờ duyệt");
        approvePaymentOrder(itemCode);
        verifyPaymentOrderStatusEqualTo(itemCode, "Đã duyệt");
        processPaymentOrder(itemCode);
        verifyPaymentOrderStatusEqualTo(itemCode, "Hoàn thành");
        return this;
    }

    @Step("Verify Payment order status of {itemCode} is {status}")
    public void verifyPaymentOrderStatusEqualTo(String itemCode, String status) {
        waitTextElementVisible(driver, DYNAMIC_PAYMENT_ORDER_STATUS, itemCode);
        verify.verifyEquals(getElementText(driver, DYNAMIC_PAYMENT_ORDER_STATUS, itemCode), status);
    }

    @Step("Click submit button")
    public void clickSubmitBtn() {
        waitElementClickable(driver, SUBMIT_BTN);
        clickToElement(driver, SUBMIT_BTN);
    }

    @Step("Click submit button")
    public void clickSubmitActionBtn() {
        waitElementClickable(driver, SUBMIT_ACTION_BTN);
        clickToElement(driver, SUBMIT_ACTION_BTN);
    }

    @Step("Choose payment date is {paymentDate}")
    public void choosePaymentDate(String paymentDate) {
        clickToElementByJS(driver, PAYMENT_DATE_PICKER_BTN);
        clickToElement(driver, DYNAMIC_PAYMENT_DATE_PICKER_DATE, paymentDate);
        clickToElement(driver, DATE_PICKER_SUBMIT_BTN);
    }

    @Step("Input rate is {rate}")
    public void inputToRateTxtbx(String rate) {
        waitElementVisible(driver, RATE_TXTBX);
        sendkeyToElement(driver, RATE_TXTBX, rate);
    }

    @Step("Choose item code is {itemCode}")
    public void chooseItemCode(String itemCode) {
        sendkeyToElement(driver, ITEM_CODE_TXTBX, itemCode);
        clickToElement(driver, ITEM_CODE_DDL, itemCode);
    }

    @Step("Choose payment order type is {type}")
    public void choosePaymentOrderType(String type) {
        selectItemsInCustomDropdown(driver, PAYMENT_ORDER_TYPE_DDL_PARENT, PAYMENT_ORDER_TYPE_DDL_ITEMS, type);
    }

    @Step("Click Create payment order button")
    public void clickToCreatePaymentOrderBtn() {
        waitElementClickable(driver, CREATE_PAYMENT_ORDER);
        clickToElement(driver, CREATE_PAYMENT_ORDER);
    }

    @Step("Approve payment order of item code {itemCode}")
    public void approvePaymentOrder(String itemCode) {
        clickToActionBtn(itemCode);
        clickToDynamicActionItemBtn("Duyệt lệnh");
        clickSubmitActionBtn();
    }

    @Step("Click {actionItem} button")
    public void clickToDynamicActionItemBtn(String actionItem) {
        waitElementClickable(driver,DYNAMIC_ACTION_ITEMS_BTN, actionItem);
        clickToElement(driver,DYNAMIC_ACTION_ITEMS_BTN, actionItem);
    }

    @Step("Click Action button of item code {itemCode}")
    public void clickToActionBtn(String itemCode) {
        waitElementClickable(driver, DYNAMIC_ACTION_BTN, itemCode);
        clickToElement(driver, DYNAMIC_ACTION_BTN, itemCode);
    }

    @Step("Process payment order of {itemCode}")
    public void processPaymentOrder(String itemCode) {
        clickToActionBtn(itemCode);
        clickToDynamicActionItemBtn("Thực thi lệnh");
        clickSubmitActionBtn();
    }
}
