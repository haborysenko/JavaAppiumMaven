package lib;

import io.appium.java_client.AppiumDriver;

import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase {

    protected RemoteWebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.openWikiPageForMobileWeb();
    }

    @After
    public void tearDown() {

        driver.quit();
    }

    protected void rotateScreenPortrait() {
        if(Platform.getInstance().isIOS() ||Platform.getInstance().isAndroid()) {
            if (driver instanceof AppiumDriver) {
                AppiumDriver driver = (AppiumDriver) this.driver;
                driver.rotate(ScreenOrientation.PORTRAIT);
            } else {
                System.out.println("Method rotateScreenPortrait() does nothing for platform: " + Platform.getInstance().getPlatformVar());
            }
        }
    }

    protected void rotateScreenLandscape() {
        if(Platform.getInstance().isIOS() ||Platform.getInstance().isAndroid()) {
            if(driver instanceof AppiumDriver) {
                AppiumDriver driver = (AppiumDriver) this.driver;
                driver.rotate(ScreenOrientation.LANDSCAPE);
            } else {
                System.out.println("Method rotateScreenLandscape() does nothing for platform: "+ Platform.getInstance().getPlatformVar());
            }
        }
    }

    protected void backgroundApp(int seconds) {
        if(driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("Method backgroundApp(int seconds) does nothing for platform: "+ Platform.getInstance().getPlatformVar());
        }
    }

    protected void openWikiPageForMobileWeb() {
        if(Platform.getInstance().isMw()) {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiPageForMobileWeb() does nothing for platform: "+ Platform.getInstance().getPlatformVar());
        }
        }

    private void skipWelcomePageForIOSApp() {
        if(Platform.getInstance().isIOS()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
}