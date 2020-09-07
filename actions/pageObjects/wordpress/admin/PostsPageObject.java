package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.wordpress.admin.AdminPostsPageUI;
import pageUI.wordpress.admin.NewEditPostPageUI;
import pageUI.wordpress.user.HomePageUI;
import pageUI.wordpress.user.PostsPageUI;

public class PostsPageObject extends AbstractPage {

	WebDriver driver;

	public PostsPageObject(WebDriver mapDriver) {
		driver = mapDriver;
		System.out.println("Driver at PostsPage " + driver.toString());
	}

	public NewEditPostPageObject clickToPostDetailByTitleName(String postTitle) {
		
		waitForElementClickable(driver, NewEditPostPageUI.POST_TITLE_LINK, postTitle);
		clickToElement(driver, NewEditPostPageUI.POST_TITLE_LINK, postTitle);
		return PageGeneratorManager.getNewOrEditPostAdminPage(driver);
	}

	public NewEditPostPageObject clickToAddNewButton() {
		waitForElementClickable(driver, AdminPostsPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminPostsPageUI.ADD_NEW_BUTTON);
		return PageGeneratorManager.getNewOrEditPostAdminPage(driver);
	}

	public void inputToSearchTextbox(String postTitle) {
		waitForElementVisible(driver, AdminPostsPageUI.SEARCH_POST_TEXTFIELD);
		sendkeyToElement(driver, AdminPostsPageUI.SEARCH_POST_TEXTFIELD, postTitle);
		
	}

	public void clickToSearchPostsButton() {
		waitForElementVisible(driver, AdminPostsPageUI.SEARCH_POST_BUTTON);
		clickToElement(driver, AdminPostsPageUI.SEARCH_POST_BUTTON);
	}

	public boolean isNoPostFoundMessageDisplayed(String noPostFoundMessage) {
		waitForElementVisible(driver, AdminPostsPageUI.NO_POST_FOUND_SEARCH_RESULT_MESSAGE);
		return isElementDisplayed(driver, AdminPostsPageUI.NO_POST_FOUND_SEARCH_RESULT_MESSAGE);
	}


}
