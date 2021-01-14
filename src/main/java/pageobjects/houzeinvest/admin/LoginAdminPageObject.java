package pageobjects.houzeinvest.admin;

import commons.AbstractPage;
import commons.GlobalConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageuis.houzeinvest.investor.LoginAdminPageUI;

public class LoginAdminPageObject extends AbstractPage {
    WebDriver driver;

    public LoginAdminPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Go to login admin page")
    public LoginAdminPageObject navigateToPage() {
        openPageUrl(driver, GlobalConstants.HI_ADMIN_URL + "/signin");
        return this;
    }

    @Step("Input username with: {username}")
    public LoginAdminPageObject inputToUsernameTextbox(String username) {
        waitElementVisible(driver, LoginAdminPageUI.USERNAME_TEXTBOX);
        sendkeyToElement(driver, LoginAdminPageUI.USERNAME_TEXTBOX, username);
        return this;
    }

    @Step("Input password with: {password}")
    public LoginAdminPageObject inputToPasswordTextbox(String password) {
        waitElementVisible(driver, LoginAdminPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginAdminPageUI.PASSWORD_TEXTBOX, password);
        return this;
    }

    @Step("Click Login button")
    public void clickToLoginButton() {
        waitElementClickable(driver, LoginAdminPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginAdminPageUI.LOGIN_BUTTON);
    }

    @Step("Login to Admin")
    public void loginToAdmin() {
        inputToUsernameTextbox(GlobalConstants.USERNAME_ADMIN_HI);
        inputToPasswordTextbox(GlobalConstants.PASSWORD_ADMIN_HI);
        clickToLoginButton();
    }
}
