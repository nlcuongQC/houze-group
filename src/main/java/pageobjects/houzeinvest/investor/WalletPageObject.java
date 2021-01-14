package pageobjects.houzeinvest.investor;

import commons.AbstractPage;
import commons.GlobalConstants;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static commons.Functions.convertMoneyAmount;
import static commons.Functions.convertMoneyStringToInt;
import static pageuis.houzeinvest.investor.BasePageUI.ALERT_MESSAGE;
import static pageuis.houzeinvest.investor.WalletPageUI.*;

public class WalletPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public WalletPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    @Step("Go to Wallet page")
    public WalletPageObject navigateToPage() {
        openPageUrl(driver, GlobalConstants.HI_URL + "/account/wallet");
        return this;
    }

    @Step("Click Add bank button")
    public WalletPageObject clickToAddBankBtn() {
        waitElementClickable(driver, ADD_BANK_BTN);
        clickToElement(driver, ADD_BANK_BTN);
        return this;
    }

    @Step("Input account number with value: {accountNumber}")
    public WalletPageObject inputToAddBankAccountNumberTxtbx(String accountNumber) {
        waitElementVisible(driver, ADD_BANK_ACCOUNT_NUMBER_TXTBX);
        sendkeyToElement(driver, ADD_BANK_ACCOUNT_NUMBER_TXTBX, accountNumber);
        return this;
    }

    @Step("Input OTP with value: {otp}")
    public WalletPageObject inputOTP(int otp) {
        waitElementsVisible(driver, OTP_TXTBXES);
        List<String>     otpList    = Arrays.asList(String.valueOf(otp).split(""));
        List<WebElement> otpTxtbxes = finds(driver, OTP_TXTBXES);
        for (int i = 0; i < otpTxtbxes.size(); i++) {
            sendkeyToElement(driver, otpTxtbxes.get(i), otpList.get(i));
        }
        return this;
    }

    @Step("Click next button")
    public WalletPageObject clickToAddBankNextBtn() {
        waitElementClickable(driver, ADD_BANK_NEXT_BTN);
        clickToElement(driver, ADD_BANK_NEXT_BTN);
        return this;
    }

    @Step("Select bank {bankName}")
    public WalletPageObject selectBank(String bankName) {
        selectItemsInCustomDropdown(driver, ADD_BANK_DROPDOWN_PARENT, ADD_BANK_DROPDOWN_ITEMS, bankName);
        return this;
    }

    @Step("Create a bank with bank account: {bankAccount}")
    public void createABank(String bankName, String bankAccount) {
        clickToAddBankBtn();
        selectBank(bankName);
        inputToAddBankAccountNumberTxtbx(bankAccount);
        clickToAddBankNextBtn();
        inputOTP(123456);
    }

    @Step("Input to Depost textbox with value: {value}")
    public WalletPageObject inputToDepositTxtbx(String depositAmount) {
        waitElementVisible(driver, DEPOSIT_TXTBX);
        sendkeyToElement(driver, DEPOSIT_TXTBX, depositAmount);
        return this;
    }

    @Step("Select Deposit Bank: {depositBank}")
    public WalletPageObject selectDepositBank(String depositBank) {
        selectItemsInCustomDropdown(driver, DEPOSIT_BANK_PARENT, DEPOSIT_BANK_ITEMS, depositBank);
        return this;
    }

    @Step("Click to Create Deposit Order button")
    public WalletPageObject clickToCreateDepositOrderBtn() {
        waitElementClickable(driver, CREATE_DEPOSIT_ORDER_BTN);
        clickToElement(driver, CREATE_DEPOSIT_ORDER_BTN);
        return this;
    }

    @Step("Verify Add bank account number is displayed")
    public void verifyAddBankAccountNumberIsDisplayed() {
        waitElementVisible(driver, ADD_BANK_ACCOUNT_NUMBER_TXTBX);
        verify.verifyTrue(isElementDisplayed(driver, ADD_BANK_ACCOUNT_NUMBER_TXTBX));
    }

    @Step("Verify Add bank next button is disabled")
    public WalletPageObject verifyAddBankNextBtnIsDisabled() {
        waitElementVisible(driver, ADD_BANK_NEXT_BTN);
        verify.verifyTrue(getElementAttribute(driver, ADD_BANK_NEXT_BTN, "class").contains("disable"));
        return this;
    }

    @Step("Verify Add bank next button is enabled")
    public WalletPageObject verifyAddBankNextBtnEnabled() {
        waitAttributeElement(driver, ADD_BANK_NEXT_BTN, "class", "linear-background");
        verify.verifyTrue(getElementAttribute(driver, ADD_BANK_NEXT_BTN, "class").contains("linear-background"));
        return this;
    }

    @Step("Verify Wallet page is displayed")
    public void verifyWalletPageIsDisplayed() {
        waitElementVisible(driver, ADD_BANK_BTN);
        verify.verifyTrue(isElementDisplayed(driver, ADD_BANK_BTN));
    }

    @Step("Verify Create deposit order button is disabled")
    public WalletPageObject verifyCreateDepositOrderBtnIsDisabled() {
        waitElementVisible(driver, CREATE_DEPOSIT_ORDER_BTN);
        verify.verifyTrue(!isElementEnabled(driver, CREATE_DEPOSIT_ORDER_BTN));
        return this;
    }

    @Step("Verify Deposit popup is displayed")
    public WalletPageObject verifyDepostitPopupIsDisplayed() {
        verify.verifyTrue(isElementDisplayed(driver, DEPOSIT_POPUP_BANK_NAME));
        return this;
    }

    @Step("Verify Bank account name deposit popup equal to: {value}")
    public WalletPageObject verifyBankAccountNameDepositPopupEqualTo(String value) {
        waitElementVisible(driver, DEPOSIT_POPUP_BANK_NAME);
        verify.verifyEquals(getElementText(driver, DEPOSIT_POPUP_BANK_NAME), value);
        return this;
    }

    @Step("Verify Bank account amount deposit popup equal to: {value}")
    public void verifyBankAccountAmountDepositPopupEqualTo(String value) {
        waitElementVisible(driver, DEPOSIT_POPUP_AMOUNT);
        verify.verifyEquals(getElementText(driver, DEPOSIT_POPUP_AMOUNT), value);
    }

    @Step("Verify Bank info with bank name: {bankName}, owner: {owner}, account number: {accountNumber}")
    public void verifyBankInfoEqualTo(String bankName, String owner, String accountNumber) {
        waitElementVisible(driver, BANK_INFO_NAME_TXT);
        verify.verifyEquals(getElementText(driver, BANK_INFO_NAME_TXT), bankName.toUpperCase(Locale.ROOT));

        waitElementVisible(driver, BANK_INFO_OWNER_TXT);
        verify.verifyEquals(getElementText(driver, BANK_INFO_OWNER_TXT), owner.toUpperCase(Locale.ROOT));

        waitElementVisible(driver, BANK_INFO_ACCOUNT_NUMBER_TXT);
        verify.verifyEquals(getElementText(driver, BANK_INFO_ACCOUNT_NUMBER_TXT), accountNumber);
    }

    @Step("Verify alert message equal to: {message}")
    public WalletPageObject verifyAlertMessageEqualTo(String message) {
        waitElementVisible(driver, ALERT_MESSAGE);
        verify.verifyEquals(getElementText(driver, ALERT_MESSAGE), message);
        return this;
    }

    @Step("Verify OTP form is disappeared")
    public WalletPageObject verifyOTPFormIsDisappeared() {
        waitElementInvisible(driver, OTP_TXTBXES);
        verify.verifyTrue(isElementUndisplayed(driver, OTP_TXTBXES));
        return this;
    }

    @Step("Click to Delete bank button")
    public WalletPageObject clickToDeleteBankBtn() {
        waitElementClickable(driver, BANK_DELETE_BTN);
        clickToElement(driver, BANK_DELETE_BTN);
        return this;
    }

    @Step("Verify Delete bank form is displayed")
    public void verifyDeleteBankFormIsDisplayed() {
        verify.verifyTrue(isElementDisplayed(driver, DELETE_BANK_SUBMIT_BTN));
    }

    @Step("Click to Back Delete bank button")
    public WalletPageObject clickToDeleteBankBackBtn() {
        waitElementClickable(driver, DELETE_BANK_BACK_BTN);
        clickToElement(driver, DELETE_BANK_BACK_BTN);
        return this;
    }

    @Step("Verify Delete bank form is disappeared")
    public WalletPageObject verifyDeleteBankFormIsDisappeared() {
        waitElementInvisible(driver, DELETE_BANK_SUBMIT_BTN);
        verify.verifyTrue(isElementUndisplayed(driver, DELETE_BANK_SUBMIT_BTN));
        return this;
    }

    @Step("Click to submit Delete bank button")
    public void clickToDeleteBankSubmitBtn() {
        waitElementClickable(driver, DELETE_BANK_SUBMIT_BTN);
        clickToElement(driver, DELETE_BANK_SUBMIT_BTN);
    }

    @Step("Verify bank is deleted")
    public void verifyBankIsDeleted() {
        waitElementInvisible(driver, BANK_INFO);
        verify.verifyTrue(isElementUndisplayed(driver, BANK_INFO));
    }

    @Step("Verify Create withdrawn order button is disabled")
    public WalletPageObject verifyCreateWithdrawOrderBtnIsDisabled() {
        verify.verifyFalse(isElementEnabled(driver, CREATE_WITHDRAW_ORDER_BTN));
        return this;
    }

    @Step("Deposit to account with value: {amount}")
    public void createDepositOrder(String bankName, String amount) {
        verifyCreateDepositOrderBtnIsDisabled();
        inputToDepositTxtbx(amount);
        selectDepositBank(bankName);
        clickToCreateDepositOrderBtn();
        verifyDepostitPopupIsDisplayed();
        verifyBankAccountNameDepositPopupEqualTo(bankName);
        verifyBankAccountAmountDepositPopupEqualTo(convertMoneyAmount(amount));
    }

    @Step("Input Withdraw amount: {amount}")
    public WalletPageObject inputToWithdrawTxtbx(String amount) {
        waitElementVisible(driver, WITHDRAW_TXTBX);
        sendkeyToElement(driver, WITHDRAW_TXTBX, amount);
        return this;
    }

    @Step("Select withdraw bank")
    public WalletPageObject selectWithdrawBank() {
        waitElementVisible(driver, BANK_INFO);
        clickToElement(driver, BANK_INFO);
        return this;
    }

    @Step("Verify bank owner")
    public WalletPageObject verifyAddBankOwner(String name) {
        verify.verifyTrue(getElementAttribute(driver, ADD_BANK_NAME_TXTBX, "value").equals(name));
        return this;
    }

    @Step("Click Create withdraw order button")
    public WalletPageObject clickToCreateWithdrawBtn() {
        waitElementClickable(driver, CREATE_WITHDRAW_ORDER_BTN);
        clickToElement(driver, CREATE_WITHDRAW_ORDER_BTN);
        return this;
    }

    @Step("Click Approve create withdraw")
    public void clickToApproveCreateWithdrawBtn() {
        waitElementClickable(driver, CREATE_WITHDRAW_ORDER_APPROVE_BTN);
        clickToElement(driver, CREATE_WITHDRAW_ORDER_APPROVE_BTN);
    }

    @Step("Verify create withdraw dialog is not disappeared")
    public void verifyCreateWithdrawOrderApproveBtnIsNotDisappeared() {
        verify.verifyFalse(isElementUndisplayed(driver, CREATE_WITHDRAW_ORDER_APPROVE_BTN));
    }

    @Step("Verify create withdraw dialog is disappeared")
    public WalletPageObject verifyCreateWithdrawOrderApproveBtnIsDisappeared() {
        waitElementInvisible(driver, CREATE_WITHDRAW_ORDER_APPROVE_BTN);
        verify.verifyTrue(isElementUndisplayed(driver, CREATE_WITHDRAW_ORDER_APPROVE_BTN));
        return this;
    }

    @Step("Verify Withdraw info amount equal to: {amount}")
    public WalletPageObject verifyWithdrawInfoAmountEqualTo(String amount) {
        verify.verifyEquals(getElementText(driver, WITHDRAW_INFO_AMOUNT), convertMoneyAmount(amount));
        return this;
    }

    @Step("Verify available balance")
    public WalletPageObject verifyAvailableBalance(String oldAmount, String amount) {
        String newBalance = getAvailableBalance();
        String availableBalance = String.valueOf(
                convertMoneyStringToInt(newBalance) - convertMoneyStringToInt(oldAmount));
        verify.verifyEquals(convertMoneyAmount(availableBalance), convertMoneyAmount(amount));
        return this;
    }

    @Step("Verify total balance")
    public void verifyTotalBalance() {
        int availableBalance         = convertMoneyStringToInt(getElementText(driver, AVAILABLE_BALANCE));
        int buyTradingItemsAmount    = convertMoneyStringToInt(getElementText(driver, BUY_TRADING_ITEM_AMOUNT));
        int withdrawProcessingAmount = convertMoneyStringToInt(getElementText(driver, WITHDRAW_PROCESSING_AMOUNT));
        int totalBalance             = convertMoneyStringToInt(getElementText(driver, TOTAL_BALANCE));
        verify.verifyTrue((availableBalance + buyTradingItemsAmount + withdrawProcessingAmount == totalBalance));
    }

    @Step("Get available balance")
    public String getAvailableBalance() {
        waitElementVisible(driver, AVAILABLE_BALANCE);
        return getElementText(driver, AVAILABLE_BALANCE);
    }

    @Step("Verify withdraw processing amount equal to {amount}")
    public WalletPageObject verifyWithdrawProcessingAmountEqualTo(String amount) {
        waitElementVisible(driver, WITHDRAW_PROCESSING_AMOUNT);
        String withdrawProcessingAmount = getElementText(driver, WITHDRAW_PROCESSING_AMOUNT);
        verify.verifyEquals(withdrawProcessingAmount, convertMoneyAmount(amount) + " đ");
        return this;
    }

    public void verifyOTPValidateMessageEqualTo(String message) {
        waitElementVisible(driver, OTP_VALIDATE_TXT);
        verify.verifyEquals(getElementText(driver, OTP_VALIDATE_TXT), message);
    }
}
