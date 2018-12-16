package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }



    public void clickElementToTheRighUpperCorner(String locator, String err_msg) {
        WebElement element = this.waitForElementPresent(locator + "/..", err_msg, 10);
        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;
        int width = element.getSize().getWidth();

        int point_to_click_x = (right_x + width) - 3;
        int point_to_click_y = middle_y;

        TouchAction action = new TouchAction(driver);
        action.tap(point_to_click_x, point_to_click_y).perform();


    }


    public void swipeElementToLeft(String locator, String err_msg) {
        WebElement element = waitForElementPresent(locator, err_msg, 15);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y);
        action.waitAction(1000);

        if(Platform.getInstance().isAndroid()) {
            action.moveTo(left_x, middle_y);
        } else {
            int offset_x = (-1*element.getSize().getWidth());
            action.moveTo(offset_x, 0);
        }

        action.release();
        action.perform();
    }


    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public List<WebElement> getListOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements;
    }

    public void swipeUpQick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String err_msg, int max_swipe) {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped >= max_swipe) {
                waitForElementPresent(locator, "sdf \n" + err_msg, 10);
            }
            swipeUpQick();
            already_swiped++;
        }
    }

    public void SwipeUpTillElementAppear(String locator, String err_msg, int max_swipes) {
        int already_swiped = 0;

        while (!this.isElementLocatedOnTheScreen(locator)) {
            if(already_swiped>max_swipes) {
                Assert.assertTrue(err_msg, isElementLocatedOnTheScreen(locator));
            }
            swipeUpQick();
            ++already_swiped;
        }

    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 10).getLocation().getY();
        int screen_syze_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_syze_by_y;
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }


    public WebElement waitForElementPresent(String locator, String err_msg, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> waitForAllElementsPresent(String locator, String err_msg, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        List<WebElement> elements = driver.findElements(by);
        return elements;
    }

    public WebElement waitForElementAndClick(String locator, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, err_msg, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndKeys(String locator, String value, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, err_msg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String err_msg, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(String locator, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, err_msg, timeoutInSeconds);
        element.click();
        element.clear();
        return element;
    }



    public String getElementAttribute(String locator, String attribute, String err_msg, long timeout) {
        WebElement element_title = waitForElementPresent(locator, err_msg, timeout);
        return element_title.getAttribute(attribute);
    }


    public void assertElementNotPresent(String locator, String err_msg) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_msg = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(default_msg + " " + err_msg);
        }
    }

    public void assertElementPresentWithoutWait(String locator, String nameTitle, String err_msg) {
        By by = this.getLocatorByString(locator);
        try {
            WebElement element_title = driver.findElement(by);
            assert element_title.getAttribute("text").equals(nameTitle);
        } catch (WebDriverException ex)  {
            String default_msg = "An element '" + by.toString() + "' supposed to be visible.\n";
            throw new AssertionError(default_msg + " " + err_msg);
        }
    }

    private By getLocatorByString(String locator_with_type) {

        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        }else if (by_type.equals("id")) {
            return By.id(locator);
        }else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }

}
