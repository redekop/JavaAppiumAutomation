package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_NAME_TPL;

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }


    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{SUBSTRING}", name_of_folder);
    }

    private static String getArticleNameXpathByName(String name_of_folder) {
        return ARTICLE_NAME_TPL.replace("{SUBSTRING}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{SUBSTRING}", article_title);
    }


    public void openArticle(String name_of_article) {
        this.waitForElementAndClick(getArticleNameXpathByName(name_of_article), "Can not find name article", 15);
    }

    public void openFolderByName(String name_of_folder) {
        this.waitForElementAndClick(getFolderXpathByName(name_of_folder), "Cannot find folder by name " + name_of_folder, 10);
    }


    public void waitForArticleToAppearsByTitle(String article_title) {
        this.waitForElementPresent(getFolderXpathByName(article_title), "Cannot find saved article by title - " + article_title, 15);
    }


    public void waitForArticleToDisappearsByTitle(String article_title) {
        this.waitForElementNotPresent(getFolderXpathByName(article_title), "Saved article still present with title - " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearsByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(getSavedArticleXpathByTitle(article_title), "Cannot find saved article");
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRighUpperCorner(article_xpath, "Cannod find saved article");
        }
        this.waitForArticleToDisappearsByTitle(article_title);
    }
}
