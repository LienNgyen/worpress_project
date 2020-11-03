package commons;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.jetty9.util.log.Log;
import org.testng.Assert;
import org.testng.Reporter;



public class AbstractTest {
	WebDriver driver;
	String rootFolder = System.getProperty("user.dir");
	protected final Log log = new Log();
	
	
	protected WebDriver getBrowserDriver (String browser, String url) {
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", rootFolder + "/common_libs/driver/geckodriver");
			driver= new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", rootFolder + "/common_libs/driver/chromedriver");
			driver= new ChromeDriver();
		} 
		else if (browser.equalsIgnoreCase("chromeheadless")) {
			System.setProperty("webdriver.chrome.driver", rootFolder + "/common_libs/driver/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366x768");
			driver = new ChromeDriver(options);
			
		} else {
			System.out.println("Please select the browser");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(GlobalConstants.shortTimeout, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}
	
	
	
	
	protected int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
	protected Date getDateTimeNow() {
		Date date = new Date();
		return date;
	}
	
	protected String getCurrentDate() {
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
		return now.format(formatter);
	}
	
	protected boolean verifyTrue(boolean Condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(Condition);
		} catch (Throwable e) {
			pass = false;
			//VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	protected boolean verifyFalse(boolean Condition) {
		boolean pass = true;
		try {
				Assert.assertFalse(Condition);
			}
			
		catch (Throwable e) {
			pass = false;
			//VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	protected boolean verifyEqual(Object actual, Object expect) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expect);
		} catch (Throwable e) {
			pass = false;
			//VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	protected void closeBrowserAndDriver(WebDriver driver) {
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			
			log.equals("OS name = " + osName);
			String cmd = "";
			if (driver != null) {
				driver.quit();
			}

			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			}

			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

			log.equals("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			log.equals(e.getMessage());
		}
	}

}
