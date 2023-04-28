package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="GetStarted")})
    @DisplayName("Go through welcome flow for iOS")
    @Description("In this test we're checking welcome flow for iOS till clicking get started")
    @Step("Starting testPassThoughWelcome()")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testPassThoughWelcome() {
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMw()) {
            return;
        }

        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForNewWaysToExploreText();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForAddOrEditPreferredLangsLink();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForAboutDataCollectedLink();
        WelcomePageObject.clickGetStartedButton();
    }
}
