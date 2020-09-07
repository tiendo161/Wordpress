package com.wordpress.posts;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.LoginPageObject;
import pageObjects.wordpress.admin.NewEditPostPageObject;
import pageObjects.wordpress.admin.PostsPageObject;
import pageObjects.wordpress.user.HomePageObject;
import pageObjects.wordpress.user.PostDetailPageObject;
import pageObjects.wordpress.user.SearchResultPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Post_01_Create_View_Edit_Delete extends AbstractTest {
	int fakeNumber = randomNumber();
	String postDate;
	String featureImageName = "newpost.jpg";
	String newPostTitle, newPostContent, NewPostTag, newPostCategory, author;
	String editPostTitle, editPostTag, editPostCategory;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		newPostTitle = "[TienDT] New_Post_Title " + fakeNumber;
		newPostContent = "[TienDT] New Post Content " + fakeNumber;
		NewPostTag = "[TienDT] Tag " + fakeNumber;
		newPostCategory = "NEW LIVE CODING";
		author = "Automation FC";
		postDate = getWordpressToday();

		editPostTitle = "[TienDT] Edit_Post_Title " + fakeNumber;
		editPostTag = "[TienDT] Edit Tag " + fakeNumber;
		editPostCategory = "EDIT LIVE CODING";

		log.info("Pre-Condition: Open browser");
		driver = getBrowserDriver(browserName);

		log.info("Pre-Condition- STEP 01: Open Login Page");
		loginAdminPage = PageGeneratorManager.getLoginAdminPage(driver);

		log.info("Pre-Condition- STEP 02: Input to Email textbox");
		loginAdminPage.inputToEmailTextbox("automationeditor");

		log.info("Pre-Condition- STEP 03: Click To Login Button");
		loginAdminPage.clickToContinueOrLoginButton();

		log.info("Pre-Condition- STEP 04: Input to Password textbox");
		loginAdminPage.inputToPassowrdTextbox("automationfc");

		log.info("Pre-Condition- STEP 05: Click to login button");
		loginAdminPage.clickToContinueOrLoginButton();

		log.info("Pre-Condition- STEP 06: Verify Header Text display");
		dashboardAdminPage = PageGeneratorManager.getDashboardAdminPage(driver);
		verifyTrue(dashboardAdminPage.isHeaderTextDisplayed());
	}

	@Test
	public void Post_01_Create_New_Post_At_Admin_Page() {
		// Go to Posts Page
		dashboardAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);
		newEditPostsAdminPage = postsAdminPage.clickToAddNewButton();

		newEditPostsAdminPage.inputToPostTitleTextbox(newPostTitle);

		newEditPostsAdminPage.inputToPostContentTextbox(newPostContent);

		newEditPostsAdminPage.selectCategoryCheckbox(newPostCategory);

		newEditPostsAdminPage.inputToTagTextbox(NewPostTag);

		newEditPostsAdminPage.clickToAddTagButton();

		newEditPostsAdminPage.clickToSetFeatureImageLink();

		newEditPostsAdminPage.clickToUploadFileTab();

		newEditPostsAdminPage.uploadMultipleFiles(driver, featureImageName);

		verifyTrue(newEditPostsAdminPage.areFileUploadedDisplay(driver, featureImageName));

		newEditPostsAdminPage.clickToSetFeatureImageButton();

		verifyTrue(newEditPostsAdminPage.isFeatureImageThumbnailDisplayed(featureImageName));

		newEditPostsAdminPage.clickToPublishOrUpdateButton();

		verifyTrue(newEditPostsAdminPage.isSuccessMessageDisplayedWithValue(driver, "Post published. "));

		// Search Post At Admin Page
		newEditPostsAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);

		postsAdminPage.inputToSearchTextbox(newPostTitle);

		postsAdminPage.clickToSearchPostsButton();

		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Title", newPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Author", author));
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Categories", newPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Tags", NewPostTag));

		// View Post At User Page

		homeUserPage = postsAdminPage.OpenEndUserPage(driver);

		// Design in Abstract Page (Re-use at Search Result Page)
		verifyTrue(homeUserPage.isPostDisplayedOnLatestPost(driver, newPostCategory, newPostTitle, postDate));
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver, newPostTitle, featureImageName));

		// Go Post Detail at User Page
		homeUserPage.clickToCloseAndAcceptButton();
		postDetailUserPage = homeUserPage.clickToPostDetailWithTitleName(driver, newPostTitle);

		verifyTrue(postDetailUserPage.isCategoryNameDisplayed(newPostCategory));
		verifyTrue(postDetailUserPage.isTitleNameDisplayed(newPostTitle));
		verifyTrue(postDetailUserPage.isImageDisplayed(featureImageName));
		verifyTrue(postDetailUserPage.isContentDisplayed(newPostContent));
		verifyTrue(postDetailUserPage.isDateCreatedDisplayed(postDate));
		verifyTrue(postDetailUserPage.isAuthorDisplayed(author));

		// Search Post at User Page

		searchResultUserPage = postDetailUserPage.inputToSearchTextboxAtEndUserPage(driver, newPostTitle);
		verifyTrue(searchResultUserPage.isPostTitleDisplayedInSearchResultHeader(newPostTitle));

		verifyTrue(searchResultUserPage.isPostDisplayedOnLatestPost(driver, newPostCategory, newPostTitle, postDate));
		verifyTrue(searchResultUserPage.isPostImageDisplayedAtPostTitleName(driver, newPostTitle, featureImageName));
	}

	@Test
	public void Post_02_Edit_Post_At_Admin_Page() {

		// Naviage to Admin site (logged in)

		dashboardAdminPage = searchResultUserPage.OpenAdminLoggedPage(driver);

		// Go to Posts page
		dashboardAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);

		// Search Post at Admin Page
		postsAdminPage.inputToSearchTextbox(newPostTitle);
		postsAdminPage.clickToSearchPostsButton();

		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Title", newPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Author", author));
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Categories", newPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Tags", NewPostTag));

		// Click To Post Detail
		newEditPostsAdminPage = postsAdminPage.clickToPostDetailByTitleName(newPostTitle);

		// Edit Post
		newEditPostsAdminPage.inputToPostTitleTextbox(editPostTitle);
		newEditPostsAdminPage.deSelectCategoryCheckbox(newPostCategory);
		newEditPostsAdminPage.selectCategoryCheckbox(editPostCategory);
		
		newEditPostsAdminPage.inputToTagTextbox(editPostTag);
		newEditPostsAdminPage.clickToAddTagButton();
		newEditPostsAdminPage.clickToDeleteTagIconWithTagName(NewPostTag);
		

		newEditPostsAdminPage.clickToPublishOrUpdateButton();

		verifyTrue(newEditPostsAdminPage.isSuccessMessageDisplayedWithValue(driver,"Post updated"));

		// Search Post At Admin Page
		newEditPostsAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);

		postsAdminPage.inputToSearchTextbox(editPostTitle);

		postsAdminPage.clickToSearchPostsButton();

		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Title", editPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Author", author));
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Categories", editPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Tags", editPostTag));
		
		// View Post At User Page

		homeUserPage = postsAdminPage.OpenEndUserPage(driver);

		// Design in Abstract Page (Re-use at Search Result Page)
		verifyTrue(homeUserPage.isPostDisplayedOnLatestPost(driver, editPostCategory, editPostTitle, postDate));
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver, editPostTitle, featureImageName));


		// Go Post Detail at User Page
		postDetailUserPage = homeUserPage.clickToPostDetailWithTitleName(driver, editPostTitle);

		verifyTrue(postDetailUserPage.isCategoryNameDisplayed(editPostCategory));
		verifyTrue(postDetailUserPage.isTitleNameDisplayed(editPostTitle));
		verifyTrue(postDetailUserPage.isImageDisplayed(featureImageName));
		verifyTrue(postDetailUserPage.isContentDisplayed(newPostContent));
		verifyTrue(postDetailUserPage.isDateCreatedDisplayed(postDate));
		verifyTrue(postDetailUserPage.isAuthorDisplayed(author));


		// Search Post at User Page

		searchResultUserPage = postDetailUserPage.inputToSearchTextboxAtEndUserPage(driver, editPostTitle);
		verifyTrue(searchResultUserPage.isPostTitleDisplayedInSearchResultHeader(editPostTitle));

		verifyTrue(searchResultUserPage.isPostDisplayedOnLatestPost(driver, editPostCategory, editPostTitle, postDate));
		verifyTrue(searchResultUserPage.isPostImageDisplayedAtPostTitleName(driver, editPostTitle, featureImageName));
	}

	@Test
	public void Post_03_Delete_Post_At_Admin_Page() {

		// Naviage to Admin site (logged in)

		dashboardAdminPage = searchResultUserPage.OpenAdminLoggedPage(driver);

		// Go to Posts page
		dashboardAdminPage.openMenuPageByName(driver, "Posts");
		postsAdminPage = PageGeneratorManager.getPostsAdminPage(driver);

		// Search Post at Admin Page
		postsAdminPage.inputToSearchTextbox(editPostTitle);
		postsAdminPage.clickToSearchPostsButton();
		
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Title", editPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Author", author));
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Categories", editPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedWithText(driver, "Tags", editPostTag));
	
		newEditPostsAdminPage = postsAdminPage.clickToPostDetailByTitleName(editPostTitle);

		postsAdminPage = newEditPostsAdminPage.clickToMoveToTrashButton();

		verifyTrue(newEditPostsAdminPage.isSuccessMessageDisplayedWithValue(driver,"1 post moved to the Trash. "));

		// Search Post At Admin Page

		postsAdminPage.inputToSearchTextbox(editPostTitle);

		postsAdminPage.clickToSearchPostsButton();

		// Un-displayed
		verifyTrue(postsAdminPage.isRowValueUnDisplayedWithText(driver, "Title", editPostTitle));
		verifyTrue(postsAdminPage.isRowValueUnDisplayedWithText(driver, "Author", author));
		verifyTrue(postsAdminPage.isRowValueUnDisplayedWithText(driver, "Categories", editPostCategory));
		verifyTrue(postsAdminPage.isRowValueUnDisplayedWithText(driver, "Tags", editPostTag));
		
		// Displayed
		verifyTrue(postsAdminPage.isNoPostFoundMessageDisplayed("No posts found."));

		// View Post At User Page

		homeUserPage = postsAdminPage.OpenEndUserPage(driver);

		// Design in Abstract Page (Re-use at Search Result Page)
		verifyTrue(homeUserPage.isPostUnDisplayedOnLatestPost(driver, editPostCategory, editPostTitle, postDate));
		verifyTrue(homeUserPage.isPostImageUnDisplayedAtPostTitleName(driver, editPostTitle, featureImageName));

		// Search Post at User Page
		searchResultUserPage = homeUserPage.inputToSearchTextboxAtEndUserPage(driver, editPostTitle);

		verifyTrue(searchResultUserPage.isPostUnDisplayedOnLatestPost(driver, editPostCategory, editPostTitle, postDate));
		verifyTrue(searchResultUserPage.isPostImageUnDisplayedAtPostTitleName(driver, editPostTitle, featureImageName));

	}

	@AfterClass
	public void afterClass() {
		// closeBrowserAndDriver(driver);

	}

	WebDriver driver;
	LoginPageObject loginAdminPage;
	DashboardPageObject dashboardAdminPage;
	PostsPageObject postsAdminPage;
	NewEditPostPageObject newEditPostsAdminPage;
	HomePageObject homeUserPage;
	PostDetailPageObject postDetailUserPage;
	SearchResultPageObject searchResultUserPage;

}
