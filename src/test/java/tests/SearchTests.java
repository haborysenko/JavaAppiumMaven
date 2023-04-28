package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import lib.ui.factories.SearchPageObjectFactory;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Verify that search results are shown after valid search")
    @Description("In this test we're searching by text nd verifying that results are shown")
    @Step("Starting testSearch()")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResults("Object-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Verify the search filed cancel button")
    @Description("In this test we init search filed so cancel button appear and click on cancel button so it disappears")
    @Step("Starting testCancelSearch()")
    @Severity(value = SeverityLevel.MINOR)
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        //SearchPageObject.takeScreenshot("Search screen");
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Verify amount of not empty search results")
    @Description("In this test we're searching by text and verifying that results are shown")
    @Step("Starting testAmountOfNotEmptySearch()")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfNotEmptySearch() {
        String search_line = "Linking park discography";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int actual_amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "We found too few results",
                actual_amount_of_search_results > 0);
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Verify amount of empty search results")
    @Description("In this test we're searching by invalid text and verifying that NO results are shown")
    @Step("Starting testAmountOfEmptySearch()")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch() {
        String search_line = "ewewewewew";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Verify that it's possible search by text and cancel after")
    @Description("In this test we're searching by text, checking that each title contains search string and cancel search after")
    @Step("Starting testSearchResultsByStringAndCancelSearch()")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearchResultsByStringAndCancelSearch() {
        String search_line = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.assertThatEachTitleInSearchResultsContainSearchLine(search_line);
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Verify search filed placeholder has value")
    @Description("In this test we're checking that placeholder of the search filed has text value as we expect")
    @Step("Starting testSearchPlaceholderHasExactExpectedText()")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testSearchPlaceholderHasExactExpectedText() {
        String expected_value_of_search_placeholder = "Search";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String actual_value_of_search_placeholder = SearchPageObject.getSearchInitInputPlaceholderText();
        Assert.assertEquals(
                "Unexpected placeholder is returned",
                expected_value_of_search_placeholder,
                actual_value_of_search_placeholder
        );
    }


    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Verify search filed placeholder contains value")
    @Description("In this test we're checking that placeholder of the search filed contains text value as we expect")
    @Step("Starting testSearchPlaceholderContainsExpectedText()")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testSearchPlaceholderContainsExpectedText() {
        String expected_value_of_search_placeholder = "Search";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.assertSearchInitInputPlaceholderContainsText(expected_value_of_search_placeholder);
    }
}
