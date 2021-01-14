package pageobjects.houzeinvest.admin;

import commons.AbstractPage;
import commons.GlobalConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageuis.houzeinvest.admin.BasePageUI;

public class BaseAdminPageObject extends AbstractPage {
    WebDriver driver;

    public BaseAdminPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to notification button")
    public BaseAdminPageObject clickToNotificationButton() {
        waitElementVisible(driver, BasePageUI.NOTIFICATION_BUTTON);
        clickToElement(driver, BasePageUI.NOTIFICATION_BUTTON);
        return this;
    }

    @Step("Click EKYC link")
    public void clickToEKYCLink() {
        waitElementVisible(driver, BasePageUI.EKYC_NOTIFICATION_LINK);
        clickToElement(driver, BasePageUI.EKYC_NOTIFICATION_LINK);
    }

    @Step("Go to admin page")
    public BaseAdminPageObject navigateToPage() {
        openPageUrl(driver, GlobalConstants.HI_ADMIN_URL + "/app/property");
        return this;
    }

    @Step("Click Transaction link")
    public void clickToTransactionLink() {
        waitElementVisible(driver, BasePageUI.TRANSACTION_NOTIFICATION_LINK);
        clickToElement(driver, BasePageUI.TRANSACTION_NOTIFICATION_LINK);
    }
}
