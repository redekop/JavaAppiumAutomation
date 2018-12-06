package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTION_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 30);
    }

    public String getArticleTitle() {
        WebElement element_title = waitForTitleElement();
        return element_title.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find and end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder) {
        //Добавляем статью в папку
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON), "Can not find button to open options", 30);
        this.waitForElementAndClick(By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON), "Can not find option", 30);
        this.waitForElementAndClick(By.id(ADD_TO_MY_LIST_OVERLAY), "Can not find 'Go it' tip overlay", 30);
        //чистим поле
        this.waitForElementAndClear(By.id(MY_LIST_NAME_INPUT), "Can not find input to set name of article folder", 30);
        //заполняем
        this.waitForElementAndKeys(By.id(MY_LIST_NAME_INPUT), name_of_folder, "Can not put text into folder input", 30);
        //Сохраняем статью
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON), "Can not press 'OK' button", 30);
    }

    public void closeArticle() {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON), "Can not close article", 30);
    }


    public void openOptionMenuAddToList() {
        //Нажимаем кнопку с опциями
        waitForElementAndClick(By.xpath(OPTIONS_BUTTON), "Can not find button to open options", 30);
        //Выбираем соответствующую опцию
        waitForElementAndClick(By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON), "Can not find option", 15);
    }

    public void assertElementPresent(String search_word) {
        this.assertElementPresentWithoutWait(By.id(TITLE), search_word, "Title should be - " + search_word);
    }
}
