package pageobjects.houzeinvest.admin;

import commons.AbstractPage;
import commons.VerifyHelper;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

import static pageuis.houzeinvest.admin.PropertyDetailPageUI.*;

public class PropertyDetailPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public PropertyDetailPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    public void clickToAcceptPrjBtn() {
        waitElementClickable(driver, ACCEPT_PRJ_BTN);
        clickToElement(driver, ACCEPT_PRJ_BTN);
    }

    public void clickToApproveBtn() {
        waitElementClickable(driver, APPROVE_BTN);
        clickToElement(driver, APPROVE_BTN);
    }

    public PropertyDetailPageObject acceptPrj() {
        clickToAcceptPrjBtn();
        clickToDynamicPopupBtn("Đồng ý");
        return this;
    }

    private void clickToDynamicPopupBtn(String text) {
        waitElementClickable(driver, DYNAMIC_POPUP_BTN, text);
        clickToElement(driver, DYNAMIC_POPUP_BTN, text);
    }

    public PropertyDetailPageObject verifyPropertyStatusEqualTo(String status) {
        waitTextElementVisible(driver, PROPERTY_STATUS, status);
        verify.verifyEquals(getElementText(driver, PROPERTY_STATUS), status.toUpperCase(Locale.ROOT));
        return this;
    }

    public PropertyDetailPageObject approvePrj() {
        clickToApproveBtn();
        clickToDynamicPopupBtn("Đồng ý");
        return this;
    }

    public void clickAddItemTypeBtn() {
        waitElementClickable(driver, ADD_ITEM_TYPE_BTN);
        clickToElement(driver, ADD_ITEM_TYPE_BTN);
    }

    public PropertyDetailPageObject addItemType(String type, String rate) {
        clickAddItemTypeBtn();
        selectItemsInCustomDropdown(driver, ITEM_TYPE_DDL_PARENT, ITEM_TYPE_DDL_ITEMS, type);
        sendkeyToElement(driver, TERM_TXTBX, "6");
        if (type.equals("Cố định")) {
            sendkeyToElement(driver, COMMITMENT_RATE_TXTBX, rate);
        } else if (type.equals("Linh hoạt")) {
            sendkeyToElement(driver, FLEXIBLE_RATE_TXTBX, rate);
        }
        clickToElement(driver, SAVE_BTN);
        return this;
    }

    public PropertyDetailPageObject addItemType(String type, String rate1, String rate2) {
        clickAddItemTypeBtn();
        selectItemTypeDDL(type);
        sendkeyToElement(driver, TERM_TXTBX, "6");
        sendkeyToElement(driver, COMMITMENT_RATE_TXTBX, rate1);
        sendkeyToElement(driver, FLEXIBLE_RATE_TXTBX, rate2);
        clickToElement(driver, SAVE_BTN);
        return this;
    }

    public void selectItemTypeDDL(String type) {
        waitElementVisible(driver, ITEM_TYPE_DDL_PARENT);
        selectItemsInCustomDropdown(driver, ITEM_TYPE_DDL_PARENT, ITEM_TYPE_DDL_ITEMS, type);
    }

    public void inputToTermTxtbx(String value) {
        waitElementVisible(driver, TERM_TXTBX);
        sendkeyToElement(driver, TERM_TXTBX, value);
    }

    public PropertyDetailPageObject addImages() {
        waitElementClickable(driver, ADD_IMG_BTN);
        clickToElement(driver, ADD_IMG_BTN);
        uploadMultipleFile(driver, "a.jpg", "b.jpg", "c.jpg", "d.jpg", "e.jpg");
        waitElementsVisible(driver, IMAGES_UPLOADED);
        verify.verifyTrue(areElementsDisplayed(driver, IMAGES_UPLOADED));
        waitElementClickable(driver, SUBMIT_BTN);
        clickToElement(driver, SUBMIT_BTN);
        return this;
    }

    public void verifyImagesAreUploaded() {
        waitElementsVisible(driver, IMAGES);
        verify.verifyEquals(countElementsNumber(driver, IMAGES), 5);
    }

    public PropertyDetailPageObject successProject() {
        waitElementClickable(driver, SUCCESS_PRJ_BTN);
        clickToElement(driver, SUCCESS_PRJ_BTN);
        waitElementClickable(driver, SUBMIT_BTN);
        clickToElement(driver, SUBMIT_BTN);
        return this;
    }

    public PropertyDetailPageObject finishPrj() {
        waitElementClickable(driver, FINISH_PRJ_BTN);
        clickToElement(driver, FINISH_PRJ_BTN);
        waitElementClickable(driver, SUBMIT_BTN);
        clickToElement(driver, SUBMIT_BTN);
        return this;
    }
}
