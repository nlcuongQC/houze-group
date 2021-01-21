package pageobjects.houzeinvest.admin;

import commons.AbstractPage;
import commons.GlobalConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageuis.houzeinvest.admin.BaseAdminPageUI;

public class BaseAdminPageObject extends AbstractPage {
    WebDriver driver;

    public BaseAdminPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to notification button")
    public BaseAdminPageObject clickToNotificationButton() {
        waitElementVisible(driver, BaseAdminPageUI.NOTIFICATION_BUTTON);
        clickToElement(driver, BaseAdminPageUI.NOTIFICATION_BUTTON);
        return this;
    }

    @Step("Click EKYC link")
    public void clickToEKYCLink() {
        waitElementVisible(driver, BaseAdminPageUI.EKYC_NOTIFICATION_LINK);
        clickToElement(driver, BaseAdminPageUI.EKYC_NOTIFICATION_LINK);
    }

    @Step("Go to admin page")
    public BaseAdminPageObject navigateToPage() {
        openPageUrl(driver, GlobalConstants.HI_ADMIN_URL + "/app/property");
        return this;
    }

    @Step("Click Transaction link")
    public void clickToTransactionLink() {
        waitElementVisible(driver, BaseAdminPageUI.TRANSACTION_NOTIFICATION_LINK);
        clickToElement(driver, BaseAdminPageUI.TRANSACTION_NOTIFICATION_LINK);
    }
}
