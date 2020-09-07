package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.user.HomePageUI;
import pageUI.wordpress.user.MediaPageUI;

public class MediaPageObject extends AbstractPage {
	
	WebDriver driver;
	
	public MediaPageObject(WebDriver mapDriver) {
		driver= mapDriver;
		System.out.println("Driver at MediaPage " + driver.toString());
	}

	public void clickToAddNewButton() {
		waitForElementClickable(driver, MediaPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, MediaPageUI.ADD_NEW_BUTTON);
		
	}



}
