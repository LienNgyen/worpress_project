package com.wordpress.userSite;

import org.testng.annotations.Test;

import commons.AbstractTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class user_login_testcases extends AbstractTest {
	WebDriver driver;
	
	@Parameters({"browser","url"})

	@BeforeClass
	public void beforeClass(String browser, String url) {
		driver = getBrowserDriver(browser, url);
	}

	@Test
	public void f() {
	}

	@AfterClass
	public void afterClass() {
	}

}
