package pageobjects.houzeinvest.investor;

import commons.AbstractPage;
import commons.VerifyHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static pageuis.houzeinvest.investor.ProjectPageUI.*;

public class ProjectPageObject extends AbstractPage {
    WebDriver driver;
    VerifyHelper verify;

    public ProjectPageObject(WebDriver driver) {
        this.driver = driver;
        verify = VerifyHelper.getVerify(driver);
    }

    @Step("Click Project {projectName}")
    public void clickToProject(String projectName) {
        waitElementVisible(driver, DYNAMIC_PROJECT, projectName);
        clickToElement(driver, DYNAMIC_PROJECT, projectName);
    }

    @Step("Verify Projects are displayed")
    public ProjectPageObject verifyProjectsAreDisplayed() {
        waitElementsVisible(driver, PROJECTS);
        verify.verifyTrue(areElementsDisplayed(driver, PROJECTS));
        return this;
    }
}
