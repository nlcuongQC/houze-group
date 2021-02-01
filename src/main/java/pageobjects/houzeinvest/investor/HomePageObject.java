package pageobjects.houzeinvest.investor;

import commons.AbstractPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageuis.houzeinvest.investor.HomePageUI;

public class HomePageObject extends AbstractPage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click Invest now button")
    public void clickToInvestNowButton() {
        waitElementClickable(driver, HomePageUI.INVEST_NOW_BUTTON);
        clickToElement(driver, HomePageUI.INVEST_NOW_BUTTON);
    }

    @Step("Go to homepage")
    public HomePageObject navigateToHomepage() {
        openPageUrl(driver, "https://integration-houze-invest.houze.io/");
        return this;
    }
}
