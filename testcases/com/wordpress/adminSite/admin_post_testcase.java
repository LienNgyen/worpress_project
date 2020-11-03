package com.wordpress.adminSite;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageManageGenerator;
import pageObjects.DashboardPageObject;
import pageObjects.LandingPageObject;
import pageObjects.PostPageObject;



public class admin_post_testcase extends AbstractTest{
	WebDriver driver;
	LandingPageObject landingPage;
	DashboardPageObject dashboardPage;
	PostPageObject postPage;
	Date date;
	@Parameters({"browser","url"})
	
	@BeforeClass(alwaysRun=true)
	public void BeforeMethod(String browser, String url) {
		driver = getBrowserDriver(browser,url);
		landingPage = PageManageGenerator.getLandingPage(driver);
	}

	@Test
	public void TC01_Create_New_Post() {
		
		landingPage.inputUserName("automationeditor");
		landingPage.clickToContinueButton();
		landingPage.inputPassword("automationfc");
		landingPage.clickToLoginButton();
		verifyTrue(landingPage.isDashboardDisplay());
		dashboardPage = PageManageGenerator.getDashboardPage(driver);
		dashboardPage.moveToPostPage();
		postPage = PageManageGenerator.getPostPage(driver);
		postPage.clickToAddNewButton();
		postPage.inputTheTitle("Sample Title 222");
		postPage.clickToPublishButton2times();
		verifyTrue(postPage.waitForTheSpinnerInvisibility());
		verifyTrue(postPage.isSuccessfulMessageDisplay("Sample Title 222"));
		
		
		 
	}
	
	

	@AfterClass(alwaysRun = true)
	public void afterMethod() {
	    driver.quit();
	  }


}
