package pageuis.houzeinvest.investor;

public class ProfilePageUI {
    public static final String BEGIN_VERIFY_BUTTON               = "//div[@id = 'kyc_profile']//button";
    public static final String VERIFY_SUBMIT_BUTTON              = "//div[@aria-labelledby= " +
                                                                   "'simple-modal-title']/div/div/button";
    public static final String VERIFY_NAME_TEXTBOX               = "//input[@id='fullname']";
    public static final String GENDER_DROPDOWN                   = "//div[@id='gender-select']";
    public static final String GENDER_DROPDOWN_ITEMS             = "//ul[@role = 'listbox']/li/p";
    public static final String BIRTHDAY_TEXTBOX                  = "//div[@labelid= 'birth-select-label']//input";
    public static final String ADDRESS_TEXTBOX                   = "//input[@id='address']";
    public static final String FULL_ADDRESS_TEXTBOX              = "//input[@id='full-address']";
    public static final String ISSUE_DATE_TEXTBOX                = "//input[@id='issue_date']";
    public static final String ISSUE_PLACE_TEXTBOX               = "//input[@id='issue_place']";
    public static final String ISSUE_NUMBER_TEXTBOX              = "//input[@id='card']";
    public static final String ISSUE_PAPER_RADIO                 = "//input[@name = 'paper' and @value = '%s']";
    public static final String IMAGE_FRONT_UPLOAD                = "//input[@id='contained-button-file-1']";
    public static final String IMAGE_BACK_UPLOAD                 = "//input[@id='contained-button-file-2']";
    public static final String VERIFY_POPUP_TITLE                = "//p[contains(@class, 'hi-title-large')]";
    public static final String FRONT_ISSUE_IMAGE                 = "//label[@for = 'contained-button-file-1']//img";
    public static final String BACK_ISSUE_IMAGE                  = "//label[@for = 'contained-button-file-2']//img";
    public static final String VERIFY_POPUP_DONE_BUTTON          = "//p[contains(@class, 'hi-title-large')]/." +
                                                                   "./following-sibling::button";
    public static final String STATUS_PROFILE                    = "(//div[@id = 'kyc_profile']/div/div/span)[2]";
    public static final String UPDATE_PROFILE_BTN                = "//div[@id = 'info_profile']//span[text() = 'Cập " +
                                                                   "nhật']/ancestor::button";
    public static final String UPDATE_PROFILE_SAVE_BTN           = "//div[@id = 'info_profile']//span[text() = " +
                                                                   "'Lưu']/ancestor::button";
    public static final String UPDATE_PROFILE_CANCEL_BTN         = "//div[@id = 'info_profile']//span[text() = " +
                                                                   "'Lưu']/ancestor::button/following-sibling::button";
    public static final String UPDATE_PROFILE_EMAIL_TXTBX        = "//input[@id='standard-email']";
    public static final String UPDATE_PROFILE_AVATAR             = "//input[@id='image-avatar']";
    public static final String UPDATE_PROFILE_AVATAR_IMG         = "//img[@class = 'MuiAvatar-img']";
    public static final String CHANGE_PW_BTN                     = "//div[@id = 'password_profile']//span[text() = " +
                                                                   "'Cập nhật']/ancestor::button";
    public static final String CHANGE_PW_SAVE_BTN                = "//div[@id = 'password_profile']//span[text() = " +
                                                                   "'Lưu']/ancestor::button";
    public static final String CHANGE_PW_CANCEL_BTN              = "//div[@id = 'password_profile']//span[text() = " +
                                                                   "'Huỷ']/ancestor::button";
    public static final String CHANGE_PW_FILLED_PW_TXTBX         = "//input[@id='filled-password-input']";
    public static final String CHANGE_PW_CURRENT_PW_TXTBX        = "//input[@id='one']";
    public static final String CHANGE_PW_NEW_PW_TXTBX            = "//input[@id='two']";
    public static final String CHANGE_PW_CONFIRM_NEW_PW_TXTBX    = "//input[@id='three']";
    public static final String UPDATE_PROFILE_EMAIL_VALIDATE_TXT = "//p[@id='phone-number-helper']";
    public static final String CHANGE_PW_NOT_MATCH_ERR           = "//label[text() = 'Xác nhận mật khẩu mới']/." +
                                                                   "./following-sibling::div";
}
