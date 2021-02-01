package pageuis.houzeinvest.investor;

public class CheckoutPageUI {
    public static final String REQUEST_LOGIN_MESSAGE            = "//p[contains(@class, 'row-two')]/b";
    public static final String NEXT_BUTTON                      = "//button[text() = 'Tiếp theo']";
    public static final String DYNAMIC_ITEMTYPE_TEXTBOX         = "//div[text() = '%s']/." +
                                                                  "./following-sibling::div//input";
    public static final String TOTAL_ITEMS                      = "//div[contains(., ' chứng chỉ đầu tư')]/b";
    public static final String DYNAMIC_ITEMTYPE_PRICE           = "//div[text() = '%s']/." +
                                                                  "./following-sibling::div//div/span";
    public static final String TOTAL_PRICE                      = "//div[contains(text(),'Tổng số tiền đầu tư')]//span";
    public static final String DYNAMIC_ITEM_AMOUNT              = "//div[text() = '%s']/../following-sibling::div//div";
    public static final String DYNAMIC_CONFIRM_ITEMTYPE_PRICE   = "//div[text() = '%s']/." +
                                                                  "./following-sibling::div//span";
    public static final String CONTRACT_CHECKBOX                = "//input[@name = 'checkedA']";
    public static final String OTP_TEXTBOXES                    = "//input[@type = 'tel']";
    public static final String SUBMIT_INVEST_BUTTON             = "//button[text() = 'Xác nhận']";
    public static final String POPUP_INVEST_SUBMIT_TITLE        = "//div[contains(text(), 'thành công'])";
    public static final String POPUP_FAILED_INVEST_SUBMIT_TITLE = "//div[text() = 'Mua chứng chỉ thất bại...']";
    public static final String VIEW_PORTFOLIO_BUTTON            = "//span[text() = 'Xem danh mục']/parent::button";
    public static final String DO_AGAIN_BUTTON                  = "//span[text() = 'Thực hiện lại']/parent::button";
}
