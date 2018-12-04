package tests;

import lib.CoreTestCase;

public class FirstTest extends CoreTestCase {

    private String someWord = "word";



//    @Test
//    public void testSaveTwoArticleToMyList() {
//        String listName = "Learning programming";
//        //Сохраняем две статьи в одну папку
//        mainPageObject.searchAndOpenArticle("Java", "Object-oriented programming language");
//        mainPageObject.createNewReadingListAndSaveArticle("Learning programming");
//        mainPageObject.closeArticle();
//        mainPageObject.searchAndOpenArticle("JavaScript", "Programming language");
//        mainPageObject.openOptionMenu("Add to reading list");
//        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='" + listName + "']"), "Can not find list with name " + listName + "", 30);
//        mainPageObject.closeArticle();
//        //Удаляем одну из статей в списке
//        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"), "Can not press 'My lists'", 15);
//        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='" + listName + "']"), "Can not find list with name " + listName + "", 30);
//        mainPageObject.swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"), "Can not delete article");
//        mainPageObject.waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"), "element not found", 15);
//        mainPageObject.waitForElementPresent(By.xpath("//*[@text='JavaScript']"), "Can not find name article", 15);
//        //Переходим в оставшуюся статью
//        mainPageObject.waitForElementAndClick(By.xpath("//*[@text='JavaScript']"), "Can not find name article", 15);
//        assertEquals("Article titles do not match",
//                "JavaScript",
//                mainPageObject.getElementAttribute(By.id("org.wikipedia:id/view_page_title_text"), "text", "Can not find attribute", 15));
//    }
//


    //    @Test
//    public void testCheckTextSearch() {
//        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 10);
//        assertEquals("err", "Search…", mainPageObject.checkText(
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

//    @Test
//    public void testAssertTitleTest() {
//        String search_word = "JavaScript";
//        mainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "err_id_search", 20);
//        mainPageObject.waitForElementAndKeys(By.xpath("//*[contains(@text, 'Search…')]"), search_word, "error_input", 7);
//        mainPageObject.waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
//                "err_id_search", 30);
//        mainPageObject.assertElementPresentWithoutWait(By.id("org.wikipedia:id/view_page_title_text"), search_word, "Title should be - " + search_word);
//    }

}
