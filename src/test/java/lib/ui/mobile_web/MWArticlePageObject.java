package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        TITLE_BY_SUBSTRING_TPL = "xpath://h1/span[contains(text(), '{SUBSTRING}')]";
        FOOTER_ELEMENT = "css:footer";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions [title='Watch']"; //#page-actions a#ca-watch
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions a#ca-watch[class*='watched']";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
