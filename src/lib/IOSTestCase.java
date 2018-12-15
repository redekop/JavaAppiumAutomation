package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class IOSTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();


        driver = new IOSDriver(new URL(AppiumURL), capabilities);

        this.rotateScreenPortrait();

//        //Как вариант перед каждым тестом проверять текущую ориентацию
//        ScreenOrientation screenOrientation = driver.getOrientation();
//        if (screenOrientation == ScreenOrientation.LANDSCAPE) {
//            driver.rotate(ScreenOrientation.PORTRAIT);
//        }
    }


    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }


    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        driver.runAppInBackground(seconds);
    }
}
