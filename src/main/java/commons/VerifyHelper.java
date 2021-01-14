package commons;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class VerifyHelper {
    WebDriver driver;

    Functions functions;

    public VerifyHelper(WebDriver driver) {
        this.driver = driver;
        functions = new Functions(driver);
    }

    public static VerifyHelper getVerify(WebDriver driver) {
        return new VerifyHelper(driver);
    }

    private boolean checkTrue(WebDriver driver, boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            functions.saveScreenshot();
        }
        return pass;
    }

    public boolean verifyTrue(boolean condition) {
        return checkTrue(driver, condition);
    }

    private boolean checkFalse(WebDriver driver, boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            functions.saveScreenshot();
        }
        return pass;
    }

    public boolean verifyFalse(boolean condition) {
        return checkFalse(driver, condition);
    }

    private boolean checkEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            pass = false;
            e.printStackTrace();
            functions.saveScreenshot();
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    public boolean verifyEquals(Object actual, Object expected) {
        return checkEquals(actual, expected);
    }
}
