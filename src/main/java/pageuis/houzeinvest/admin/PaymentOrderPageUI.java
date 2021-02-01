package pageuis.houzeinvest.admin;

public class PaymentOrderPageUI {
    public static final String CREATE_PAYMENT_ORDER             = "//span[text() = 'Tạo lệnh']/parent::button";
    public static final String PAYMENT_ORDER_TYPE_DDL_PARENT    = "//div[@id = 'mui-component-select-type']";
    public static final String PAYMENT_ORDER_TYPE_DDL_ITEMS     = "//ul[@role = 'listbox']/li";
    public static final String ITEM_CODE_DDL                    = "//div[@role= 'presentation']//li";
    public static final String ITEM_CODE_TXTBX                  = "//div[@name = 'item']//input";
    public static final String RATE_TXTBX                       = "//input[@name = 'interest_rate']";
    public static final String PAYMENT_DATE_PICKER_BTN          = "//h6[text() = 'Ngày thanh " +
                                                                  "toán']/following-sibling::div//button";
    public static final String DYNAMIC_PAYMENT_DATE_PICKER_DATE = "//p[text() ='%s']/ancestor::button[not(contains" +
                                                                  "(@class, 'hidden'))]";
    public static final String DATE_PICKER_SUBMIT_BTN           = "//span[contains(text(),'OK')]/parent::button";
    public static final String SUBMIT_BTN                       = "//span[contains(text(),'Lưu')]/parent::button";
    public static final String DYNAMIC_PAYMENT_ORDER_STATUS     = "//td[text() = '%s']/..//div[contains(@class, " +
                                                                  "'badge-pill')]";
    public static final String DYNAMIC_ACTION_BTN               = "//td[text() = '%s']/following-sibling::td[@class =" +
                                                                  " 'text-right']/button";
    public static final String DYNAMIC_ACTION_ITEMS_BTN         = "//ul[@role = 'menu']/li[text() = '%s']";
    public static final String SUBMIT_ACTION_BTN                = "//span[contains(text(),'Đồng ý')]/parent::button";
}
