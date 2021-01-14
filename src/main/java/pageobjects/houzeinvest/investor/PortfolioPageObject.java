package pageobjects.houzeinvest.investor;

import commons.AbstractPage;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageuis.houzeinvest.investor.PortfolioPageUI;

public class PortfolioPageObject extends AbstractPage {
    WebDriver    driver;
    VerifyHelper verify;

    public PortfolioPageObject(WebDriver driver) {
        this.driver = driver;
        verify      = VerifyHelper.getVerify(driver);
    }

    private int getDynamicProjectAmount(String project) {
        waitElementsVisible(driver, PortfolioPageUI.DYNAMIC_PROJECTS, project);
        return countElementsNumber(driver, PortfolioPageUI.DYNAMIC_PROJECTS, project);
    }

    @Step("Verify {project} with amount {i}")
    public void verifyDynamicProjectAmount(String projectName, int i) {
        verify.verifyTrue(getDynamicProjectAmount(projectName) == i);
    }
}
