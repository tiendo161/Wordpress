package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.wordpress.user.HomePageUI;
import pageUI.wordpress.user.PostsPageUI;

public class HomePageObject extends AbstractPage {

	WebDriver driver;

	public HomePageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public void clickToCloseAndAcceptButton() {
		waitForElementClickable(driver, HomePageUI.CLOSE_AND_ACCEPT_BUTTON);
		clickToElement(driver, HomePageUI.CLOSE_AND_ACCEPT_BUTTON);
		
	}






}
