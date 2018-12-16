package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }


    private static final String
        STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
        STEP_LINK = "id:Next",
        STEP_STARTED_BUTTON = "id:Get started",
        SKIP = "id:Skip";

    public void waitForLearnMoreLink() {
        this.waitForAllElementsPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 20);
    }

    public void waitForNewWayToExploreText() {
        this.waitForAllElementsPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' link", 20);
    }


    public void waitForAddOrEditPreferredLangText() {
        this.waitForAllElementsPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "Cannot find 'Add or edit preferred languages' link", 20);
    }


    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForAllElementsPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Learn more about data collected' link", 20);
    }


    public void clickNextButton() {
        this.waitForElementAndClick(STEP_LINK, "Cannot find and click 'Next' link", 10);
    }


    public void clickGetStartedButton() {
        this.waitForElementAndClick(STEP_STARTED_BUTTON, "Cannot find and click 'Get started' link", 10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP, "Cannot find and click skip button", 10);
    }



}




