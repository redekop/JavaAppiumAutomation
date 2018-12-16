package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{SUBSTRING}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{SUBSTRING}']";
        ARTICLE_NAME_TPL = "/xpath:/*[@text='{SUBSTRING}']";
    }


    public AndroidMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
