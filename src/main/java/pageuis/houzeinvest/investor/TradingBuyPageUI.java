package pageuis.houzeinvest.investor;

public class TradingBuyPageUI {
    public static final String TITLE                = "//h1[@class = 'title']";
    public static final String PRJ_DDL_PARENT       = "//p[text() = 'Chọn dự án']/following-sibling::div/button";
    public static final String DDL_ITEMS            = "//ul[@id='custom-menu-list']/li//p[contains(@class, 'view')]";
    public static final String ITEM_TYPE_DDL_PARENT = "//p[text() = 'Chọn Gói chứng " +
                                                      "chỉ']/following-sibling::div/button";
    public static final String CREATE_POST_BUY_BTN  = "//p[text() = 'Tạo tin Mua']/ancestor::button";
    public static final String ITEM_AMOUNT_TXTBX    = "//input[@id='input-quantity']";
    public static final String EXPECTED_PRICE_TXTBX = "//input[@id='input-amount']";
    public static final String DYNAMIC_POPUP        = "//div[@id='modal-result-wrapper']/p[contains(text(), '%s')]";
}
