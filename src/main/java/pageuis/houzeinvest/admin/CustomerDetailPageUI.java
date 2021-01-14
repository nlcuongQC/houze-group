package pageuis.houzeinvest.admin;

public class CustomerDetailPageUI {
    public static final String VERIFY_SUBMIT_BUTTON = "//div[@class = 'position-relative']//span[contains(@class, " +
                                                      "'Button')]";
    public static final String REJECT_BUTTON        = "//span[text() = 'Từ chối']//parent::button";
    public static final String APPROVE_BUTTON       = "//span[text() = 'Duyệt']//parent::button";
    public static final String DYNAMIC_BREADCRUMBS  = "//li[contains(@class, 'Breadcrumbs')]/a[text() = '%s']";
}
