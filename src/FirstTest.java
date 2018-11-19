import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import static java.lang.Thread.sleep;

public class FirstTest {

    private AppiumDriver driver;


    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "GoogleNexus5");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "E:\\GIT\\JavaAppiumAutomation\\apk\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }


    @Test
    public void firstTest() {
            waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "error_search", 7);
            waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java","error_input", 7);
            waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                    "error_element", 15);
    }


    @Test
    public void testCancelSearch() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 10);
        waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java","error_input", 7);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "sdf", 10);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "err_id_close_btn", 10);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "search_close_btn is visible", 10);
    }


    @Test
    public void testCompareArcicleTitle() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 10);
        waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java","error_input", 7);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "err_id_search", 10);

        WebElement element_title = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "sdfsfsf", 15);
        String article_title = element_title.getAttribute("text");
        Assert.assertEquals("gtrtedref","Java (programming language)",article_title);

    }


    @Test
    public void testCheckTextSearch() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 10);

        Assert.assertEquals("err","Search…",checkText(
                By.id("org.wikipedia:id/search_src_text"),
                "err_text",
                10,
                "text"));
    }


    private String checkText(By by, String err_msg, long timeoutInSeconds, String attribute) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        String value = element.getAttribute(attribute);
        return value;
    }



    private WebElement waitForElementPresent(By by, String err_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }



    private WebElement waitForElementAndClick(By by, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndKeys(By by, String value, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }



    private boolean waitForElementNotPresent(By by, String err_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }


    private WebElement waitForElementAndClear(By by, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.clear();
        return element;
    }





    @After
    public void tearDown() {
        driver.quit();
    }
}
