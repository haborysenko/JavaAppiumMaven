package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        if(Platform.getInstance().isMw()) {
            return;
        }

        String search_line = "Java";
        String search_result_to_click_on = "Object-oriented programming language";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(search_result_to_click_on);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();
        String article_title_after_rotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title has been changed after screen rotation",
                article_title_before_rotation,
                article_title_after_rotation
        );

        this.rotateScreenPortrait();
        String article_title_after_second_rotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title has been changed after screen rotation",
                article_title_before_rotation,
                article_title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        if(Platform.getInstance().isMw()) {
            return;
        }

        String search_line = "Java";
        String article_text_to_wait_for = "Object-oriented programming language";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResults(article_text_to_wait_for);

        this.backgroundApp(5);
        SearchPageObject.waitForSearchResults(article_text_to_wait_for);
    }
}
