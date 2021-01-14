package pageobjects.houzeinvest.admin;

import commons.AbstractPage;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageuis.houzeinvest.admin.CustomerPageUI;

public class CustomerPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public CustomerPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    @Step("Click to Customer name: {name}")
    public void clickToDynamicCustomerName(String name) {
        waitElementClickable(driver, CustomerPageUI.DYNAMIC_CUSTOMER_DETAIL_BY_NAME, name);
        clickToElement(driver, CustomerPageUI.DYNAMIC_CUSTOMER_DETAIL_BY_NAME, name);
        new CustomerDetailPageObject(driver);
    }

    @Step("Verify customer status of {name} equal to {value}")
    public void verifyCustomerStatusByDynamicName(String name, String value) {
        waitElementVisible(driver, CustomerPageUI.DYNAMIC_STATUS_BY_NAME, name);
        verify.verifyEquals(getElementText(driver, CustomerPageUI.DYNAMIC_STATUS_BY_NAME, name), value);
    }
}
