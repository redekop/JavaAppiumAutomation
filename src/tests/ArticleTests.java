package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFarcory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArcicleTitle() {
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = articlePageObject.getArticleTitle();

        assertEquals("gtrtedref", "Java (programming language)", article_title);
    }

    @Test
    public void testSwipeArcicle() {
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }


    @Test
    public void testAssertTitleTest() {
        String search_word = "Java";
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.assertElementPresent(search_word);

    }

}
