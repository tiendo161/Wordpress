package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;
import pageObjects.wordpress.user.HomePageObject;
import pageObjects.wordpress.user.PostDetailPageObject;
import pageObjects.wordpress.user.SearchResultPageObject;
import pageUI.wordpress.user.AbstractPageUI;


public class AbstractPage {

	public void openURL(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextInAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void sendkeyToAlert(WebDriver driver, String keyValue) {
		driver.switchTo().alert().sendKeys(keyValue);
	}

	public void waitAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait;
		explicitWait = new WebDriverWait(driver, 15);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchWindowById(WebDriver driver, String parentID) {

		Set<String> allWindow = driver.getWindowHandles();

		for (String id : allWindow) {
			System.out.println("Id = " + id);
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String windowTitle) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String id : allWindow) {
			driver.switchTo().window(id);
			String title = driver.getTitle();
			if (title.equals(windowTitle)) {
				break;
			}
		}
	}

	public boolean areWindowClosedWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String id : allWindow) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
			if (driver.getWindowHandles().size() == 1)
				return true;
		}
		return false;
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

	public String castToObject(String locator, String... value) {
		return String.format(locator, (Object[]) value);
	}

	public void clickToElement(WebDriver driver, String locator) {

		findElementByXpath(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... value) {
		findElementByXpath(driver, castToObject(locator, value)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		// textbox/ textarea/dropdown list (editable)/ upload file
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(value);
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value, String... values) {
		// textbox/ textarea/dropdown list (editable)/ upload file
		element = findElementByXpath(driver, castToObject(locator, values));
		element.clear();
		element.sendKeys(value);
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + GlobalConstants.UPLOAD_FOLDER + file + "\n";
		}

		fullFileName = fullFileName.trim();
		sendkeyToElement(driver, AbstractPageUI.UPLOAD_FILE_TYPE, fullFileName);
	}

	public boolean areFileUploadedDisplay(WebDriver driver, String... fileNames) {
		boolean status = false;
		int number = fileNames.length;

		waitForElementsInvisible(driver, AbstractPageUI.MEDIA_PROGRESS_BAR);
		sleepInSecond(5);
		elements = findElementsByXpath(driver, AbstractPageUI.MEDIA_ALL_UPLOADED_VALUE);

		List<String> imageValues = new ArrayList<String>();

		int i = 0;
		for (WebElement image : elements) {
			imageValues.add(image.getAttribute("src"));
			i++;
			if (i == number) {
				break;
			}
		}

		for (String fileName : fileNames) {
			String[] files = fileName.split("\\.");
			fileName = files[0].toLowerCase();
//Need to re-work 
//			for (i = 0; i < imageValues.size(); i++) {
//				if (!imageValues.get(i).contains(fileName)) {
//					status = false;
//					if (i == imageValues.size() - 1) {
//						return status;
//					}
//				} else
					
					
					for (i = 0; i < imageValues.size();) {
						if (!imageValues.get(i).contains(fileName)) {
							status = false;
							if (i == imageValues.size() - 1) {
								return status;
							}
						} else status = true;
				break;
			}

		}

		return status;
	}

	public String getElementText(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText().trim();
	}
	
	public String getElementText(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, castToObject(locator, values)).getText().trim();
	}

	public String getAttribute(WebDriver driver, String locator, String attributeName) {
		return findElementByXpath(driver, locator).getAttribute(attributeName);
	}

	public void selectValueInDropdow(WebDriver driver, String locator, String value) {
		select = new Select(findElementByXpath(driver, locator));
		select.selectByVisibleText(value);
	}

	public String getSelectedItemInDropdown(WebDriver driver, String locator) {
		select = new Select(findElementByXpath(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectItemInCustomDropDown(WebDriver driver, String parentXpath, String allItemXpath,
			String itemSelectedXpath, String expectedValueItem) {
		element = findElementByXpath(driver, parentXpath);

		jsExecutor.executeScript("arguments[0].click();", element);
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(allItemXpath)));

		elements = findElementsByXpath(driver, allItemXpath);

		for (WebElement childElement : elements) {
			if (childElement.getText().equals(expectedValueItem)) {
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);
					jsExecutor.executeScript("arguments[0].click();", childElement);
				}
				sleepInSecond(1);
				break;

			}
		}
	}

	public int countElementNumber(WebDriver driver, String locator) {
		elements = findElementsByXpath(driver, locator);
		return elements.size();
	}

	public int countElementNumber(WebDriver driver, String locator, String... values) {
		elements = findElementsByXpath(driver, castToObject(locator, values));
		return elements.size();
	}

	public int GetNumberOfSearchResultItem(WebDriver driver, String searchResultLocator, String searchKeyword) {
		int numberOfResult = 0;
		elements = findElementsByXpath(driver, searchResultLocator);
		for (WebElement resultElement : elements) {
			if (resultElement.getText().contains(searchKeyword)) {
				numberOfResult = numberOfResult + 1;
				System.out.println("Result : " + resultElement.getText());
			}
		}
		return numberOfResult;
	}

	public boolean isListSortAscending(WebDriver driver, String listLocator) {
		elements = findElementsByXpath(driver, listLocator);
		List<String> originalList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();

		for (WebElement e : elements) {
			originalList.add(e.getText());
			tempList.add(e.getText());
		}
		System.out.println("OriginalList before soring tempList" + originalList);

		Collections.sort(tempList);
		System.out.println("OriginalList after sorting tempList" + originalList);
		System.out.println("This is tempList" + tempList);

		if (tempList.size() == originalList.size()) {
			if (tempList.equals(originalList)) {
				return true;
			}
		}
		return false;
	}

	public boolean isListSortDescending(WebDriver driver, String listLocator) {
		elements = findElementsByXpath(driver, listLocator);
		List<String> originalList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();

		for (WebElement e : elements) {
			originalList.add(e.getText());
			tempList.add(e.getText());
		}
		System.out.println("OriginalList before soring tempList" + originalList);

		Collections.sort(tempList);
		Collections.reverse(tempList);

		System.out.println("OriginalList after sorting tempList" + originalList);
		System.out.println("This is tempList" + tempList);

		if (tempList.size() == originalList.size()) {
			if (tempList.equals(originalList)) {
				return true;
			}
		}
		return false;

	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}
	public void checkToCheckbox(WebDriver driver, String locator, String ...values) {
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
	public void uncheckToCheckbox(WebDriver driver, String locator, String ...values) {
		element = findElementByXpath(driver, castToObject(locator, values));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return findElementByXpath(driver, locator).isDisplayed();
		} catch (NoSuchElementException noSuchException) {
			// noSuchException.printStackTrace();
			return false;
		}

	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... value) {
		return findElementByXpath(driver, castToObject(locator, value)).isDisplayed();
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = findElementsByXpath(driver, locator);

		if (elements.size() == 0) {
			System.out.println("Element is NOT in DOM");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element is in DOM BUT not visible/ displayed");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else {
			System.out.println("Element is in DOM and visible/ displayed");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, String... value) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = findElementsByXpath(driver, castToObject(locator, value));

		if (elements.size() == 0) {
			System.out.println("Element is NOT in DOM");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element is in DOM BUT not visible/ displayed");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else {
			System.out.println("Element is in DOM and visible/ displayed");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isSelected();
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isEnabled();
	}

	public void switchToFrameOrIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(findElementByXpath(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(driver, locator)).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, locator), key).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String... values) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, castToObject(locator, values)), key).perform();
	}

	// Java script for Browser
	public Object executeForBrowser(WebDriver driver, String javaSript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(WebDriver driver, String expectedText) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
		return textActual.equals(expectedText);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	// Java script for Element
	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 5px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", findElementByXpath(driver, locator));
	}
	
	public void clickToElementByJS(WebDriver driver, String locator,  String ...values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", findElementByXpath(driver, castToObject(locator, values)));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, locator));
	}
	public void scrollToElement(WebDriver driver, String locator, String ...values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, castToObject(locator, values)));
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
	
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String ...value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				findElementByXpath(driver, castToObject(locator, value)));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor
				.executeScript(
						"return arguments[0].complete && typeof arguments[0]"
								+ ".naturalWidth != 'undefined' && arguments[0]" + ".naturalWidth > 0",
								findElementByXpath(driver, locator));
		if (status) {
			return true;
		}
		return false;
		
	}
	public boolean isImageLoaded(WebDriver driver, String locator, String ...values) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor
				.executeScript(
						"return arguments[0].complete && typeof arguments[0]"
								+ ".naturalWidth != 'undefined' && arguments[0]" + ".naturalWidth > 0",
						findElementByXpath(driver, castToObject(locator, values)));
		if (status) {
			return true;
		}
		return false;

	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... value) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(locator, value))));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}
	public void waitForElementInvisible(WebDriver driver, String locator, String... value) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToObject(locator, value))));
	}

	public void waitForElementsInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		elements = findElementsByXpath(driver, locator);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... value) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(locator, value))));
		System.out.println(findElementByXpath(driver, castToObject(locator, value)));
	}

	// Navigate to Page WordPress

	public PostsPageObject clickToPostsMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.POSTS_LINK);
		clickToElement(driver, AbstractPageUI.POSTS_LINK);
		return PageGeneratorManager.getPostsAdminPage(driver);
	}

	public MediaPageObject clickToMediaMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.MEDIA_LINK);
		clickToElement(driver, AbstractPageUI.MEDIA_LINK);
		return PageGeneratorManager.getMediaAdminPage(driver);
	}

	public PagesPageObject clickToPagesMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.PAGES_LINK);
		clickToElement(driver, AbstractPageUI.PAGES_LINK);
		return PageGeneratorManager.getPagesAdminPage(driver);

	}

	// Dynamic locator: apply for app less common pages (10>15>20)

	public AbstractPage openMenuPageByPageName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);

		switch (pageName) {
		case "Posts":
			return PageGeneratorManager.getPostsAdminPage(driver);
//		case "Media":
//			return PageGeneratorManager.getMediaPage(driver);
//		case "Pages":
//			return PageGeneratorManager.getPagesPage(driver);

		default:
			return PageGeneratorManager.getDashboardAdminPage(driver);
		}
	}

	public void openMenuPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
	}

	public HomePageObject OpenEndUserPage(WebDriver driver) {
		openURL(driver, GlobalConstants.WORDPRESS_USER_URL);
		return PageGeneratorManager.getHomeUserPage(driver);
	}
	
	public DashboardPageObject OpenAdminLoggedPage(WebDriver driver) {
		openURL(driver, GlobalConstants.WORDPRESS_ADMIN_URL);
		return PageGeneratorManager.getDashboardAdminPage(driver);
	}
	
	public SearchResultPageObject inputToSearchTextboxAtEndUserPage(WebDriver driver, String searchValue) {
		waitForElementVisible(driver, AbstractPageUI.SEARCH_ICON);
		clickToElement(driver, AbstractPageUI.SEARCH_ICON);
		sendkeyToElement(driver, AbstractPageUI.SEARCH_TEXTBOX, searchValue);
		clickToElement(driver, AbstractPageUI.SEARCH_BUTTON);
		return PageGeneratorManager.getSearchResultUserPage(driver);
	}
	
	public PostDetailPageObject clickToPostDetailWithTitleName(WebDriver driver, String postTitle) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_POST_TITLE, postTitle);
		clickToElement(driver, AbstractPageUI.DYNAMIC_POST_TITLE, postTitle);
		return PageGeneratorManager.getPostDetailUserPage(driver);
	}
	public boolean isSuccessMessageDisplayedWithValue(WebDriver driver, String successMessage) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_SUCCESS_MESSAGE_ON_POSTS_OR_PAGE_PAGE, successMessage);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_SUCCESS_MESSAGE_ON_POSTS_OR_PAGE_PAGE, successMessage);
	}


	public boolean isRowValueDisplayedWithText(WebDriver driver,String colName, String rowValue) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_ROW_VALUE_AT_COLUMN_NAME, colName,rowValue );
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_ROW_VALUE_AT_COLUMN_NAME, colName,rowValue );
	}
	
	public boolean isRowValueUnDisplayedWithText(WebDriver driver,String colName, String rowValue) {
		waitForElementInvisible(driver, AbstractPageUI.DYNAMIC_ROW_VALUE_AT_COLUMN_NAME, colName,rowValue );
		return isElementUndisplayed(driver, AbstractPageUI.DYNAMIC_ROW_VALUE_AT_COLUMN_NAME, colName,rowValue );
	}
	
	

	public boolean isPostDisplayedOnLatestPost(WebDriver driver,String categoryName, String postTitle, String date) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoryName, postTitle, date);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoryName, postTitle, date);
	}
	
	public boolean isPostUnDisplayedOnLatestPost(WebDriver driver,String categoryName, String postTitle, String date) {
		waitForElementInvisible(driver, AbstractPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoryName, postTitle, date);
		return isElementUndisplayed(driver, AbstractPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoryName, postTitle, date);
	}
	
	public boolean isPostImageDisplayedAtPostTitleName(WebDriver driver,String postTitle, String imageFileName) {
		String [] files = imageFileName.split("\\.");
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_POST_IMAGE_WITH_TITLE, postTitle, files[0]);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_POST_IMAGE_WITH_TITLE, postTitle, files[0]) 
				&& isImageLoaded(driver, AbstractPageUI.DYNAMIC_POST_IMAGE_WITH_TITLE,  postTitle, files[0]);
	}
	
	public boolean isPostImageUnDisplayedAtPostTitleName(WebDriver driver,String postTitle, String imageFileName) {
		String [] files = imageFileName.split("\\.");
		waitForElementInvisible(driver, AbstractPageUI.DYNAMIC_POST_IMAGE_WITH_TITLE, postTitle, files[0]);
		return isElementUndisplayed(driver, AbstractPageUI.DYNAMIC_POST_IMAGE_WITH_TITLE, postTitle, files[0]);
				
	}
	
	
	
	private Select select;
	private Actions action;
	private WebElement element;
	private List<WebElement> elements;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;

}
