package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.user.HomePageUI;
import pageUI.wordpress.user.PostsPageUI;
import pageUI.wordpress.user.SearchResultPageUI;

public class SearchResultPageObject extends AbstractPage {

	WebDriver driver;

	public SearchResultPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public boolean isPostTitleDisplayedInSearchResultHeader(String newPostTitle) {
		waitForElementVisible(driver, SearchResultPageUI.SEARCH_RESULT_HEADER, newPostTitle);
		return isElementDisplayed(driver, SearchResultPageUI.SEARCH_RESULT_HEADER, newPostTitle);
	}

}
