package commons;

import org.openqa.selenium.WebDriver;


import pageObjects.DashboardPageObject;
import pageObjects.LandingPageObject;
import pageObjects.PostPageObject;

public class PageManageGenerator {
	public static LandingPageObject getLandingPage(WebDriver driver) {
		return new LandingPageObject(driver);
	}

	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}

	public static PostPageObject getPostPage(WebDriver driver) {
		return new PostPageObject(driver);
	}

}
