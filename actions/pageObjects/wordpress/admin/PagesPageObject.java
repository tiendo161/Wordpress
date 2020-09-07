package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.user.HomePageUI;
import pageUI.wordpress.user.PagesPageUI;

public class PagesPageObject extends AbstractPage {
	
	WebDriver driver;
	
	public PagesPageObject(WebDriver mapDriver) {
		driver= mapDriver;
		System.out.println("Driver at PagesPage " + driver.toString());
	}



}
