package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFarcory;
import org.junit.Test;

public class MyListTests extends CoreTestCase {

    private String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyList();

        MyListsPageObject myListsPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
        }

        myListsPageObject.swipeByArticleToDelete(article_title);
    }


    @Test
    public void testSaveTwoArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        String name_of_folder = "Learning programming";

        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();


        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("JavaScript");
        searchPageObject.clickByArticleWithSubstring("Programming language");
        articlePageObject.waitForTitleElement();
        String article_title2 = articlePageObject.getArticleTitle();

        articlePageObject.openOptionMenuAddToList();
        MyListsPageObject myListsPageObject = MyListPageObjectFactory.get(driver);
        myListsPageObject.openFolderByName(name_of_folder);

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyList();
        myListsPageObject.openFolderByName(name_of_folder);

        myListsPageObject.swipeByArticleToDelete(article_title);

        myListsPageObject.openArticle(article_title2);
        assertEquals("Article titles do not match", "JavaScript", article_title2);
    }
}
