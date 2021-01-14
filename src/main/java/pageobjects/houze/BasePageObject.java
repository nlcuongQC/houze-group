package pageobjects.houze;

import commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class BasePageObject extends AbstractPage {
    WebDriver driver;
    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }
}
