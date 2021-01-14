package pageobjects.houzeinvest.investor;

import commons.AbstractPage;
import commons.GlobalConstants;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static pageuis.houzeinvest.investor.AccountPageUI.*;

public class AccountPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public AccountPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    @Step("Go to account page")
    public AccountPageObject navigateToPage() {
        openPageUrl(driver, GlobalConstants.HI_URL + "/account");
        return this;
    }

    @Step("Click Verify button")
    public void clickToVerifyButton() {
        waitElementClickable(driver, VERIFY_BUTTON);
        clickToElement(driver, VERIFY_BUTTON);
    }

    @Step("Click Profile menu")
    public void clickToProfileMenu() {
        waitElementVisible(driver, PROFILE_MENU);
        clickToElement(driver, PROFILE_MENU);
    }

    @Step("Verify status equal to: {status}")
    public AccountPageObject verifyStatusEqualTo(String status) {
        boolean flag = isElementUndisplayed(driver, VERIFY_BUTTON);
        while (!flag) {
            navigateToPage();
            waitElementInvisible(driver, VERIFY_BUTTON);
            flag = isElementUndisplayed(driver, VERIFY_BUTTON);
        }
        waitElementVisible(driver, ACCOUNT_STATUS);
        verify.verifyEquals(getElementText(driver, ACCOUNT_STATUS), status);
        return this;
    }

    @Step("Verify Deposit button is displayed")
    public void verifyDepositBtnIsDisplayed() {
        waitElementVisible(driver, DEPOSIT_BUTTON);
        verify.verifyTrue(isElementDisplayed(driver, DEPOSIT_BUTTON));
    }

    @Step("Logout account")
    public void logout() {
        navigateToPage();
        clickToLogoutBtn();
        clickToLogoutSubmitBtn();
    }

    @Step("Click Logout submit button")
    public void clickToLogoutSubmitBtn() {
        waitElementClickable(driver, LOGOUT_SUBMIT_BTN);
        clickToElement(driver, LOGOUT_SUBMIT_BTN);
    }

    @Step("Click Logout button")
    public void clickToLogoutBtn() {
        waitElementClickable(driver, LOGOUT_BTN);
        clickToElementByJS(driver, LOGOUT_BTN);
    }
}
