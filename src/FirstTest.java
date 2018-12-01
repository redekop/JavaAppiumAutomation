import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;

public class FirstTest extends BaseTest {

    private String someWord = "word";


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


    @Test
    public void testSearchSomeWord() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err click on search", 10);
        waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Some word","error input value", 10);

        assert waitForAllElementsPresent(By.id("org.wikipedia:id/page_list_item_container"), "", 10).size() >= 1;

        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "err_id_close_btn", 10);

        waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_container"), "search_close_btn is visible", 10);
    }


    @Test
    public void testConteinsSomeWord() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err click on search", 10);
        waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), someWord,"error input value", 10);

        List <WebElement> elements = waitForAllElementsPresent(By.id("org.wikipedia:id/page_list_item_title"), "err", 10);

        for(WebElement element : elements){
            assert element.getText().toUpperCase().contains(someWord.toUpperCase());
        }

    }



    @Test
    public void testSwipeArcicle() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 10);
        waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Appium","error_input", 7);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "err_id_search", 10);

        WebElement element_title = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "sdfsfsf", 15);
        element_title.getAttribute("text");

        swipeUpToFindElement(By.xpath("//*[@text='View page in browser1']"), "err", 20);

    }


    @Test
    public void saveFirstArticleToMyList() {
        //ищем нужную статью, открываем
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 20);
        waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java","error_input", 7);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "err_id_search", 30);
         waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "sdfsfsf", 15);

        //Добавляем статью в папку
        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "errr", 30);
        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"), "errrrrr", 15);
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"), "errrr", 15);
        //чистим поле
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"), "errr", 15);
        //заполняем
        waitForElementAndKeys(By.id("org.wikipedia:id/text_input"), "Learning programming","errr", 15);
        //Сохраняем статью
        waitForElementAndClick(By.xpath("//*[@text='OK']"), "errrrrr", 15);

        //Закрываем статью
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "errr", 15);
        //Переходим в списки
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"), "errr", 15);
        //Удаляем список
        waitForElementAndClick(By.xpath("//*[@text='Learning programming']"), "errrrrr", 30);

        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"), "sdfsfsf");
        waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"), "sdfsfsf", 15);
    }



    @Test
    public void testAmountOfNotEmptySearch() {
        String seacrcLine = "Linkin park Discography";
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']";

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 20);
        waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), seacrcLine,"error_input", 7);
        waitForElementPresent(By.xpath(searchResultLocator), "Can not find anything by request " + seacrcLine, 20);

        int amountOfSearchResults = getAmountOfElements(By.xpath(searchResultLocator));

        Assert.assertTrue("We found too few results!", amountOfSearchResults>0);

    }


    @Test
    public void saveTwoArticleToMyList() {
        String listName = "Learning programming";

        //Сохраняем две статьи в одну папку
        searchAndOpenArticle("Java", "Object-oriented programming language");
        createNewReadingListAndSaveArticle("Learning programming");
        closeArticle();

        searchAndOpenArticle("JavaScript", "Programming language");
        openOptionMenu("Add to reading list");
        waitForElementAndClick(By.xpath("//*[@text='"+listName+"']"),"Can not find list with name "+listName+"", 30);
        closeArticle();


        //Удаляем одну из статей в списке
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"), "Can not press 'My lists'", 15);
        waitForElementAndClick(By.xpath("//*[@text='"+listName+"']"), "Can not find list with name "+listName+"", 30);
        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"), "Can not delete article");
        waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"), "element not found", 15);
        waitForElementPresent(By.xpath("//*[@text='JavaScript']"), "Can not find name article", 15);

        //Переходим в оставшуюся статью
        waitForElementAndClick(By.xpath("//*[@text='JavaScript']"), "Can not find name article", 15);

        Assert.assertEquals("Article titles do not match","JavaScript",getTitleArticle());
    }


}
