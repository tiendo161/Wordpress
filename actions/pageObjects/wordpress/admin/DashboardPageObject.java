package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.admin.DashboardPageUI;
import pageUI.wordpress.user.HomePageUI;

public class DashboardPageObject extends AbstractPage {
	
	WebDriver driver;
	
	public DashboardPageObject(WebDriver mapDriver) {
		driver= mapDriver;
		//System.out.println("Driver at DashboardPageObject " + driver.toString());
	}

	public boolean isHeaderTextDisplayed() {
		waitForElementVisible(driver, DashboardPageUI.HEADER_TEXT);
		return isElementDisplayed(driver, DashboardPageUI.HEADER_TEXT);
	}

	public void clickToScreenOption() {
		waitForElementVisible(driver, DashboardPageUI.SCREEN_OPTION_BUTTON);
		clickToElement(driver, DashboardPageUI.SCREEN_OPTION_BUTTON);
		sleepInSecond(2);
		
	}

	public boolean IsActivityCheckboxDisplayed() {
		return isElementDisplayed(driver, DashboardPageUI.ACTIVITY_CHECKBOX);
	}

	public boolean IsAllPostsSubmenuDisplay() {
		return isElementDisplayed(driver, DashboardPageUI.ALL_POSTS_SUBMENU_LINK);
	}


	public boolean isPlansMenuUndisplayed() {
		return isElementUndisplayed(driver, DashboardPageUI.PLANS_PAGE_LINK);
	}

	

}
