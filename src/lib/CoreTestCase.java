package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Samsung");
        capabilities.setCapability("platformVersion", "4.4.4");
        capabilities.setCapability("automationName", "appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "E:\\GIT\\JavaAppiumAutomation\\apk\\org.wikipedia.apk");
        driver = new AndroidDriver(new URL(AppiumURL), capabilities);

        //Как вариант перед каждым тестом проверять текущую ориентацию
        ScreenOrientation screenOrientation = driver.getOrientation();
        if (screenOrientation == ScreenOrientation.LANDSCAPE) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }


    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
}
