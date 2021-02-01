package pageuis.houzeinvest.admin;

public class PollPageUI {
    public static final String CREATE_POLL_BTN         = "//span[text() = 'Tạo biểu quyết']/parent::button";
    public static final String CREATE_POLL_FORM        = "//form";
    public static final String PRJ_COMBOBX             = "//div[@name = 'property']//input";
    public static final String DYNAMIC_PRJ_ITEM        = "//li[@role = 'option']";
    public static final String OK_BTN                  = "//span[contains(text(),'OK')]/parent::button";
    public static final String DYNAMIC_DAY             = "//p[text() = '%s']/ancestor::button[not(contains(@class, " +
                                                         "'hidden'))]";
    public static final String DYNAMIC_DATEPICKER_BTN  = "//input[@name = '%s_datetime']/following-sibling::div/button";
    public static final String DEFAULT_VOTE_DDL_PARENT = "//div[@id='mui-component-select-default_vote']";
    public static final String DEFAULT_VOTE_DDL_ITEMS  = "//div[@id='menu-default_vote']//li";
    public static final String DESCRIPTION_TXTBX       = "//div[@role = 'textbox']/p";
    public static final String SAVE_BTN                = "//span[contains(text(),'Lưu')]/parent::button";
    public static final String DYNAMIC_POLL            = "//td[contains(text(), '%s')]";
    public static final String DYNAMIC_ACTION_BTN      = "//td[contains(text(), '%s')]/following-sibling::td[@class =" +
                                                         " 'text-right']";
    public static final String DYNAMIC_ACTION_ITEM     = "//li[@role = 'menuitem']";
    public static final String SUBMIT_BTN              = "//span[contains(text(),'Đồng ý')]/parent::button";
    public static final String DYNAMIC_POLL_STATUS     = "//td[contains(text(), '%s')" +
                                                         "]/following-sibling::td/div[contains(@class, 'badge')]";
}
