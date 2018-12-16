package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.android.AndroidAtriclePageObject;
import lib.ui.ios.IOSAtriclePageObject;

public class ArticlePageObjectFactory {


    public static ArticlePageObject get(AppiumDriver driver) {

        if (Platform.getInstance().isAndroid()) {
            return new AndroidAtriclePageObject(driver);
        } else {
            return new IOSAtriclePageObject(driver);
        }
    }
}
