package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.DashboardPageUI;

public class DashboardPageObject extends AbstractPage{
	WebDriver driver;

	public DashboardPageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}

	public void moveToPostPage() {
		waitForElementsVisible(driver, DashboardPageUI.POST_MENU);
		clickToElement(driver, DashboardPageUI.POST_MENU);
	}

}
