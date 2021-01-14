package pageuis.houzeinvest.investor;

public class BasePageUI {
    public static final String LOGIN_BUTTON                = "//button[@name ='article']";
    public static final String SUBMIT_LOGIN_BUTTON         = "//p[text()= 'Đăng nhập']/../parent::button";
    public static final String PHONE_TEXTBOX               = "//input[@id='login_phone_number']";
    public static final String PASSWORD_TEXTBOX            = "//input[@id='input_password']";
    public static final String ALERT_MESSAGE               = "//*[@role = 'alert']/div[contains(@class, 'message')]";
    public static final String REGISTER_BUTTON             = "//button[@name = 'article']/." +
                                                             "./following-sibling::li//button";
    public static final String REGISTER_SUBMIT_BUTTON      = "//button[contains(@class, 'button-register')]";
    public static final String REGISTER_NAME_TEXTBOX       = "//input[@name = 'full_name']";
    public static final String REGISTER_PHONE_TEXTBOX      = "//input[@name = 'phone_number']";
    public static final String REGISTER_EMAIL_TEXTBOX      = "//input[@name = 'email']";
    public static final String REGISTER_PASSWORD_TEXTBOX   = "//input[@name = 'password']";
    public static final String REGISTER_CONDITION_CHECKBOX = "//input[@name = 'is_accepted']";
    public static final String REGISTER_PHONE_ERROR_TEXT   = "//input[@name = 'phone_number']/../." +
                                                             "./following-sibling::p";
    public static final String REGISTER_OTP_TEXTBOXES      = "//div[@role = 'presentation' and not(@aria-hidden = " +
                                                             "'true')]//input[contains(@class, 'custom-input-otp')]";
    public static final String REGISTER_OTP_ERROR_TEXT     = "//p[contains(@class, 'txt-negative')]";
    public static final String REGISTER_EMAIL_ERROR_TEXT   = "//input[@name = 'email']/../../following-sibling::p";
}
