package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.wordpress.admin.NewEditPostPageUI;
import pageUI.wordpress.user.HomePageUI;
import pageUI.wordpress.user.PostsPageUI;

public class NewEditPostPageObject extends AbstractPage {

	WebDriver driver;

	public NewEditPostPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public void inputToPostContentTextbox(String inputValue) {
		// switch to Iframe
		switchToFrameOrIframe(driver, NewEditPostPageUI.TINY_MCE_IFRAME);
		// Input content
		waitForElementVisible(driver, NewEditPostPageUI.TINY_MCE_TEXTBOX);
		sendkeyToElement(driver, NewEditPostPageUI.TINY_MCE_TEXTBOX, inputValue);
		// Switch to default
		switchToDefaultContent(driver);

	}

	public void clickToPublishOrUpdateButton() {
		scrollToElement(driver, NewEditPostPageUI.PUBLISH_OR_UPDATE_BUTTON);
		waitForElementClickable(driver, NewEditPostPageUI.PUBLISH_OR_UPDATE_BUTTON);
		clickToElementByJS(driver, NewEditPostPageUI.PUBLISH_OR_UPDATE_BUTTON);
	}


	public PostsPageObject clickToMoveToTrashButton() {
		waitForElementClickable(driver, NewEditPostPageUI.MOVE_TO_TRASH_LINK);
		clickToElement(driver, NewEditPostPageUI.MOVE_TO_TRASH_LINK);
		return PageGeneratorManager.getPostsAdminPage(driver);
	}

	public void inputToPostTitleTextbox(String inputValue) {
		waitForElementVisible(driver, NewEditPostPageUI.ADD_TITLE_TEXT);
		sendkeyToElement(driver, NewEditPostPageUI.ADD_TITLE_TEXT, inputValue);

	}



	public void inputToTagTextbox(String tagValue) {
		waitForElementVisible(driver, NewEditPostPageUI.TAG_TEXTBOX);
		sendkeyToElement(driver, NewEditPostPageUI.TAG_TEXTBOX, tagValue);
	}

	public void clickToAddTagButton() {
		waitForElementClickable(driver, NewEditPostPageUI.ADD_TAG_BUTTON);
		clickToElement(driver, NewEditPostPageUI.ADD_TAG_BUTTON);
	}

	public void clickToSetFeatureImageLink() {
		waitForElementClickable(driver, NewEditPostPageUI.SET_FEATURE_IMAGE_LINK);
		clickToElement(driver, NewEditPostPageUI.SET_FEATURE_IMAGE_LINK);
		
	}

	public void clickToUploadFileTab() {
		waitForElementClickable(driver, NewEditPostPageUI.UPLOAD_FILES_TAB);
		clickToElement(driver, NewEditPostPageUI.UPLOAD_FILES_TAB);
		
	}

	public void clickToSetFeatureImageButton() {
		waitForElementClickable(driver, NewEditPostPageUI.SET_FEATURE_IMAGE_BUTTON);
		clickToElement(driver, NewEditPostPageUI.SET_FEATURE_IMAGE_BUTTON);
		
	}

	public boolean isFeatureImageThumbnailDisplayed(String imageName) {
		String [] files = imageName.split("\\.");
		waitForElementVisible(driver, NewEditPostPageUI.FEATURE_IMAGE_THUMBNAIL, files[0]);
		return isElementDisplayed(driver, NewEditPostPageUI.FEATURE_IMAGE_THUMBNAIL, files[0]);
	}

	public void deSelectCategoryCheckbox(String newPostCategory) {
		waitForElementClickable(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, newPostCategory);
		uncheckToCheckbox(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, newPostCategory);
		
	}

	public void selectCategoryCheckbox(String checkboxLabelText) {
		
		waitForElementClickable(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxLabelText);
		//clickToElementByJS(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxLabelText);
		checkToCheckbox(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxLabelText);
		
	}

	public void clickToDeleteTagIconWithTagName(String newPostTag) {
		waitForElementClickable(driver, NewEditPostPageUI.DELETE_TAG_BUTTON, newPostTag);
		clickToElement(driver, NewEditPostPageUI.DELETE_TAG_BUTTON, newPostTag);
	}
}
