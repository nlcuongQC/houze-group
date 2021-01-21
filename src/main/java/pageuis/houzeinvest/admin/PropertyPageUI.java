package pageuis.houzeinvest.admin;

public class PropertyPageUI {

    public static final String CREATE_PROPERTY_BTN           = "//div[@class = 'app-wrapper']//button[contains" +
                                                               "(@class, 'bg-success')]";
    public static final String CREATE_PROPERTY_FORM          = "//div[@id = 'alert-dialog-title']/parent::form";
    public static final String DYNAMIC_CREATE_PROPERTY_TXTBX = "//input[@name = '%s']";
    public static final String DYNAMIC_COMBOBOX_ITEMS        = "//div[@role = 'presentation']//li[text() = '%s']";
    public static final String DYNAMIC_COMBOBOX              = "//span[text() = '%s']/following-sibling::div//input";
    public static final String PRODUCT_TYPE_DDL_PARENT       = "//div[@id='mui-component-select-type']";
    public static final String PRODUCT_TYPE_DDL_ITEMS        = "//div[@id='menu-type']//li";
    public static final String SUBMIT_BTN                    = "//span[contains(text(),'Lưu')]";
    public static final String DYNAMIC_DATE_PICKER_DAY       = "//p[text() = '%s']/ancestor::button[not(contains" +
                                                               "(@class, 'hidden'))]";
    public static final String DATE_PICKER_SUBMIT            = "//span[contains(text(),'OK')]/parent::button";
    public static final String DYNAMIC_ACTION_BTN            = "//a[text() = '%s']/../following-sibling::td/button";
    public static final String DYNAMIC_ACTION_ITEMS_BTN      = "//li[text() = '%s']";
}
