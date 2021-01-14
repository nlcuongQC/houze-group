package pageobjects.houze;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;
    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToUsernameTextbox(String username) {
    }

    public void inputToPasswordTextbox(String password) {
    }
}
