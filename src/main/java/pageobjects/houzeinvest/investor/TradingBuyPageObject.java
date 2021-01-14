package pageobjects.houzeinvest.investor;

import commons.AbstractPage;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static pageuis.houzeinvest.investor.TradingBuyPageUI.*;

public class TradingBuyPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public TradingBuyPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    @Step("Verify Trading buy page is opened")
    public void verifyTradingBuyPageIsOpened() {
        waitElementVisible(driver, TITLE);
        verify.verifyTrue(isElementDisplayed(driver, TITLE));
    }

    @Step("Choose project: {0}")
    public TradingBuyPageObject choosePrj(String prjName) {
        waitElementClickable(driver, PRJ_DDL_PARENT);
        selectItemsInCustomDropdown(driver, PRJ_DDL_PARENT, DDL_ITEMS, prjName);
        return this;
    }

    @Step("Choose item type: {0}")
    public TradingBuyPageObject chooseItemType(String itemTypeName) {
        waitElementClickable(driver, ITEM_TYPE_DDL_PARENT);
        selectItemsInCustomDropdown(driver, ITEM_TYPE_DDL_PARENT, DDL_ITEMS, itemTypeName);
        return this;
    }

    @Step("Verify Create post buy button is disabled")
    public void verifyCreatePostBuyBtnIsDisabled() {
        verify.verifyFalse(isElementEnabled(driver, CREATE_POST_BUY_BTN));
    }

    @Step("Input {0} to Item amount")
    public TradingBuyPageObject inputToItemAmountTxtbx(String amount) {
        waitElementVisible(driver, ITEM_AMOUNT_TXTBX);
        sendkeyToElement(driver, ITEM_AMOUNT_TXTBX, amount);
        return this;
    }

    @Step("Input {0} to Expected price")
    public TradingBuyPageObject inputToExpectedPrice(String price) {
        waitElementVisible(driver,EXPECTED_PRICE_TXTBX);
        sendkeyToElement(driver, EXPECTED_PRICE_TXTBX, price);
        return this;
    }

    @Step("Verify Create post buy button is enabled")
    public TradingBuyPageObject verifyCreatePostBuyBtnIsEnabled() {
        verify.verifyTrue(isElementEnabled(driver, CREATE_POST_BUY_BTN));
        return this;
    }

    @Step("Click Create post buy button")
    public TradingBuyPageObject clickToCreatePostBuyBtn() {
        waitElementClickable(driver, CREATE_POST_BUY_BTN);
        clickToElement(driver, CREATE_POST_BUY_BTN);
        return this;
    }

    @Step("Verify {0} popup is displayed")
    public void verifyDynamicPopupIsDisplayed(String result) {
        verify.verifyTrue(isElementDisplayed(driver, DYNAMIC_POPUP, result));
    }
}
