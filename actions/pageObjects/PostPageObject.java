package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.PostPageUI;

public class PostPageObject extends AbstractPage{
	WebDriver driver;
	public PostPageObject (WebDriver mapdriver) {
		driver = mapdriver;
	}
	public void clickToAddNewButton() {
		waitForElementsVisible(driver, PostPageUI.ADD_BUTTON);
		clickToElement(driver, PostPageUI.ADD_BUTTON);
	}
	public void clickToPublishButton2times() {
		waitForElementsVisible(driver, PostPageUI.PUBLISH_BUTTON);
		clickToElement(driver, PostPageUI.PUBLISH_BUTTON);
		//waitForElementsVisible(driver, PostPageUI.PUBLISH_BUTTON_2);
		clickToElement(driver, PostPageUI.PUBLISH_BUTTON_2);
	}
	public void inputTheTitle(String inputTitle) {
		waitForElementsVisible(driver, PostPageUI.TITLE_TEXTAREA);
		sendKeyToElement(driver, PostPageUI.TITLE_TEXTAREA, inputTitle);
	}
	public boolean isSuccessfulMessageDisplay(String inputTittle) {
		waitForElementVisible(driver, inputTittle, PostPageUI.SUCCESSFUL_MESSAGE);
		return isElementDisplay(driver,inputTittle, PostPageUI.SUCCESSFUL_MESSAGE);
	}
	public boolean waitForTheSpinnerInvisibility() {
		waitForElementInvisible(driver, PostPageUI.SPINNER_ICON);
		return isElementUndisplay(driver, PostPageUI.SPINNER_ICON);
	}
}
