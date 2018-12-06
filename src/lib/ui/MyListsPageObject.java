package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    private static final String
            FOLDER_BY_NAME_TPL = "//*[@text='{SUBSTRING}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{SUBSTRING}']",
            ARTICLE_NAME_TPL = "//*[@text='{SUBSTRING}']";

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
        this.waitForElementAndClick(By.xpath(getArticleNameXpathByName(name_of_article)), "Can not find name article", 15);
    }

    public void openFolderByName(String name_of_folder) {
        this.waitForElementAndClick(By.xpath(getFolderXpathByName(name_of_folder)), "Cannot find folder by name " + name_of_folder, 10);
    }


    public void waitForArticleToAppearsByTitle(String article_title) {
        this.waitForElementPresent(By.xpath(getFolderXpathByName(article_title)), "Cannot find saved article by title - " +  article_title, 15);
    }


    public void waitForArticleToDisappearsByTitle(String article_title) {
        this.waitForElementNotPresent(By.xpath(getFolderXpathByName(article_title)), "Saved article still present with title - " +  article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearsByTitle(article_title);
        this.swipeElementToLeft(By.xpath(getSavedArticleXpathByTitle(article_title)), "Cannot find saved article");
        this.waitForArticleToDisappearsByTitle(article_title);
    }
}
