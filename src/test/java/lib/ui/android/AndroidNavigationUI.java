package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    static  {
        MY_LISTS_LINK = "id:Saved";
    }

    public AndroidNavigationUI (RemoteWebDriver driver) {
        super(driver);
    }
}
