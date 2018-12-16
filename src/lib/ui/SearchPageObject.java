package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_SRC_TEXT,
            SEARCH_PAGE_LIST_TITLE;


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }


    /* TEMPLATES METHODS */
    private static String getResultSearchElemant(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public String checkSearchText() {
        return getElementAttribute(SEARCH_SRC_TEXT, "text","err_text", 20);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 10);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 10);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button is still present", 10);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 10);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndKeys(SEARCH_INPUT, search_line,"Cannot find and type into search input", 10);
    }

    public void waitForSearchResult(String substring) {
        waitForElementPresent(getResultSearchElemant(substring),
                "Cannot find search result with substring - " + substring, 15);
    }


    public void clickByArticleWithSubstring(String substring) {
        waitForElementAndClick(getResultSearchElemant(substring),
                "Cannot find and click search result with substring - " + substring, 15);
    }


    public int getAmountOfFoundArticle() {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Can not find anything by request ", 20);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void checkWordsInAllListTitle (String word) {
        List<WebElement> elements = this.getListOfElements(SEARCH_PAGE_LIST_TITLE);
        for (WebElement element : elements) {
            assert element.getText().toUpperCase().contains(word.toUpperCase());
        }
    }

    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any result");
    }








}
