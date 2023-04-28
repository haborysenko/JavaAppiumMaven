package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            TITLE_BY_SUBSTRING_TPL,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /*TEMPLATE METHODS*/
    private static String getTitleElementWithSubstring(String substring) {
        return TITLE_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATE METHODS*/

    @Step("Waiting for article title")
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(
                TITLE,
                "Cannot find article title on page",
                30);
    }

    @Step("Waiting for article title with text '{substring}'")
    public WebElement waitForTitleElementWithSubstring(String substring) {
        String title_element_xpath = getTitleElementWithSubstring(substring);
        return this.waitForElementPresent(
                title_element_xpath,
                "Cannot find article title on page",
                30);
    }

    @Step("Getting article title")
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if(Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    @Step("Swiping article to the footer")
    public void swipeToFooter() {
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of articles while swipe",
                    40);
        } else if(Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of articles while swipe",
                    40);
        } else {
            this.scrollWebPageTillElementVisisble(
                    FOOTER_ELEMENT,
                    "Cannot find the end of articles while swipe",
                    40
            );
        }

    }

    @Step("Adding article to the new folder with name '{name_of_folder}'")
    public void addArticleToMyNewList(String name_of_folder) {
        //more options
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                30
        );

        //click to add to reading list
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                30
        );

        //create reading list
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' button",
                30
        );

        //clear default name
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                30
        );

        //enter custom name for reading list
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input"
        );

        //submit creation
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find 'OK' button",
                30
        );
    }

    @Step("Adding article to the existing folder with name '{name_of_existing_folder}'")
    public void addArticleToMyExistingList(String name_of_existing_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                30
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                30
        );

        this.waitForElementAndClick(
                "//*[@text='" + name_of_existing_folder + "']",
                "Cannot find " + name_of_existing_folder,
                30
        );
    }

    @Step("Adding article my saved")
    public void addArticlesToMySaved() {
        if(Platform.getInstance().isMw()){
            this.removeArticleFromSavedIfItAdded();
        }
        this.tryClickElementWithFewAttempts(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                10);
    }

    @Step("Closing opened on screen article")
     public void closeArticle() {
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article by 'X'",
                    30
            );
        } else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
     }

    @Step("Verifying that article title is shown")
    public void assertThereIsTitleOfArticle() {
        this.assertElementPresent(
                TITLE,
                "Title of article is not found");
    }

    @Step("Clearing saved list by removing previously added article")
    public void removeArticleFromSavedIfItAdded() {
        if(this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.tryClickElementWithFewAttempts(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before"
            );
        }
    }
}
