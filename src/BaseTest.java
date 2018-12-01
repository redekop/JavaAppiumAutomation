import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class BaseTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Samsung");
        capabilities.setCapability("platformVersion", "4.4.4");
        capabilities.setCapability("automationName", "appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "E:\\GIT\\JavaAppiumAutomation\\apk\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    protected void swipeElementToLeft(By by, String err_msg) {
        WebElement element = waitForElementPresent(by, err_msg, 15);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();

        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).waitAction(1000).moveTo(left_x, middle_y).release().perform();
    }


    protected int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    protected void swipeUpQick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String err_msg, int max_swipe) {
        int already_swiped = 0;
        while(driver.findElements(by).size() == 0){
            if (already_swiped >= max_swipe) {
                waitForElementPresent(by, "sdf \n"+err_msg,  10);
            }

            swipeUpQick();
            already_swiped++;
        }
    }

    protected void swipeUp (int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();

        int x = size.width/2;
        int start_y = (int) (size.height*0.8);
        int end_y = (int) (size.height*0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    protected String checkText(By by, String err_msg, long timeoutInSeconds, String attribute) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        String value = element.getAttribute(attribute);
        return value;
    }

    protected WebElement waitForElementPresent(By by, String err_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected List<WebElement> waitForAllElementsPresent(By by, String err_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        List<WebElement> elements = driver.findElements(by);
        return elements;
    }

    protected WebElement waitForElementAndClick(By by, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.click();
        return element;
    }

    protected WebElement waitForElementAndKeys(By by, String value, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    protected boolean waitForElementNotPresent(By by, String err_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected WebElement waitForElementAndClear(By by, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void searchAndOpenArticle(String searchWord, String nameTitleArticle) {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Can not find 'search wikipedia' input", 20);
        waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), searchWord,"Can not find search input", 7);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+nameTitleArticle+"']"),
                "Can not find search article", 30);
        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Can not find article title", 15);
    }

    protected void openOptionMenu(String nameMenuOption) {
        //Нажимаем кнопку с опциями
        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "Can not find button to open options", 30);
        //Выбираем соответствующую опцию
        waitForElementAndClick(By.xpath("//*[@text='"+nameMenuOption+"']"), "Can not find option", 15);
    }


    protected void createNewReadingListAndSaveArticle(String nameFolder) {
        openOptionMenu("Add to reading list");
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"), "Can not find 'Go it' tip overlay", 15);
        //Чистим поле
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"), "Can not find input to set name of article folder", 15);
        //Заполняем название папки
        waitForElementAndKeys(By.id("org.wikipedia:id/text_input"), ""+nameFolder+"","Can not put text into folder input", 15);
        //Сохраняем статью
        waitForElementAndClick(By.xpath("//*[@text='OK']"), "Can not press 'OK' button", 15);
    }


    protected void closeArticle() {
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Can not close article", 15);
    }




    protected String getTitleArticle() {
        WebElement element_title = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "sdfsfsf", 15);
        return element_title.getAttribute("text");
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
