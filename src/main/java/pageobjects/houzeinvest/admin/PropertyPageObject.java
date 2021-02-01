package pageobjects.houzeinvest.admin;

import commons.AbstractPage;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static pageuis.houzeinvest.admin.PropertyPageUI.*;

public class PropertyPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public PropertyPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    @Step("Click Create property button")
    public PropertyPageObject clickToCreatePropertyBtn() {
        waitElementClickable(driver, CREATE_PROPERTY_BTN);
        clickToElement(driver, CREATE_PROPERTY_BTN);
        return this;
    }

    @Step("Create project {prjName} with code {prjCode}")
    public PropertyPageObject createAPrj(String prjName, String prjCode) {
        inputToDynamicCreatePropertyTxtbx("name", prjName);
        inputToDynamicCreatePropertyTxtbx("project_code", prjCode);
        chooseProductType("Căn hộ");
        chooseDynamicCombobox("PropertyID", "Kỳ Co Gateway");
        chooseDynamicCombobox("Chủ đầu tư", "Houze");
        chooseDynamicCombobox("Tỉnh - Thành phố", "Thành phố Hồ Chí Minh");
        chooseDynamicCombobox("Quận - Huyện", "Quận 10");
        chooseDynamicCombobox("Phường - Xã", "Phường 05");
        inputToDynamicCreatePropertyTxtbx("street_line", "653 Ba Tháng Hai");
        inputToDynamicCreatePropertyTxtbx("info.gfa", "50000");
        inputToDynamicCreatePropertyTxtbx("info.project_area", "50000");
        inputToDynamicCreatePropertyTxtbx("info.saleable_area", "50000");
        inputToDynamicCreatePropertyTxtbx("info.unit_area_range", "50000");
        inputToDynamicCreatePropertyTxtbx("info.unit_count", "200");
        inputToDynamicCreatePropertyTxtbx("info.unit_avg_price", "2000000");
        inputToDynamicCreatePropertyTxtbx("info.unit_avg_price", "2000000");
        inputToDynamicCreatePropertyTxtbx("total_investment", "5000000000");
        inputToDynamicCreatePropertyTxtbx("quantity", "2000");
        chooseDynamicDate("start_datetime", "1");
        chooseDynamicDate("end_datetime", "20");
        chooseDynamicDate("complete_datetime", "25");
        clickSubmitBtn();
        return this;
    }

    @Step("Choose {date} with day {day}")
    private void chooseDynamicDate(String date, String day) {
        clickToElementByJS(driver, DYNAMIC_CREATE_PROPERTY_TXTBX, date);
        waitElementClickable(driver, DYNAMIC_DATE_PICKER_DAY, day);
        clickToElement(driver, DYNAMIC_DATE_PICKER_DAY, day);
        waitElementClickable(driver, DATE_PICKER_SUBMIT);
        clickToElement(driver, DATE_PICKER_SUBMIT);
    }

    @Step("Click submit button")
    private void clickSubmitBtn() {
        waitElementClickable(driver, SUBMIT_BTN);
        clickToElement(driver, SUBMIT_BTN);
    }

    @Step("Choose combobox {comboboxName} with value {value}")
    public void chooseDynamicCombobox(String comboboxName, String value) {
        waitElementVisible(driver, DYNAMIC_COMBOBOX, comboboxName);
        sendkeyToElement(driver, DYNAMIC_COMBOBOX, value, comboboxName);
        waitElementVisible(driver, DYNAMIC_COMBOBOX_ITEMS, value);
        clickToElementByJS(driver, DYNAMIC_COMBOBOX_ITEMS, value);
    }

    @Step("Choose Product type {0}")
    public void chooseProductType(String type) {
        selectItemsInCustomDropdownByJs(driver, PRODUCT_TYPE_DDL_PARENT, PRODUCT_TYPE_DDL_ITEMS, type);
    }

    @Step("Input {value} to {txtbxName} field")
    public void inputToDynamicCreatePropertyTxtbx(String txtbxName, String value) {
        waitElementVisible(driver, DYNAMIC_CREATE_PROPERTY_TXTBX, txtbxName);
        sendkeyToElement(driver, DYNAMIC_CREATE_PROPERTY_TXTBX, value, txtbxName);
    }

    @Step("Verify Create property form is displayed")
    public PropertyPageObject verifyCreatePropertyFormIsDisplayed() {
        waitElementVisible(driver, CREATE_PROPERTY_FORM);
        verify.verifyTrue(isElementDisplayed(driver, CREATE_PROPERTY_FORM));
        return this;
    }

    @Step("Verify Create property form is disappeared")
    public PropertyPageObject verifyCreatePropertyFormIsDisappeared() {
        waitElementInvisible(driver, CREATE_PROPERTY_FORM);
        verify.verifyTrue(isElementUndisplayed(driver, CREATE_PROPERTY_FORM));
        return this;
    }

    @Step("Open Property detail of {prjName}")
    public void openPropertyDetail(String prjName) {
        clickToActionBtn(prjName);
        clickToDynamicActionItemBtn("Chi tiết");
    }

    @Step("Click {type} button")
    public void clickToDynamicActionItemBtn(String type) {
        waitElementClickable(driver, DYNAMIC_ACTION_ITEMS_BTN, type);
        clickToElement(driver, DYNAMIC_ACTION_ITEMS_BTN, type);
    }

    @Step("Click Action button")
    public void clickToActionBtn(String prjName) {
        waitElementClickable(driver, DYNAMIC_ACTION_BTN, prjName);
        clickToElement(driver, DYNAMIC_ACTION_BTN, prjName);
    }
}
