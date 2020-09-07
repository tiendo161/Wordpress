package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.user.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;

	public LoginPageObject(WebDriver mapDriver) {
		driver = mapDriver;
		System.out.println("Driver at Login Page " + driver.toString());
	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}

	public void clickToContinueOrLoginButton() {
		waitForElementVisible(driver, LoginPageUI.CONTINUE_OR_LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.CONTINUE_OR_LOGIN_BUTTON);

	}

	public String getEmailOrPasswordErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_OR_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_OR_PASSWORD_ERROR_MESSAGE);
	}

	public void inputToPassowrdTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public String getLoginPageURL() {
		return driver.getCurrentUrl();
	}

	public void openLoginPageURL(String loginPageURL) {
		openURL(driver, loginPageURL);

	}

}
