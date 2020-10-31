package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	//public abstract boolean isPageLoaded(String pageURL);

		public void OpenUrl(WebDriver driver, String urlValue) {
			driver.get(urlValue);

		}

		public String getPageTitle(WebDriver driver) {
			return driver.getTitle();

		}

		public String getCurrentUrl(WebDriver driver) {
			return driver.getCurrentUrl();
		}

		public void back(WebDriver driver) {
			driver.navigate().back();
		}

		public void refresh(WebDriver driver) {
			driver.navigate().refresh();
		}

		public void acceptAlert(WebDriver driver) {
			driver.switchTo().alert().accept();
		}

		public void cancelAlert(WebDriver dirver) {
			dirver.switchTo().alert().dismiss();
		}

		public void sendKeyAlert(WebDriver dirver, String value) {
			dirver.switchTo().alert().sendKeys(value);
		}

		public String getTextInAlert(WebDriver driver) {
			return driver.switchTo().alert().getText();
		}

		public void waitAlertPresent(WebDriver driver) {
			WebDriverWait explicitWait;
			explicitWait = new WebDriverWait(driver, 15);
			explicitWait.until(ExpectedConditions.alertIsPresent());

		}

		public void switchToWindowByID(WebDriver driver, String parentID) {
			Set<String> allWindows = driver.getWindowHandles();
			for (String id : allWindows) {
				System.out.println("ID = " + id);
				if (!id.equals(parentID)) {
					driver.switchTo().window(id);
					break;
				}

			}

		}

		public void switchToWindowByTitle(WebDriver driver, String windowTitle) {
			Set<String> allWindows = driver.getWindowHandles();
			for (String id : allWindows) {
				driver.switchTo().window(id);
				String title = driver.getTitle();
				if (title.equals(windowTitle)) {
					break;
				}
			}
		}

		public void closeWindowWoParent(WebDriver driver, String ParentID) {
			Set<String> allWindows = driver.getWindowHandles();
			for (String id : allWindows) {
				if (!id.equals(ParentID)) {
					driver.switchTo().window(id);
					driver.close();
				}
			}
			driver.switchTo().window(ParentID);
		}

		public By byXpath(String locator) {
			return By.xpath(locator);
		}

		public WebElement findElementByXpath(WebDriver driver, String locator) {
			return driver.findElement(byXpath(locator));

		}

		public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
			return driver.findElements(byXpath(locator));

		}

		public void clickToElement(WebDriver driver, String locator) {
			findElementByXpath(driver, locator).click();
		}
		
		public void clickToElement(WebDriver driver, String locator, String... values) {
			findElementByXpath(driver, castToObject(locator, values)).click();
		}

		public void sendKeyToElement(WebDriver driver, String locator, String value) {
			findElementByXpath(driver, locator).clear();
			findElementByXpath(driver, locator).sendKeys(value);
		}
		
		public void sendKeyToElement(WebDriver driver, String expected,String locator, String...values) {
			findElementByXpath(driver, castToObject(locator, values)).clear();
			findElementByXpath(driver, castToObject(locator, values)).sendKeys(expected);
		}

		public String getElementText(WebDriver driver, String locator) {
			return findElementByXpath(driver, locator).getText();
		}
		
		public String getElementText(WebDriver driver, String locator, String...values ) {
			return findElementByXpath(driver, castToObject(locator, values)).getText();
		}

		public String getAttribute(WebDriver driver, String locator, String attributeName) {
			return findElementByXpath(driver, locator).getAttribute(attributeName);
		}

		public void selectValueInDropDown(WebDriver driver, String locator, String value) {
			select = new Select(findElementByXpath(driver, locator));
			select.selectByVisibleText(value);
		}
		
		public void selectValueInDropDown(WebDriver driver, String expected, String locator, String...values) {
			select = new Select(findElementByXpath(driver, castToObject(locator, values)));
			select.selectByVisibleText(expected);
		}

		public String getFirstItemInDropDown(WebDriver driver, String locator, String value) {
			select = new Select(findElementByXpath(driver, locator));
			return select.getFirstSelectedOption().getText();
		}

		public void selectItemInCustomDropdownList(WebDriver driver, String parentXpath, String childXpath,
				String expectedItem) {

			driver.findElement(By.xpath(parentXpath)).click();
			List<WebElement> allItems = driver.findElements(By.xpath(childXpath));
			WebDriverWait waitExplicit = new WebDriverWait(driver, 30);
			waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

			for (int i = 0; i < allItems.size(); i++) {
				String itemText = allItems.get(i).getText();
				if (itemText.equals(expectedItem)) {
					allItems.get(i).click();
					break;
				}

			}
		}

		public int countElementNumber(WebDriver driver, String locator) {
			return findElementsByXpath(driver, locator).size();

		}
		
		public int countElementNumber(WebDriver driver, String locator, String...values) {
			return findElementsByXpath(driver, castToObject(locator, values)).size();

		}

		public void checkToCheckbox(WebDriver driver, String locator) {
			element = findElementByXpath(driver, locator);
			if (!element.isSelected()) {
				element.click();
			}

		}
		
		public void checkToCheckbox(WebDriver driver, String locator, String...values) {
			element = findElementByXpath(driver, castToObject(locator, values));
			if (!element.isSelected()) {
				element.click();
			}

		}

		public void uncheckToCheckbox(WebDriver driver, String locator) {
			element = findElementByXpath(driver, locator);
			if (element.isSelected()) {
				element.click();
			}

		}

		public boolean isElementDisplay(WebDriver driver, String locator) {
			try {
				return findElementByXpath(driver, locator).isDisplayed();
				
			} catch (NoSuchElementException NoSuchElement) {
				NoSuchElement.getStackTrace();
				return false;
			}
			
		}
		
		public boolean isElementDisplay(WebDriver driver, String locator,String... values) {
			return findElementByXpath(driver, castToObject(locator, values)).isDisplayed();
		}

		public boolean isElementEnable(WebDriver driver, String locator) {
			return findElementByXpath(driver, locator).isEnabled();
		}

		public boolean isElementSelected(WebDriver driver, String locator) {
			return findElementByXpath(driver, locator).isSelected();
		}

		public void switchToFrameOrIFrame(WebDriver driver, String locator) {
			driver.switchTo().frame(findElementByXpath(driver, locator));

		}

		public void switchToDefaultContent(WebDriver driver) {
			driver.switchTo().defaultContent();

		}

		public void hoverMouseToElement(WebDriver driver, String locator) {
			action = new Actions(driver);
			action.moveToElement(findElementByXpath(driver, locator)).perform();
		}
		
		public void hoverMouseToElement(WebDriver driver, String locator, String...values) {
			action = new Actions(driver);
			action.moveToElement(findElementByXpath(driver, castToObject(locator, values))).perform();
		}

		public void doubleClickToElement(WebDriver driver, String locator) {
			action = new Actions(driver);
			action.doubleClick(findElementByXpath(driver, locator)).perform();
		}

		public void rightClickToElement(WebDriver driver, String locator) {
			action = new Actions(driver);
			action.contextClick(findElementByXpath(driver, locator)).perform();
		}

		public void dragAndDropElement(WebDriver driver, String sourLocator, String targetLocator) {
			action = new Actions(driver);
			action.dragAndDrop(findElementByXpath(driver, sourLocator), findElementByXpath(driver, targetLocator))
					.perform();
		}

		public void sendKeyboardsToElement(WebDriver driver, String locator, Keys key) {
			action = new Actions(driver);
			action.sendKeys(findElementByXpath(driver, locator), key).perform();
		}
		
		public void sendKeyboardsToElement(WebDriver driver, Keys key, String locator, String...values) {
			action = new Actions(driver);
			action.sendKeys(findElementByXpath(driver, castToObject(locator, values)), key).perform();
		}

		// Browser
		public Object executeForBrowser(WebDriver driver, String javaSript) {
			jsExecutor = (JavascriptExecutor) driver;
			return jsExecutor.executeScript(javaSript);
		}

		public boolean verifyTextInInnerText(WebDriver driver, String textExpected) {
			jsExecutor = (JavascriptExecutor) driver;
			String textActual = (String) jsExecutor
					.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			System.out.println("Text actual = " + textActual);
			return textActual.equals(textExpected);
		}

		public void scrollToBottomPage(WebDriver driver) {
			jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		// Element
		public void highlightElement(WebDriver driver, String locator) {
			jsExecutor = (JavascriptExecutor) driver;
			element = findElementByXpath(driver, locator);
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
					"border: 5px solid red; border-style: dashed;");
			sleepInSecond(2);
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
					originalStyle);

		}

		public void clickToElementByJS(WebDriver driver, String locator) {
			jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", findElementByXpath(driver, locator));
		}

		public void scrollToElement(WebDriver driver, String locator) {
			jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, locator));
		}

		public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
			jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')",
					findElementByXpath(driver, locator));
		}

		public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
			jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
					findElementByXpath(driver, locator));
		}

		public boolean isImageLoaded(WebDriver driver, String locator) {
			jsExecutor = (JavascriptExecutor) driver;
			boolean status = (boolean) jsExecutor.executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					findElementByXpath(driver, locator));
			if (status) {
				return true;
			} else {
				return false;
			}
		}
		
		public void waitForElementVisible (WebDriver driver, String locator) {
			explicitWait = new WebDriverWait(driver, longTimeOut);
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
		}
		
		public void waitForElementVisible (WebDriver driver, String locator,String...values) {
			explicitWait = new WebDriverWait(driver, longTimeOut);
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(locator, values))));
		}
		
		public void waitForElementsVisible (WebDriver driver, String locator) {
			explicitWait = new WebDriverWait(driver, longTimeOut);
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(locator)));
		}
		
		public void waitForElementInvisible (WebDriver driver, String locator) {
			explicitWait = new WebDriverWait(driver, longTimeOut);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
		}
		
		public void waitForElementsInvisible (WebDriver driver, String locator) {
			explicitWait = new WebDriverWait(driver, longTimeOut);
			elements = findElementsByXpath(driver, locator);
			explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
		}
		
		public void waitForElementClickable (WebDriver driver, String locator) {
			explicitWait = new WebDriverWait(driver, longTimeOut);
			explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
		}
		

		public void waitForElementClickable (WebDriver driver, String locator, String...values) {
			explicitWait = new WebDriverWait(driver, longTimeOut);
			explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(locator, values))));
		}

		public void sleepInSecond(long timeout) {
			try {
				Thread.sleep(timeout * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*public PostPageObject clickToPostMenu(WebDriver driver) {
			System.out.println("the Driver ID: = " + driver.toString());
			waitForElementVisible(driver, LoginPageUI.POST_MENU);
			clickToElement(driver, LoginPageUI.POST_MENU);
			return PageGeneratorManager.getPostPage(driver);
		}
		
		public PagePageObject clickToPageMenu(WebDriver driver) {
			waitForElementVisible(driver, LoginPageUI.PAGES_MENU);
			clickToElement(driver, LoginPageUI.PAGES_MENU);
			return PageGeneratorManager.getPagePage(driver);
		}

		public DashboardPageObject clickToDashboardMenu(WebDriver driver) {
			waitForElementVisible(driver, LoginPageUI.DASHBOARD_MENU);
			clickToElement(driver, LoginPageUI.DASHBOARD_MENU);
			return PageGeneratorManager.getDashboardPage(driver);
		}
		
		public MediaPageObject clickToMediaMenu(WebDriver driver) {
			waitForElementVisible(driver, LoginPageUI.MEDIA_MENU);
			clickToElement(driver, LoginPageUI.MEDIA_MENU);
			return PageGeneratorManager.getMediaPage(driver);
		}*/
		
		public String castToObject (String locator, String...values) {
			return String.format(locator, (Object[]) values);
			
		}
		
		//Dynamic Locator
		//Apply for the some pages ( < 20pages)
		/*public AbstractPage clickToDynamicMenu(WebDriver driver, String pageName) {
			waitForElementVisible(driver, AbstractPageUI.DYNAMIC_MENU, pageName);
			clickToElement(driver, AbstractPageUI.DYNAMIC_MENU, pageName);
			
			if (pageName.equalsIgnoreCase("posts")) {
				return PageGeneratorManager.getPostPage(driver);
			}
			else if (pageName.equalsIgnoreCase("pages")) {
				return PageGeneratorManager.getPagePage(driver);
				
			}
			
			else if (pageName.equalsIgnoreCase("media")) {
				return PageGeneratorManager.getMediaPage(driver);
				
			}
			else {	
				return PageGeneratorManager.getDashboardPage(driver);
							
			}
		}*/
		
		public void clickToDynamicMultiplePages (WebDriver driver, String pageName) {
			waitForElementVisible(driver, AbstractPageUI.DYNAMIC_MENU, pageName);
			clickToElement(driver, AbstractPageUI.DYNAMIC_MENU, pageName);
		}
		
		public void overrideGlobalTimout(WebDriver driver, int timeOut) {
			driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		}
		
		public boolean isElementUndisplay(WebDriver driver, String locator) {
			System.out.println("Start Time = " + new Date().toString());
			overrideGlobalTimout(driver, shortTimeOut);
			List<WebElement> elements = driver.findElements(By.xpath(locator));
			if (elements.size() == 0) {
				System.out.println("Element not in DOM");
				System.out.println("End Time = " + new Date().toString());
				overrideGlobalTimout(driver, longTimeOut);
				return true;
			} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
				System.out.println("Element in DOM but invisible");
				System.out.println("End Time = " + new Date().toString());
				overrideGlobalTimout(driver, longTimeOut);
				return true;	
			} else {
				System.out.println("Element in DOM and visible");
				System.out.println("End Time = " + new Date().toString());
				overrideGlobalTimout(driver, longTimeOut);
				return false;	
			}
		}
		
		public boolean isElementUndisplay(WebDriver driver, String locator, String...values) {
			System.out.println("Start Time = " + new Date().toString());
			overrideGlobalTimout(driver, shortTimeOut);
			List<WebElement> elements = driver.findElements(By.xpath(castToObject(locator, values)));
			if (elements.size() == 0) {
				System.out.println("Element not in DOM");
				System.out.println("End Time = " + new Date().toString());
				overrideGlobalTimout(driver, longTimeOut);
				return true;
			} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
				System.out.println("Element in DOM but invisible");
				System.out.println("End Time = " + new Date().toString());
				overrideGlobalTimout(driver, longTimeOut);
				return true;	
			} else {
				System.out.println("Element in DOM and visible");
				System.out.println("End Time = " + new Date().toString());
				overrideGlobalTimout(driver, longTimeOut);
				return false;	
			}
		}
		
		public void uploadMultipleFiles (WebDriver driver, String... fileNames) {
			String fullFileName = "";
			for (String file : fileNames) {
				fullFileName = fullFileName + GlobalConstants.UPLOAD_FOLDER + file + "\n";
			}
			fullFileName = fullFileName.trim();
			sendKeyToElement(driver, AbstractPageUI.UPLOAD_FILE_TYPE, fullFileName);
		}
		
		public boolean isUploadedFileDisplay (WebDriver driver, String... fileNames) {
			boolean status = false;
			int number = fileNames.length;
			int i = 0;
			
			waitForElementsInvisible(driver, AbstractPageUI.UPLOAD_INPROGRESS_BAR);
			sleepInSecond(5);
			elements = findElementsByXpath(driver, AbstractPageUI.UPLOAD_IMAGES);
			List<String> imageValues = new ArrayList<String>();
			
			for (WebElement image : elements) {
				imageValues.add(image.getAttribute("src"));	
				i++;
				if (i == number) {
					break;
				}
			}
			
			for (String name : fileNames) {
				String[] names = name.split("\\.");
				name = names[0].toLowerCase();
				
				for (i = 0; i < imageValues.size(); i++) {
				if (!imageValues.get(i).contains(name)) {
					status = false;
					if (i == imageValues.size() - 1) {
						return status;
					}
				} else {
					status = true;
					break;
				}
			}
			}
			return status;
			
		}
		
		/*public FrontEndPageObjects openFronEndUserPage (WebDriver driver) {
			OpenUrl(driver, GlobalConstants.WORDPRESS_FRONTEND_URL);
			return PageGeneratorManager.getFrontEndPage(driver);
			
		}
		
		public DashboardPageObjects openAdminPage (WebDriver driver) {
			OpenUrl(driver, GlobalConstants.WORDPRESS_ADMIN_URL;
			return PageGeneratorManager.getDashboardPage(driver);
			
		}*/

		public boolean isDataSortedAscending (WebDriver driver, String locator) {
			ArrayList<String> arrayList = new ArrayList<>();
			List<WebElement> elementList = driver.findElements(By.xpath(locator));
			for (WebElement element : elementList) {
				arrayList.add(element.getText());
			}
			System.out.println("---------Data from UI: ----------");
			for (String name : arrayList) {
				System.out.println(name);
			}
			ArrayList<String> sortedList = new ArrayList<>();
			for (String child : arrayList) {
				sortedList.add(child);
			}
			Collections.sort(sortedList);
			System.out.println("---------Data after sorted ASC: ----------");
			for (String name : sortedList) {
				System.out.println(name);
			}
			return sortedList.equals(arrayList);
		}
		
		public boolean isDataSortedAscendingWithPaging (WebDriver driver, String locator) {
			ArrayList<String> arrayList = new ArrayList<>();
			List<WebElement> elementList = driver.findElements(By.xpath(locator));
			for (WebElement element : elementList) {
				arrayList.add(element.getText());
			}
			
			boolean morePages = isTheMorePages(driver);
			while (morePages == true) {
				clickToElement(driver,  AbstractPageUI.NEXT_BUTTON);
				elementList = driver.findElements(By.xpath(locator));
				for (WebElement element : elementList) {
					System.out.println("get text" + element.getText());
					arrayList.add(element.getText());
				}
				morePages = isTheMorePages(driver);
			}
			System.out.println("---------Data from UI: ----------");
			for (String name : arrayList) {
				System.out.println(name);
			}
			ArrayList<String> sortedList = new ArrayList<>();
			for (String child : arrayList) {
				sortedList.add(child);
			}
			Collections.sort(sortedList);
			System.out.println("---------Data after sorted ASC: ----------");
			for (String name : sortedList) {
				System.out.println(name);
			}
			return sortedList.equals(arrayList);
		}
		
		public boolean isTheMorePages(WebDriver driver) {
			boolean status;
			
			if( isElementUndisplay(driver, AbstractPageUI.NEXT_BUTTON)== true) {
				status = false;
			} else {
				status = true;
			}
			return status;
		}
		
		public boolean isDataSortedDescending (WebDriver driver, String locator) {
			ArrayList<String> arrayList = new ArrayList<>();
			List<WebElement> elementList = driver.findElements(By.xpath(locator));
			for (WebElement element : elementList) {
				arrayList.add(element.getText());
			}
			System.out.println("---------Data from UI: ----------");
			for (String name : arrayList) {
				System.out.println(name);
			}
			ArrayList<String> sortedList = new ArrayList<>();
			for (String child : arrayList) {
				sortedList.add(child);
			}
			Collections.sort(sortedList);
			
			Collections.reverse(sortedList);
			System.out.println("---------Data after sorted DSC: ----------");
			for (String name : sortedList) {
				System.out.println(name);
			}
				
			return sortedList.equals(arrayList);
		}
		
		public boolean isPriceSortedAscending (WebDriver driver, String locator) {
			ArrayList<Float> arrayList = new ArrayList<Float>();
			List<WebElement> elementList = driver.findElements(By.xpath(locator));
			for (WebElement element : elementList) {
				arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
			}
			System.out.println("---------Data from UI: ----------");
			for (Float name : arrayList) {
				System.out.println(name);
			}
			ArrayList<Float> sortedList = new ArrayList<Float>();
			for (Float child : arrayList) {
				sortedList.add(child);
			}
			Collections.sort(sortedList);
			System.out.println("---------Data after sorted ASC: ----------");
			for (Float name : sortedList) {
				System.out.println(name);
			}
				
			return sortedList.equals(arrayList);
		}





		private Select select;
		private WebElement element;
		private List<WebElement> elements;
		private Actions action;
		private JavascriptExecutor jsExecutor;
		private WebDriverWait explicitWait;
		private int longTimeOut = 30;
		private int  shortTimeOut;



}
