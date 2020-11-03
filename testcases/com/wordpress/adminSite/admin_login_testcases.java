package com.wordpress.adminSite;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageManageGenerator;
import pageObjects.LandingPageObject;



import org.testng.annotations.Parameters;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;


public class admin_login_testcases extends AbstractTest {
	WebDriver driver;
	LandingPageObject landingPage;
	Date date;
	@Parameters({"browser","url"})
	
	@BeforeClass(alwaysRun=true)
	public void BeforeMethod(String browser, String url) {
		driver = getBrowserDriver(browser,url);
		landingPage = PageManageGenerator.getLandingPage(driver);
		long id = Thread.currentThread().getId();
		System.out.println("Thread of login is: " + id);
	}

	@Test
	public void TC01_Login_Empty_UserName() {
		
		long id = Thread.currentThread().getId();
		System.out.println("Thread tc01 is: " + id);
		date = getDateTimeNow();
		System.out.println(date);
		landingPage.clickToContinueButton();
		verifyEqual(landingPage.getErrorMessage(), "Please enter a username or email address.");
	}
	
	@Test
	public void TC02_Login_NotExisted_UserName() {
		
		long id = Thread.currentThread().getId();
		System.out.println("Thread tc02 is: " + id);
		date = getDateTimeNow();
		System.out.println(date);
		landingPage.inputUserName("lien@1@#");
		landingPage.clickToContinueButton();
		verifyEqual(landingPage.getErrorMessage(),"User does not exist. Would you like to create a new account?");
	}
	
	@Test
	public void TC03_Login_Invalid_Password() {
		
		long id = Thread.currentThread().getId();
		System.out.println("Thread tc03 is: " + id);
		date = getDateTimeNow();
		System.out.println(date);
		landingPage.inputUserName("automationeditor");
		landingPage.clickToContinueButton();
		landingPage.inputPassword("123");
		landingPage.clickToLoginButton();
		verifyEqual(landingPage.getErrorMessage(),"Oops, that's not the right password. Please try again!");
	}

	@AfterClass(alwaysRun = true)
	public void afterMethod() {
	    driver.quit();
	  }

}
