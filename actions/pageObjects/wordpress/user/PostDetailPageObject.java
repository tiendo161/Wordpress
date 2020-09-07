package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.user.AbstractPageUI;
import pageUI.wordpress.user.HomePageUI;
import pageUI.wordpress.user.PostDetailPageUI;
import pageUI.wordpress.user.PostsPageUI;

public class PostDetailPageObject extends AbstractPage {

	WebDriver driver;

	public PostDetailPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public boolean isCategoryNameDisplayed(String newPostCategoryCheckbox) {
		waitForElementVisible(driver, PostDetailPageUI.CATEGORY_NAME, newPostCategoryCheckbox);
		return isElementDisplayed(driver, PostDetailPageUI.CATEGORY_NAME, newPostCategoryCheckbox);
	}

	public boolean isTitleNameDisplayed(String postTitle) {
		waitForElementVisible(driver, PostDetailPageUI.POST_TITLE_NAME, postTitle);
		return isElementDisplayed(driver, PostDetailPageUI.POST_TITLE_NAME, postTitle);
	}

	public boolean isImageDisplayed(String featureImageName) {
		featureImageName = featureImageName.split("\\.")[0];
		waitForElementVisible(driver, PostDetailPageUI.POST_AVATA_IMAGE_NAME, featureImageName);
		return isElementDisplayed(driver, PostDetailPageUI.POST_AVATA_IMAGE_NAME, featureImageName) &&
				isImageLoaded(driver, PostDetailPageUI.POST_AVATA_IMAGE_NAME, featureImageName);
	}

	public boolean isContentDisplayed(String postContentValue) {
		waitForElementVisible(driver, PostDetailPageUI.POST_CONTENT_VALUE, postContentValue);
		return isElementDisplayed(driver, PostDetailPageUI.POST_CONTENT_VALUE, postContentValue);
	}

	public boolean isDateCreatedDisplayed(String dateValue) {
		waitForElementVisible(driver, PostDetailPageUI.POST_DATE_VALUE, dateValue);
		return isElementDisplayed(driver, PostDetailPageUI.POST_DATE_VALUE, dateValue);
	}

	public boolean isAuthorDisplayed(String authorName) {
		waitForElementVisible(driver, PostDetailPageUI.POST_AUTHOR_NAME, authorName);
		return isElementDisplayed(driver, PostDetailPageUI.POST_AUTHOR_NAME, authorName);
	}

}
