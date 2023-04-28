package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;

public class ArticleTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Compare article title with expected one")
    @Description("In this test we compare article title with expected one")
    @Step("Starting testCompareArticleTitle()")
    @Severity(value = SeverityLevel.NORMAL)
        public void testCompareArticleTitle() {
        String search_line = "Java";
        String search_result_to_click_on = "programming";
        String expected_article_title = "Java (programming language)";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(search_result_to_click_on);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String actual_article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Unexpected title is returned",
                expected_article_title,
                actual_article_title
        );
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Swipe article to the footer")
    @Description("In this test we open article and swipe it to the footer")
    @Step("Starting testSwipeArticle()")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle() {
        String search_line = "Java";
        String search_result_to_click_on = "programming";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(search_result_to_click_on);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Verify that article title is present")
    @Description("In this test we check that title of the article is on the page after we open it")
    @Step("Starting testArticleTitlePresent()")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testArticleTitlePresent() {
        String search_line = "Java";
        String search_result_to_click_on = "programming";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(search_result_to_click_on);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.assertThereIsTitleOfArticle();
    }
}