import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

public class FirstTest extends CoreTestCase {

    private String someWord = "word";

    private MainPageObject mainPageObject;


    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearct() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }


    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }


    @Test
    public void testCompareArcicleTitle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String article_title = articlePageObject.getArticleTitle();

        Assert.assertEquals("gtrtedref", "Java (programming language)", article_title);

    }


    @Test
    public void testSwipeArcicle() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();

    }




    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyList();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);

        myListsPageObject.swipeByArticleToDelete(article_title);




    }


    @Test
    public void testAmountOfNotEmptySearch() {
        String seacrcLine = "Linkin park Discography";
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']";
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 20);
        mainPageObject.waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), seacrcLine, "error_input", 7);
        mainPageObject.waitForElementPresent(By.xpath(searchResultLocator), "Can not find anything by request " + seacrcLine, 20);
        int amountOfSearchResults = mainPageObject.getAmountOfElements(By.xpath(searchResultLocator));
        Assert.assertTrue("We found too few results!", amountOfSearchResults > 0);

    }


    @Test
    public void testAmountOfEmptySearch() {
        String seacrcLine = "dfghjklasdfertyuio";
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String emptyResultLabel = "//*[@text='No results found']";
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 20);
        mainPageObject.waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), seacrcLine, "error_input", 7);
        mainPageObject.waitForElementPresent(By.xpath(emptyResultLabel), "Can not find empty result", 20);
        mainPageObject.assertElementNotPresent(By.xpath(searchResultLocator), "We have found some results result by request " + seacrcLine);

    }


    @Test
    public void testAssertTitleTest() {
        String search_word = "JavaScript";
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 20);
        mainPageObject.waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), search_word, "error_input", 7);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
                "err_id_search", 30);
        mainPageObject.assertElementPresentWithoutWait(By.id("org.wikipedia:id/view_page_title_text"), search_word, "Title should be - " + search_word);

    }


    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        mainPageObject.searchAndOpenArticle("Java", "Object-oriented programming language");
        String title_before_rotation = mainPageObject.getElementAttribute(
                By.id("org.wikipedia:id/view_page_title_text"), "text", "Can not find title of article", 15);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = mainPageObject.getElementAttribute(
                By.id("org.wikipedia:id/view_page_title_text"), "text", "Can not find title of article", 15);
        Assert.assertEquals("article title have been change after screen rotation", title_before_rotation, title_after_rotation);
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = mainPageObject.getElementAttribute(
                By.id("org.wikipedia:id/view_page_title_text"), "text", "Can not find title of article", 15);
        Assert.assertEquals("article title have been change after screen rotation", title_before_rotation, title_after_second_rotation);
    }


    @Test
    public void testCheckSearchArticleInBackgraund() {
        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 20);
        mainPageObject.waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "error_input", 7);
        mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "err_id_search", 30);
        driver.runAppInBackground(2);
        mainPageObject.waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "err_id_search", 30);
    }


    @Test
    public void testSaveTwoArticleToMyList() {
        String listName = "Learning programming";
        //Сохраняем две статьи в одну папку
        mainPageObject.searchAndOpenArticle("Java", "Object-oriented programming language");
        mainPageObject.createNewReadingListAndSaveArticle("Learning programming");
        mainPageObject.closeArticle();
        mainPageObject.searchAndOpenArticle("JavaScript", "Programming language");
        mainPageObject.openOptionMenu("Add to reading list");
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='" + listName + "']"), "Can not find list with name " + listName + "", 30);
        mainPageObject.closeArticle();
        //Удаляем одну из статей в списке
        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"), "Can not press 'My lists'", 15);
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='" + listName + "']"), "Can not find list with name " + listName + "", 30);
        mainPageObject.swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"), "Can not delete article");
        mainPageObject.waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"), "element not found", 15);
        mainPageObject.waitForElementPresent(By.xpath("//*[@text='JavaScript']"), "Can not find name article", 15);
        //Переходим в оставшуюся статью
        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='JavaScript']"), "Can not find name article", 15);
        Assert.assertEquals("Article titles do not match",
                "JavaScript",
                mainPageObject.getElementAttribute(By.id("org.wikipedia:id/view_page_title_text"), "text", "Can not find attribute", 15));
    }



    //    @Test
//    public void testCheckTextSearch() {
//        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 10);
//        Assert.assertEquals("err", "Search…", mainPageObject.checkText(
//                By.id("org.wikipedia:id/search_src_text"),
//                "err_text",
//                10,
//                "text"));
//    }
//    @Test
//    public void testSearchSomeWord() {
//        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err click on search", 10);
//        mainPageObject.waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Some word","error input value", 10);
//
//        assert mainPageObject.waitForAllElementsPresent(By.id("org.wikipedia:id/page_list_item_container"), "", 10).size() >= 1;
//
//        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "err_id_close_btn", 10);
//
//        mainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/page_list_item_container"), "search_close_btn is visible", 10);
//    }


//    @Test
//    public void testConteinsSomeWord() {
//        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err click on search", 10);
//        mainPageObject.waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), someWord, "error input value", 10);
//        List<WebElement> elements = mainPageObject.waitForAllElementsPresent(By.id("org.wikipedia:id/page_list_item_title"), "err", 10);
//        for (WebElement element : elements) {
//            assert element.getText().toUpperCase().contains(someWord.toUpperCase());
//        }
//
//    }

}
