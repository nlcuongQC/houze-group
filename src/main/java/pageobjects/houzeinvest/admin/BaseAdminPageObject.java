package pageobjects.houzeinvest.admin;

import commons.AbstractPage;
import commons.GlobalConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static pageuis.houzeinvest.admin.BaseAdminPageUI.*;

public class BaseAdminPageObject extends AbstractPage {
    WebDriver driver;

    public BaseAdminPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to notification button")
    public BaseAdminPageObject clickToNotificationButton() {
        waitElementVisible(driver, NOTIFICATION_BUTTON);
        clickToElement(driver, NOTIFICATION_BUTTON);
        return this;
    }

    @Step("Click EKYC link")
    public void clickToEKYCLink() {
        waitElementVisible(driver, EKYC_NOTIFICATION_LINK);
        clickToElement(driver, EKYC_NOTIFICATION_LINK);
    }

    @Step("Go to admin page")
    public BaseAdminPageObject navigateToPage() {
        openPageUrl(driver, GlobalConstants.HI_ADMIN_URL + "/app/property");
        return this;
    }

    @Step("Click Transaction link")
    public void clickToTransactionLink() {
        waitElementVisible(driver, TRANSACTION_NOTIFICATION_LINK);
        clickToElement(driver, TRANSACTION_NOTIFICATION_LINK);
    }

    @Step("Click {menu} menu")
    public void clickToDynamicMenu(String menu) {
        waitElementClickable(driver, DYNAMIC_MENU, menu);
        clickToElement(driver, DYNAMIC_MENU, menu);
    }
}
