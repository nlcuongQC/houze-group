package pageobjects.houzeinvest.investor;

import commons.AbstractPage;
import commons.VerifyHelper;
import org.openqa.selenium.WebDriver;

import static pageuis.houzeinvest.investor.TradingSellPageUI.*;

public class TradingSellPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public TradingSellPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    public void verifyTradingSellPageIsOpened() {
        waitElementVisible(driver, TITLE);
        verify.verifyTrue(isElementDisplayed(driver, TITLE));
    }

    public TradingSellPageObject choosePrj(String prjName) {
        selectItemsInCustomDropdown(driver, PRJ_DDL_PARENT, DDL_ITEMS, prjName);
        return this;
    }

    public TradingSellPageObject chooseItemType(String itemType) {
        selectItemsInCustomDropdown(driver, ITEM_TYPE_DDL_PARENT, DDL_ITEMS, itemType);
        return this;
    }

    public void verifyCreatePostSellBtnIsDisabled() {
        verify.verifyFalse(isElementEnabled(driver, CREATE_POST_SELL_BTN));
    }

    public TradingSellPageObject inputToItemAmountTxtbx(String amount) {
        waitElementVisible(driver, ITEM_AMOUNT_TXTBX);
        sendkeyToElement(driver, ITEM_AMOUNT_TXTBX, amount);
        return this;
    }

    public TradingSellPageObject inputToExpectedPrice(String price) {
        waitElementVisible(driver, EXPECTED_PRICE_TXTBX);
        sendkeyToElement(driver, EXPECTED_PRICE_TXTBX, price);
        return this;
    }

    public TradingSellPageObject verifyCreatePostSellBtnIsEnabled() {
        verify.verifyTrue(isElementEnabled(driver, CREATE_POST_SELL_BTN));
        return this;
    }

    public TradingSellPageObject clickToCreatePostSellBtn() {
        waitElementClickable(driver,CREATE_POST_SELL_BTN);
        clickToElement(driver,CREATE_POST_SELL_BTN);
        return this;
    }

    public void verifyDynamicPopupIsDisplayed(String result) {
        verify.verifyTrue(isElementDisplayed(driver, DYNAMIC_POPUP, result));
    }
}
