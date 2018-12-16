package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListPageObject extends MyListsPageObject {

    static {
        //FOLDER_BY_NAME_TPL = "xpath://*[@text='{SUBSTRING}']";
        ARTICLE_BY_TITLE_TPL = "xpath:/XCUIElementtypeLink[contains(@name='{SUBSTRING}')]";
        //ARTICLE_NAME_TPL = "/xpath:/*[@text='{SUBSTRING}']";
    }


    public IOSMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
