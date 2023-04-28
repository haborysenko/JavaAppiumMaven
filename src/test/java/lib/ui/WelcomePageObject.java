package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_ABOUT_WIKIPEDIA_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
            STEP_FREE_ENCYCLOPEDIA = "id:The free encyclopedia",
            STEP_NEW_WAYS_TO_EXPLORE_TITLE = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANGS_LINK = "xpath://XCUIElementTypeStaticText[@name='Add or edit preferred languages']",
            STEP_LEARN_MORE_ABOUT_DATE_COLLECTED_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about data collected']",
            NEXT_LINK = "xpath://XCUIElementTypeButton[@name='Next']",
            GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name='Get started']",
            SKIP = "xpath://XCUIElementTypeStaticText[@name='Skip']";

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for learn more link to appear only for iOS")
    public void waitForLearnMoreLink() {
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_WIKIPEDIA_LINK,
                "Cannot find element 'Learn more about Wikipedia'"
        );
    }

    @Step("Waiting for new way to explore test to appear only for iOS")
    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE_TITLE,
                "Cannot find element 'New ways to explore'"
        );
    }

    @Step("Waiting for add or edit preferred language link to appear only for iOS")
    public void waitForAddOrEditPreferredLangsLink() {
        this.waitForElementPresent(
                STEP_ADD_OR_EDIT_PREFERRED_LANGS_LINK,
                "Cannot find element 'Add or edit preferred languages'"
        );
    }

    @Step("Waiting for about data collected link to appear only for iOS")
    public void waitForAboutDataCollectedLink() {
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_DATE_COLLECTED_LINK,
                "Cannot find element 'Learn more about data collected'"
        );
    }

    @Step("Waiting for free encyclopedia text to appear only for iOS")
    public void waitForFreeEncyclopedia() {
        this.waitForElementPresent(
                STEP_FREE_ENCYCLOPEDIA,
                "Cannot find element 'The free encyclopedia'"
        );
    }

    @Step("Clicking next button")
    public void clickNextButton() {
        this.waitForElementAndClick(
                NEXT_LINK,
                "Cannot find and click button 'Next'"
        );
    }

    @Step("Clicking get started button")
    public void clickGetStartedButton() {
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Cannot find button and click 'Get started'"
        );
    }

    @Step("Tapping next button by coordinates")
    public void tapNextButtonByCoordinates() {
        this.tapByCoordinate(290,755);
    }

    @Step("Clicking skip button")
    public void clickSkip() {
        this.waitForElementAndClick(
                SKIP,
                "Cannot find and click button 'Skip'"
        );
    }
}
