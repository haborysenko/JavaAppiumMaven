package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObjects extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        ARTICLE_TITLE = "xpath://XCUIElementTypeStaticText[contains(@name,'')]";
        ACTION_DELETE = "id:swipe action delete";
        CLOSE_LOGIN_SUGGESTION = "id:Close";
    }

    public iOSMyListsPageObjects(RemoteWebDriver driver) {
        super(driver);
    }
}
