package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }




    public void swipeElementToLeft(By by, String err_msg) {
        WebElement element = waitForElementPresent(by, err_msg, 15);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).waitAction(1000).moveTo(left_x, middle_y).release().perform();
    }


    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public List<WebElement> getListOfElements(By by) {
        List elements = driver.findElements(by);
        return elements;
    }

    public void swipeUpQick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String err_msg, int max_swipe) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped >= max_swipe) {
                waitForElementPresent(by, "sdf \n" + err_msg, 10);
            }
            swipeUpQick();
            already_swiped++;
        }
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

//    public String checkText(By by, String err_msg, long timeoutInSeconds, String attribute) {
//        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
//        String value = element.getAttribute(attribute);
//        return value;
//    }

    public WebElement waitForElementPresent(By by, String err_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> waitForAllElementsPresent(By by, String err_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        List<WebElement> elements = driver.findElements(by);
        return elements;
    }

    public WebElement waitForElementAndClick(By by, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndKeys(By by, String value, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String err_msg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(err_msg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(By by, String err_msg, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
        element.click();
        element.clear();
        return element;
    }

//    public void searchAndOpenArticle(String searchWord, String nameTitleArticle) {
//        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Can not find 'search wikipedia' input", 30);
//        waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), searchWord, "Can not find search input", 30);
//        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + nameTitleArticle + "']"),
//                "Can not find search article", 30);
//        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Can not find article title", 30);
//    }
//
//    public void openOptionMenu(String nameMenuOption) {
//        //Нажимаем кнопку с опциями
//        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "Can not find button to open options", 30);
//        //Выбираем соответствующую опцию
//        waitForElementAndClick(By.xpath("//*[@text='" + nameMenuOption + "']"), "Can not find option", 30);
//    }
//
//
//    public void createNewReadingListAndSaveArticle(String nameFolder) {
//        openOptionMenu("Add to reading list");
//        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"), "Can not find 'Go it' tip overlay", 30);
//        //Чистим поле
//        waitForElementAndClear(By.id("org.wikipedia:id/text_input"), "Can not find input to set name of article folder", 30);
//        //Заполняем название папки
//        waitForElementAndKeys(By.id("org.wikipedia:id/text_input"), "" + nameFolder + "", "Can not put text into folder input", 30);
//        //Сохраняем статью
//        waitForElementAndClick(By.xpath("//*[@text='OK']"), "Can not press 'OK' button", 30);
//    }


    public void closeArticle() {
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "Can not close article", 30);
    }


    public String getElementAttribute(By by, String attribute, String err_msg, long timeout) {
        WebElement element_title = waitForElementPresent(by, err_msg, timeout);
        return element_title.getAttribute(attribute);
    }


    public void assertElementNotPresent(By by, String err_msg) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_msg = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_msg + " " + err_msg);
        }
    }

    public void assertElementPresentWithoutWait(By by, String nameTitle, String err_msg) {
        try {
            WebElement element_title = driver.findElement(by);
            assert element_title.getAttribute("text").equals(nameTitle);
        } catch (WebDriverException ex)  {
            String default_msg = "An element '" + by.toString() + "' supposed to be visible.\n";
            throw new AssertionError(default_msg + " " + err_msg);
        }
    }

}
