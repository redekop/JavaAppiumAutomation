package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFarcory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }


    @Test
    public void testSearchByTitleAndDescription() {
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        assert searchPageObject.getAmountOfFoundArticle() >= 3 : "The result should have more articles!";
        searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
    }


    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }



    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        String seacrcLine = "Linkin park Discography";
        searchPageObject.typeSearchLine(seacrcLine);
        int amountOfSearchResults = searchPageObject.getAmountOfFoundArticle();

        assertTrue("We found too few results!", amountOfSearchResults > 0);

    }


    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        String seacrcLine = "dfghjklasdfertyuio";
        searchPageObject.typeSearchLine(seacrcLine);
        searchPageObject.waitForEmptyResultLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }


    @Test
    public void testCheckTextSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        assertEquals("err", "Search…", searchPageObject.checkSearchText());
    }


    @Test
    public void testSearchSomeWord() {
        String someWord = "word";
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(someWord);

        assert searchPageObject.getAmountOfFoundArticle() >= 1;
        searchPageObject.clickCancelSearch();
        searchPageObject.initSearchInput();
    }


    @Test
    public void testCheckConteinsSomeWord() {
        String someWord = "word";
        SearchPageObject searchPageObject = SearchPageObjectFarcory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(someWord);
        searchPageObject.checkWordsInAllListTitle(someWord);
    }





}
