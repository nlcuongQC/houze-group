package pageuis.houzeinvest.investor;

public class AccountPageUI {

    public static final String VERIFY_BUTTON     = "//span[text() = 'Xác thực']/parent::button";
    public static final String ACCOUNT_STATUS    = "//b[text() = 'Xem chi tiết']//ancestor::div/preceding-sibling" +
                                                   "::div//span[contains(@class, 'sub')]";
    public static final String DEPOSIT_BUTTON    = "//span[text() = 'Nạp tiền']/parent::button";
    public static final String PROFILE_MENU      = "//span[text() = 'Thông tin cá nhân']/ancestor::button";
    public static final String LOGOUT_BTN        = "//span[text() = 'Đăng xuất']/../following-sibling::span";
    public static final String LOGOUT_SUBMIT_BTN = "//p[text() = 'Đăng xuất']/ancestor::button";
}
