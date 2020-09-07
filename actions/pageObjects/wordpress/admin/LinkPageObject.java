package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.user.HomePageUI;

public class LinkPageObject extends AbstractPage {
	
	WebDriver driver;
	
	public LinkPageObject(WebDriver mapDriver) {
		driver= mapDriver;
		System.out.println("Driver at LinkPage " + driver.toString());
	}


}
