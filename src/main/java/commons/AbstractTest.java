package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    protected final Log       log;
    private         WebDriver driver;

    protected AbstractTest() {
        log = LogFactory.getLog(getClass());
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String appURL) {
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        if (browser == Browser.CHROME) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            threadLocalDriver.set(driver);
        } else if (browser == Browser.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            threadLocalDriver.set(driver);
        } else if (browser == Browser.HEADLESS) {
            WebDriverManager.chromiumdriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("disable-gpu");
            //            options.addArguments("--screenshot");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
            //            threadLocalDriver.set(driver);
        } else {
            throw new RuntimeException("Please input your browser name!");
        }

        driver.get(appURL);
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    protected void closeBrowserAndDriver(WebDriver driver) {
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String cmd = "";
            if (driver != null) {
                driver.quit();
            } else if (driver.toString().toLowerCase().contains("chrome") && osName.toLowerCase().contains("window")) {
                System.out.println(driver);
                cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
            }

            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();

            log.info("---------- QUIT BROWSER SUCCESS ----------");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
