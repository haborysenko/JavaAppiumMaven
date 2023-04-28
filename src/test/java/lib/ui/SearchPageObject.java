package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_TITLE_DESCRIPTION_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT_ELEMENT;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /*TEMPLATE METHODS*/
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementTitleDescription(String substring_title, String substring_description) {
        return SEARCH_RESULT_TITLE_DESCRIPTION_BY_SUBSTRING_TPL
                .replace("{SUBSTRING_TITLE}", substring_title)
                .replace( "{SUBSTRING_DESCRIPTION}", substring_description);
    }
    /*TEMPLATE METHODS*/

    @Step("Initializing the search filed")
    public void initSearchInput() {
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                 "Cannot find init search element with placeholder 'Search Wikipedia'");
        screenshot(this.takeScreenshot("Search new screen"));
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find & click in init search element with placeholder 'Search Wikipedia'");
    }

    @Step("Waiting for search cancel button to appear")
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button 'X'");
    }

    @Step("Waiting for search cancel button to disappear")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button 'X' is still present");
    }

    @Step("Clicking button to cancel search results")
    public void clickCancelSearch() {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot fins and click search cancel button");
    }

    @Step("Typing '{search_line}' to the search filed")
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Cannot find and type into search input element");
    }

    @Step("Clearing text from the search filed")
    public void clearSearchInputField() {
        this.waitForElementAndClearTextField(
                SEARCH_INPUT,
                "Cannot clear search input");
    }

    @Step("Waiting till search results are present on the page")
    public void waitForSearchResults(String substring) {
        String search_result_element_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                search_result_element_xpath,
                "Cannot find search result with substring " + substring,
                30);
    }

    @Step("Opening article by clicking on '{substring}' in search results")
    public void clickByArticleWithSubstring(String substring) {
        String search_result_element_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                search_result_element_xpath,
                "Cannot find and click search result with substring " + substring,
                30);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles() {
        //wait till at leas one is shown
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything",
                15
        );

        //get amount of elements
        return this.getAmountOfElements(
                SEARCH_RESULT_ELEMENT
        );
    }

    @Step("Verifying that each article in the search result list contains entered '{search_line}'")
    public void assertThatEachTitleInSearchResultsContainSearchLine(String search_line) {
        List<String> titles = this.getArticleTitlesFromSearchResults(search_line);

        for(String title: titles){
            Assert.assertThat(title, containsString(search_line));
        }
    }

    @Step("Getting list of articles titles with '{substring}' from search results")
    public List<String> getArticleTitlesFromSearchResults(String substring) {
        String search_result_element_xpath = getResultSearchElement(substring);
                if(Platform.getInstance().isAndroid()) {
                    return this.waitForElementsAndGetAttribute(
                            search_result_element_xpath,
                            "text",
                            "Cannot get article title attribute",
                            30);
                } else if (Platform.getInstance().isIOS()){
                    return this.waitForElementsAndGetAttribute(
                            search_result_element_xpath,
                            "name",
                            "Cannot get article title attribute",
                            30);
                } else {
                    return this.waitForElementsAndGetAttribute(
                            search_result_element_xpath,
                            "textContent",
                            "Cannot get article title attribute",
                            30);
                }
    }

    @Step("Waiting for empty results label")
    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element",
                30
        );
    }

    @Step("Verifying that there is no search results")
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We've found some results, but not suppose");
    }

    @Step("Waiting for initializing of the search input")
    public WebElement waitForSearchInitInput() {
        return this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search input",
                30);
    }

    @Step("Getting text of the search filed placeholder")
    public String getSearchInitInputPlaceholderText() {
        WebElement search_placeholder = waitForSearchInitInput();
        if(Platform.getInstance().isAndroid()) {
            return search_placeholder.getAttribute("text");
        } else if(Platform.getInstance().isIOS()){
            return search_placeholder.getAttribute("name");
        } else {
            return search_placeholder.getAttribute("textContent");
        }
    }

    @Step("Verifying that the search filed has placeholder as we expect: '{expected_placeholder}'")
    public void assertSearchInitInputPlaceholderHasExactText(String expected_placeholder) {
        this.assertElementHasExactText(
                SEARCH_INIT_ELEMENT,
                expected_placeholder,
                "Init search placeholder does not have exact text " + expected_placeholder);
    }

    @Step("Verifying that the search filed contains text '{expected_placeholder}' in placeholder")
    public void assertSearchInitInputPlaceholderContainsText(String expected_placeholder) {
        this.textToBePresentInElementLocated(
                SEARCH_INIT_ELEMENT,
                expected_placeholder);
    }
}

