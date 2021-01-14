package pageuis.houzeinvest.admin;

public class BasePageUI {
    public static final String NOTIFICATION_BUTTON           = "//i[contains(@class, 'noti')]//ancestor::button";
    public static final String EKYC_NOTIFICATION_LINK        = "//a[contains(@href, 'verify')]";
    public static final String TRANSACTION_NOTIFICATION_LINK = "//a[@class = 'media' and contains(@href, " +
                                                               "'transaction-order')]";
}
