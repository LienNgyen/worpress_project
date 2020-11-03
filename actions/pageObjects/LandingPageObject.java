package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.LandingPageUI;

public class LandingPageObject extends AbstractPage{
	WebDriver driver;

	public LandingPageObject(WebDriver mapdriver) {
		driver = mapdriver;
	}

	public void inputUserName(String inputUsername) {
		waitForElementsVisible(driver, LandingPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LandingPageUI.EMAIL_TEXTBOX, inputUsername);
	}

	public void clickToContinueButton() {
		waitForElementsVisible(driver, LandingPageUI.CONTINUE_BUTTON);
		clickToElement(driver, LandingPageUI.CONTINUE_BUTTON);
	}

	public void inputPassword(String inputPassword) {
		waitForElementsVisible(driver, LandingPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver,  LandingPageUI.PASSWORD_TEXTBOX, inputPassword);
	}

	public void clickToLoginButton() {
		waitForElementsVisible(driver, LandingPageUI.LOGIN_BUTTON);
		clickToElement(driver, LandingPageUI.LOGIN_BUTTON);
	}

	public String getErrorMessage() {
		waitForElementsVisible(driver, LandingPageUI.GET_ERROR_MESSAGE);
		return getElementText(driver, LandingPageUI.GET_ERROR_MESSAGE);
	}

	public boolean isDashboardDisplay() {
		waitForElementsVisible(driver, LandingPageUI.DASHBOARD_ICON);
		return isElementDisplay(driver, LandingPageUI.DASHBOARD_ICON);
	}

}
