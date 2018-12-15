package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends  MainPageObject {

    private static final String
            MY_LIST_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }


    public void clickMyList() {
        //Переходим в списки
        this.waitForElementAndClick(MY_LIST_LINK, "Cannot find navigation button to my list", 30);

    }
}
