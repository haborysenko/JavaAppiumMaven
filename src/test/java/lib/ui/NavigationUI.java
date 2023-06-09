package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
        MY_LISTS_LINK,
        OPEN_NAVIGATION;


    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Opening navigation button only for Mobile web")
    public void openNavigation() {
        if(Platform.getInstance().isMw()) {
            this.tryClickElementWithFewAttempts(OPEN_NAVIGATION, "Cannot find and click open navigation button",5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Clicking my list")
    public void clickMyLists()
    {
        if (Platform.getInstance().isMw()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }
}
