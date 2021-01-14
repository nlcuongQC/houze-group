package pageuis.houzeinvest.investor;

public class WalletPageUI {
    public static final String ADD_BANK_BTN                      = "//button[contains(@class, 'add-bank')]";
    public static final String ADD_BANK_NAME_TXTBX               = "//input[@id='input-owner-name']";
    public static final String ADD_BANK_ACCOUNT_NUMBER_TXTBX     = "//input[@id='input-account-number']";
    public static final String ADD_BANK_NEXT_BTN                 = "//div/button[contains(@class, 'btn-next')]";
    public static final String OTP_TXTBXES                       = "//input[contains(@class, 'custom-input-otp')]";
    public static final String WITHDRAW_TXTBX                    = "//input[@id = 'input-pay-in' and contains" +
                                                                   "(@placeholder, 'rút')]";
    public static final String DEPOSIT_TXTBX                     = "//input[@id = 'input-pay-in' and contains" +
                                                                   "(@placeholder, 'nạp')]";
    public static final String CREATE_DEPOSIT_ORDER_BTN          = "//p[text() = 'Tạo lệnh nạp tiền']/ancestor::button";
    public static final String CREATE_WITHDRAW_ORDER_BTN         = "//p[text() = 'Tạo lệnh rút tiền']/ancestor::button";
    public static final String ADD_BANK_DROPDOWN_PARENT          = "//div[@class = 'column " +
                                                                   "new-bank-wrapper']//p/following-sibling::div" +
                                                                   "/button";
    public static final String ADD_BANK_DROPDOWN_ITEMS           = "//ul[@id = 'custom-menu-list']/li/p";
    public static final String BANK_INFO_NAME_TXT                = "//p[contains(@class, 'bank-name')]";
    public static final String BANK_INFO_OWNER_TXT               = "(//p[contains(@class, 'bank-name')" +
                                                                   "]/following-sibling::p)[1]";
    public static final String BANK_INFO_ACCOUNT_NUMBER_TXT      = "(//p[contains(@class, 'bank-name')" +
                                                                   "]/following-sibling::p)[2]";
    public static final String DEPOSIT_BANK_PARENT               = "//p[text() = 'Chọn ngân " +
                                                                   "hàng']/following-sibling::div/button";
    public static final String DEPOSIT_BANK_ITEMS                = "//ul[@id = 'custom-menu-list']/li/p";
    public static final String DEPOSIT_POPUP_BANK_NAME           =
            "//div[contains(@class, 'pop-up-deposit')]//h2[text() " + "= 'NGÂN HÀNG']/..//h1";
    public static final String DEPOSIT_POPUP_AMOUNT              =
            "//div[contains(@class, 'pop-up-deposit')]//h2[text() " + "= 'SỐ TIỀN CHUYỂN KHOẢN']/..//h1";
    public static final String BANK_DELETE_BTN                   = "//p[contains(text(),'Xoá')]/ancestor::button";
    public static final String DELETE_BANK_SUBMIT_BTN            = "//p[contains(text(),'Xác nhận xóa')" +
                                                                   "]/ancestor::button";
    public static final String DELETE_BANK_BACK_BTN              = "//p[contains(text(),'Quay lại')]/ancestor::button";
    public static final String BANK_INFO                         = "//p[contains(text(),'Xoá')" +
                                                                   "]//ancestor::div[contains(@class, 'bank-info')]";
    public static final String CREATE_WITHDRAW_ORDER_APPROVE_BTN = "//div[@class = 'wallet-modals-wrapper']//button" +
                                                                   "[contains(@class, 'secondary-button')]";
    public static final String WITHDRAW_INFO_AMOUNT              = "//p[text() = 'SỐ TIỀN CẦN " +
                                                                   "RÚT']/following-sibling::p";
    public static final String AVAILABLE_BALANCE                 = "//p[text() = 'Số dư khả " +
                                                                   "dụng']/following-sibling::div/h2";
    public static final String BUY_TRADING_ITEM_AMOUNT           = "//p[text() = 'Tiền rao mua chứng " +
                                                                   "chỉ']/following-sibling::div/h2";
    public static final String WITHDRAW_PROCESSING_AMOUNT        = "//p[text() = 'Tiền rút đang xử " +
                                                                   "lý']/following-sibling::div/h2";
    public static final String TOTAL_BALANCE                     = "//p[text() = 'Tổng số " +
                                                                   "dư']/following-sibling::div/h2";
    public static final String OTP_VALIDATE_TXT                  = "//p[contains(@class, 'negative')]";
}
