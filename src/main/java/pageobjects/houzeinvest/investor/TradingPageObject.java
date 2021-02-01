package pageobjects.houzeinvest.investor;

import commons.AbstractPage;
import commons.GlobalConstants;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

import static pageuis.houzeinvest.investor.TradingPageUI.*;

public class TradingPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public TradingPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    @Step("Navigate to trading page")
    public TradingPageObject navigateToPage() {
        openPageUrl(driver, GlobalConstants.HI_URL + "/trading");
        return this;
    }

    @Step("Click {0} channel button")
    public TradingPageObject clickToDynamicChannelBtn(String btnName) {
        btnName = btnName.toUpperCase(Locale.ROOT);
        waitElementClickable(driver, DYNAMIC_CHANNEL_BTN, btnName);
        clickToElement(driver, DYNAMIC_CHANNEL_BTN, btnName);
        return this;
    }

    @Step("Verify Post button text is: {0}")
    public TradingPageObject verifyPostBtnTxtIsChangedTo(String txt) {
        waitTextElementVisible(driver, DYNAMIC_POST_BTN, txt);
        verify.verifyTrue(isElementDisplayed(driver, DYNAMIC_POST_BTN, txt));
        return this;
    }

    @Step("Click Post button")
    public void clickToPostBtn() {
        waitElementClickable(driver, DYNAMIC_POST_BTN, "Mua");
        clickToElement(driver, DYNAMIC_POST_BTN, "Mua");
    }

    @Step("Click Buy now button of project {0}")
    public void clickToDynamicBuyNowBtn(String prjName) {
        waitElementClickable(driver, DYNAMIC_BUY_NOW_BTN, prjName);
        clickToElement(driver, DYNAMIC_BUY_NOW_BTN, prjName);
    }
}
