package pageuis.houzeinvest.admin;

public class PropertyDetailPageUI {

    public static final String ACCEPT_PRJ_BTN        = "//span[contains(text(),'Xác nhận dự án')]/parent::button";
    public static final String APPROVE_BTN           = "//span[contains(text(),'Duyệt')]/parent::button";
    public static final String DYNAMIC_POPUP_BTN     = "//span[contains(text(),'%s')]/parent::button";
    public static final String PROPERTY_STATUS       = "//div[contains(@class, 'avatar-info')]/div";
    public static final String ADD_ITEM_TYPE_BTN     = "//th[@class = 'text-right']/button";
    public static final String ITEM_TYPE_DDL_PARENT  = "//div[@id='mui-component-select-type']";
    public static final String ITEM_TYPE_DDL_ITEMS   = "//ul[@role = 'listbox']/li";
    public static final String TERM_TXTBX            = "//input[@name = 'term']";
    public static final String COMMITMENT_RATE_TXTBX = "//input[@name = 'interest_rate_commitment']";
    public static final String FLEXIBLE_RATE_TXTBX   = "//input[@name = 'interest_rate_flexible']";
    public static final String SAVE_BTN              = "//div[@class = 'position-relative']/button";
    public static final String ADD_IMG_BTN           = "//h4[text() = 'Hình ảnh']/following-sibling::button";
    public static final String IMAGES_UPLOADED       = "//img[@class = 'uploadPicture']";
    public static final String SUBMIT_BTN            = "//div[@class = 'position-relative']/button";
    public static final String IMAGES                = "//div[@class = 'jr-tabs-classic']//li//img";
    public static final String SUCCESS_PRJ_BTN       = "//span[text() = 'Gọi vốn thành công']/parent::button";
    public static final String FINISH_PRJ_BTN        = "//span[text() = 'Dự án đã hoàn thành']/parent::button";
}
