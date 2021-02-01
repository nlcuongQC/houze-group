package commons;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.Keys.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfAllElements;

public abstract class AbstractPage {
    private Alert              alert;
    private WebElement         element;
    private Select             select;
    private WebDriverWait      wait;
    private JavascriptExecutor js;
    private List<WebElement>   elements;
    private Actions            actions;
    private Functions          functions;

    protected void openPageUrl(WebDriver driver, String url) {
        //driver.get(url);
        driver.navigate().to(url);
    }

    protected void setImplicitWait(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void back(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forward(WebDriver driver) {
        driver.navigate().forward();
    }

    protected void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected void acceptAlert(WebDriver driver) {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        alert.accept();
    }

    protected void cancelAlert(WebDriver driver) {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        alert.dismiss();
    }

    protected String getAlertText(WebDriver driver) {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        return alert.getText();
    }

    protected void sendkeysToAlert(WebDriver driver, String text) {
        waitAlertPresence(driver);
        alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    protected void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(windowID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    protected void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(title)) {
                break;
            }
        }
    }

    protected void closeAllWindowsWithoutID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    protected String castToObject(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    protected By byXpath(String xpathValue) {
        return By.xpath(xpathValue);
    }

    protected WebElement find(WebDriver driver, String xpathValue) {
        return driver.findElement(byXpath(xpathValue));
    }

    protected List<WebElement> finds(WebDriver driver, String xpathValue) {
        return driver.findElements(byXpath(xpathValue));
    }

    protected void clickToElement(WebDriver driver, String xpathValue) {
        try {
            find(driver, xpathValue).click();
        } catch (StaleElementReferenceException e) {
            find(driver, xpathValue).click();
        } catch (ElementClickInterceptedException e) {
            find(driver, xpathValue).click();
        }
    }

    protected void clickToElement(WebElement element) {
        try {
            element.click();
        } catch (StaleElementReferenceException e) {
            element.click();
        };
    }

    protected void clickToElement(WebDriver driver, String xpathValue, String... values) {
        try {
            find(driver, castToObject(xpathValue, values)).click();
        } catch (StaleElementReferenceException ex) {
            find(driver, castToObject(xpathValue, values)).click();
        }
    }

    protected void sendkeyToElement(WebDriver driver, WebElement ele, String text) {
        try {
            clearText(driver, ele);
            ele.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void sendkeyToElement(WebDriver driver, String xpathValue, String text) {
        try {
            element = find(driver, xpathValue);
            clearText(driver, xpathValue);
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void sendkeyToElement(WebDriver driver, String xpathValue, String text, String... values) {
        try {
            element = find(driver, castToObject(xpathValue, values));
            clearText(driver, xpathValue, values);
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void selectInDropdownByText(WebDriver driver, String xpathValue, String itemValue) {
        select = new Select(find(driver, xpathValue));
        select.selectByVisibleText(itemValue);
    }

    protected void selectInDropdownByText(WebDriver driver, String xpathValue, String itemText, String... values) {
        select = new Select(find(driver, castToObject(xpathValue, values)));
        select.selectByVisibleText(itemText);
    }

    protected void selectInDropdownByValue(WebDriver driver, String xpathValue, String itemValue) {
        select = new Select(find(driver, xpathValue));
        select.selectByValue(itemValue);
    }

    protected void selectInDropdownByValue(WebDriver driver, String xpathValue, String itemValue, String... values) {
        select = new Select(find(driver, castToObject(xpathValue, values)));
        select.selectByValue(itemValue);
    }

    protected String getSelectedItemInDropdown(WebDriver driver, String xpathValue) {
        select = new Select(find(driver, xpathValue));
        return select.getFirstSelectedOption().getText();
    }

    protected String getSelectedItemInDropdown(WebDriver driver, String xpathValue, String... values) {
        select = new Select(find(driver, castToObject(xpathValue, values)));
        return select.getFirstSelectedOption().getText();
    }

    protected Boolean isDropdownMultiple(WebDriver driver, String xpathValue) {
        select = new Select(find(driver, xpathValue));
        return select.isMultiple();
    }

    protected void selectItemsInCustomDropdown(WebDriver driver, String parentLocator, String itemLocator,
                                               String expectedItem) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        waitElementClickable(driver, parentLocator);
        clickToElement(driver, parentLocator);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(itemLocator)));
        elements = finds(driver, itemLocator);
        js       = (JavascriptExecutor) driver;
        for (WebElement item : elements) {
            String actualItem = item.getText();
            if (actualItem.equals(expectedItem)) {
                js.executeScript("arguments[0]. scrollIntoView(true);", item);
                wait.until(ExpectedConditions.elementToBeClickable(item));
                clickToElement(item);
                break;
            }
        }

    }

    protected void selectItemsInCustomDropdownByJs(WebDriver driver, String parentLocator, String itemLocator,
                                               String expectedItem) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        waitElementClickable(driver, parentLocator);
        clickToElement(driver, parentLocator);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(itemLocator)));
        elements = finds(driver, itemLocator);
        js       = (JavascriptExecutor) driver;
        for (WebElement item : elements) {
            String actualItem = item.getText();
            if (actualItem.equals(expectedItem)) {
                js.executeScript("arguments[0]. scrollIntoView(true);", item);
                wait.until(ExpectedConditions.elementToBeClickable(item));
                clickToElementByJS(driver,item);
                break;
            }
        }

    }

    //    protected void  selectItemsInCustomDropdown(WebDriver driver, String parentLocator, String itemLocator,
    //    String... values) {
    //        find(driver, parentLocator).click();
    //
    //        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
    //        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(itemLocator)));
    //        elements = finds(driver, itemLocator);
    //
    //        for (WebElement item : elements) {
    //            String actualItem = item.getText();
    //            if (actualItem.equals(expectedItem)) {
    //                js.executeScript("arguments[0]. scrollIntoView(true);", item);
    //                wait.until(ExpectedConditions.elementToBeClickable(item));
    //                item.click();
    //                break;
    //            }
    //        }
    //    }

    protected void deselectAllItemsInCustomDropdown(WebDriver driver, String parentLocator, String deselectLocator) {
        find(driver, parentLocator).click();
        waitElementVisible(driver, deselectLocator);
        find(driver, deselectLocator).click();
    }

    protected String getElementAttribute(WebDriver driver, String xpathValue, String attributeName) {
        return find(driver, xpathValue).getAttribute(attributeName);
    }

    protected String getElementAttribute(WebDriver driver, String xpathValue, String attributeName, String... values) {
        return find(driver, castToObject(xpathValue, values)).getAttribute(attributeName);
    }

    protected String getElementText(WebDriver driver, String xpathValue) {
        try {
            return find(driver, xpathValue).getText();
        } catch (StaleElementReferenceException e) {
            return find(driver, xpathValue).getText();
        }
    }

    protected String getElementText(WebDriver driver, String xpathValue, String... values) {
        return find(driver, castToObject(xpathValue, values)).getText();
    }

    protected int countElementsNumber(WebDriver driver, String xpathValue) {
        return finds(driver, xpathValue).size();
    }

    protected int countElementsNumber(WebDriver driver, String xpathValue, String... values) {
        return finds(driver, castToObject(xpathValue, values)).size();
    }

    protected void checkTheCheckbox(WebDriver driver, String xpathValue) {
        element = find(driver, xpathValue);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void checkTheCheckbox(WebDriver driver, String xpathValue, String... values) {
        element = find(driver, castToObject(xpathValue, values));
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void uncheckTheCheckbox(WebDriver driver, String xpathValue) {
        element = find(driver, xpathValue);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected Boolean isElementDisplayed(WebDriver driver, String xpathValue) {
        try {
            element = find(driver, xpathValue);
            return element.isDisplayed();
        } catch (NoSuchElementException noSuchElementException) {
            noSuchElementException.printStackTrace();
            return false;
        }
    }

    protected Boolean isElementDisplayed(WebDriver driver, String xpathValue, String... values) {
        try {
            return find(driver, castToObject(xpathValue, values)).isDisplayed();
        } catch (NoSuchElementException noSuchElementException) {
            noSuchElementException.getMessage();
            return false;
        }
    }

    protected Boolean areElementsDisplayed(WebDriver driver, String xpathValue) {
        try {
            elements = finds(driver, xpathValue);
            boolean status = true;
            for (WebElement item : elements) {
                if (!item.isDisplayed()) {
                    status = false;
                }
            }
            return status;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        } catch (StaleElementReferenceException e) {
            elements = finds(driver, xpathValue);
            boolean status = true;
            for (WebElement item : elements) {
                if (!item.isDisplayed()) {
                    status = false;
                }
            }
            return status;
        }
    }

    protected void overrideGlobalTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    protected Boolean isElementUndisplayed(WebDriver driver, String xpathValue) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        elements = finds(driver, xpathValue);
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        if (elements.size() == 0) {
            return true;
        } else if (!elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    protected Boolean isElementUndisplayed(WebDriver driver, String xpathValue, String... values) {
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        elements = finds(driver, castToObject(xpathValue, values));
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        if (elements.size() == 0) {
            return true;
        } else if (!elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    protected Boolean isElementSelected(WebDriver driver, String xpathValue) {
        return find(driver, xpathValue).isSelected();
    }

    protected Boolean isElementSelected(WebDriver driver, String xpathValue, String... values) {
        return find(driver, castToObject(xpathValue, values)).isSelected();
    }

    protected Boolean isElementEnabled(WebDriver driver, String xpathValue) {
        return find(driver, xpathValue).isEnabled();
    }

    protected Boolean isElementDisabled(WebDriver driver, String xpathValue) {
        return !find(driver, xpathValue).isEnabled();
    }


    protected void switchToFrameOrIframe(WebDriver driver, String xpathValue) {
        driver.switchTo().frame(find(driver, xpathValue));
    }

    protected void switchToDefaultContents(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    protected void hoverToElement(WebDriver driver, String xpathValue) {
        actions = new Actions(driver);
        actions.moveToElement(find(driver, xpathValue)).perform();
    }

    protected void hoverToElement(WebDriver driver, String xpathValue, String... values) {
        actions = new Actions(driver);
        actions.moveToElement(find(driver, castToObject(xpathValue, values))).perform();
    }

    protected void doubleClickToElement(WebDriver driver, String xpathValue) {
        actions = new Actions(driver);
        actions.doubleClick(find(driver, xpathValue)).perform();
    }

    protected void sendKeyboardToElement(WebDriver driver, String xpathValue, Keys keys) {
        actions = new Actions(driver);
        actions.sendKeys(find(driver, xpathValue), keys).perform();
    }

    protected void sendKeyboardToElement(WebDriver driver, WebElement element, Keys keys) {
        actions = new Actions(driver);
        actions.sendKeys(element, keys).perform();
    }

    protected void sendKeyboardToElement(WebDriver driver, String xpathValue, Keys keys, String... values) {
        actions = new Actions(driver);
        actions.sendKeys(find(driver, castToObject(xpathValue, values)), keys).perform();
    }

    protected void highlightElement(WebDriver driver, String xpathValue) {
        element = find(driver, xpathValue);
        scrollToElement(driver, xpathValue);
        String originalStyle = element.getAttribute("style");
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
                         "border: 5px solid red; border-style: dashed;");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void clearElement(WebDriver driver, String xpathValue) {
        find(driver, xpathValue).clear();
    }

    protected void clearElement(WebDriver driver, String xpathValue, String... values) {
        find(driver, castToObject(xpathValue, values)).clear();
    }

    protected void clickToElementByJS(WebDriver driver, String xpathValue) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", find(driver, xpathValue));
    }

    protected void clickToElementByJS(WebDriver driver, String xpathValue, String values) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", find(driver, castToObject(xpathValue, values)));
    }

    protected void clickToElementByJS(WebDriver driver, WebElement element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    protected void scrollToElement(WebDriver driver, String xpathValue) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", find(driver, xpathValue));
    }

    protected void sendkeyToElementByJS(WebDriver driver, String xpathValue, String attributeName) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '" + attributeName + "')", find(driver, xpathValue));
    }

    protected void sendkeyToElementByJS(WebDriver driver, String xpathValue, String attributeName, String... values) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '" + attributeName + "')",
                         find(driver, castToObject(xpathValue, values)));
    }

    protected void sendkeyToElementByJS(WebDriver driver, WebElement element, String attributeName) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', '" + attributeName + "')", element);
    }

    protected void removeAttributeInDOM(WebDriver driver, String xpathValue, String attributeName) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('" + attributeName + "');", find(driver, xpathValue));
    }

    protected void removeAttributeInDOM(WebDriver driver, String xpathValue, String attributeName, String... values) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('" + attributeName + "');",
                         find(driver, castToObject(xpathValue, values)));
    }

    protected void setAttributeValue(WebDriver driver, String xpathValue, String attributeName, String value) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + value + "');",
                         find(driver, xpathValue));
    }

    protected void setAttributeValue(WebDriver driver, String xpathValue, String attributeName, String value,
                                     String values) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + value + "');",
                         find(driver, castToObject(xpathValue, values)));
    }

    protected void setAttributeValue(WebDriver driver, WebElement element, String attributeName, String value) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + value + "');", element);
    }

    protected boolean isImageLoaded(WebDriver driver, String xpathValue) {
        js = (JavascriptExecutor) driver;
        boolean status = (boolean) js.executeScript(
                "return arguments[0].complete " + "&& typeof arguments[0].naturalWidth != \"undefined\" && " +
                "arguments[0].naturalWidth >0", find(driver, xpathValue));
        return status;
    }

    protected void waitElementVisible(WebDriver driver, String xpathValue) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(xpathValue)));
        } catch (TimeoutException e) {
            e.printStackTrace();
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void waitElementVisible(WebDriver driver, String xpathValue, String... values) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(xpathValue, values))));
        } catch (TimeoutException e) {
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void waitElementsVisible(WebDriver driver, String xpathValue) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(xpathValue)));
        } catch (TimeoutException e) {
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void waitElementsVisible(WebDriver driver, String xpathValue, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(castToObject(xpathValue, values))));
    }

    protected void waitElementClickable(WebDriver driver, String xpathValue) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(byXpath(xpathValue)));
        } catch (TimeoutException e) {
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void waitElementClickable(WebDriver driver, WebElement element) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void waitElementClickable(WebDriver driver, String xpathValue, String... values) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(xpathValue, values))));
        } catch (TimeoutException e) {
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void waitElementsPresence(WebDriver driver, String xpathValue) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(xpathValue)));
        } catch (TimeoutException e) {
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void waitAlertPresence(WebDriver driver) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    protected void waitElementsInvisible(WebDriver driver, String xpathValue) {
        wait     = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        elements = finds(driver, xpathValue);
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        wait.until(invisibilityOfAllElements(elements));
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
    }

    protected void waitElementInvisible(WebDriver driver, String xpathValue) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(xpathValue)));
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        } catch (TimeoutException e) {
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void waitElementInvisible(WebDriver driver, String xpathValue, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToObject(xpathValue, values))));
        overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
    }

    protected void waitTextElementVisible(WebDriver driver, String xpathValue, String text) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(byXpath(xpathValue), text));
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        } catch (TimeoutException e) {
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void waitTextElementVisible(WebDriver driver, String xpathValue, String text, String... values) {
        try {
            wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
            overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(byXpath(castToObject(xpathValue, values)), text));
            overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
        } catch (TimeoutException e) {
            functions = Functions.getFunctions(driver);
            functions.saveScreenshot();
        }
    }

    protected void waitTextElementChanged(WebDriver driver, String xpathValue, String text) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOfElementWithText(byXpath(xpathValue), text));
    }

    protected void waitTextElementChanged(WebDriver driver, String xpathValue, String text, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOfElementWithText(byXpath(castToObject(xpathValue, values)), text));
    }

    protected void waitAttributeElement(WebDriver driver, String xpathValue, String attribute, String value) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.attributeContains(byXpath(xpathValue), attribute, value));
    }

    protected Boolean isElementsSortedInAlphabetAscending(WebDriver driver, String xpathValue) {
        wait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
        ArrayList<String> elementTextList = new ArrayList<>();
        elements = finds(driver, xpathValue);
        for (WebElement element : elements) {
            elementTextList.add(element.getText());
        }

        ArrayList<String> sortedElementTextList = new ArrayList<>(elementTextList);

        Collections.sort(sortedElementTextList);
        return sortedElementTextList.equals(elementTextList);
    }

    protected boolean isPageIsDisplayedByPageTitle(WebDriver driver, String pageTitle) {
        return getPageTitle(driver).equals(pageTitle);
    }

    protected Boolean isElementsSortedInAlphabetDescending(WebDriver driver, String xpathValue) {
        ArrayList<String> elementTextList = new ArrayList<>();
        elements = finds(driver, xpathValue);
        for (WebElement element : elements) {
            elementTextList.add(element.getText());
        }

        ArrayList<String> sortedElementTextList = new ArrayList<>(elementTextList);

        Collections.sort(sortedElementTextList);
        Collections.reverse(sortedElementTextList);
        return sortedElementTextList.equals(elementTextList);
    }

    protected void uploadMultipleFile(WebDriver driver, String... fileName) {
        StringBuilder fullFileName = new StringBuilder();
        for (String file: fileName) {
            fullFileName = fullFileName.append(GlobalConstants.UPLOAD_FOLDER).append(file).append("\n");
        }
        fullFileName = new StringBuilder(fullFileName.toString().trim());
        sendkeyToElement(driver, AbstractPageUI.UPLOAD_FILE_TYPE, fullFileName.toString());
    }

    protected void uploadFile(WebDriver driver, String xpathValue, String fileName) {
        StringBuilder fullFileName = new StringBuilder();
        fullFileName.append(GlobalConstants.UPLOAD_FOLDER).append(fileName);
        fullFileName = new StringBuilder(fullFileName.toString().trim());
        sendkeyToElement(driver, xpathValue, fullFileName.toString());
    }

    protected void sleepInSeconds(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected int getConvertPrice(WebDriver driver, String xpathValue) {
        String        price          = getElementText(driver, xpathValue);
        StringBuilder convertedPrice = new StringBuilder();
        List<String>  number         = Arrays.asList(price.split(" "));
        number = Arrays.asList(number.get(0).split(","));
        for (String s : number) {
            convertedPrice.append(s);
        }
        return Integer.parseInt(convertedPrice.toString());
    }

    protected int getConvertPrice(WebDriver driver, String xpathValue, String... values) {
        String        price          = getElementText(driver, xpathValue, values);
        StringBuilder convertedPrice = new StringBuilder();
        List<String>  number         = Arrays.asList(price.split(" "));
        number = Arrays.asList(number.get(0).split(","));
        for (String s : number) {
            convertedPrice.append(s);
        }
        return Integer.parseInt(convertedPrice.toString());
    }

    protected void clearText(WebDriver driver, String xpathValue) {
        try {
            element = find(driver, xpathValue);
            element.clear();
            if (!element.getAttribute("value").equals("")) {
                actions = new Actions(driver);
                element.click();
                actions.keyDown(LEFT_CONTROL).sendKeys("a").keyUp(LEFT_CONTROL).sendKeys(DELETE).perform();
            }
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        }

    }

    protected void clearText(WebDriver driver, String xpathValue, String... values) {
        element = find(driver, castToObject(xpathValue, values));
        element.clear();
        if (!element.getAttribute("value").equals("")) {
            actions = new Actions(driver);
            element.click();
            actions.keyDown(LEFT_CONTROL).sendKeys("a").keyUp(LEFT_CONTROL).sendKeys(DELETE).perform();
        }
    }

    protected void clearText(WebDriver driver, WebElement ele) {
        ele.clear();
        if (!ele.getAttribute("value").equals("")) {
            actions = new Actions(driver);
            ele.click();
            actions.keyDown(LEFT_CONTROL).sendKeys("a").keyUp(LEFT_CONTROL).sendKeys(DELETE).perform();
        }
    }

    protected void outFocusElement(WebDriver driver) {
        actions = new Actions(driver);
        actions.sendKeys(TAB).perform();
    }
}
