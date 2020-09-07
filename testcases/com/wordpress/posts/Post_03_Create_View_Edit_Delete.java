package com.wordpress.posts;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.LoginPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Post_03_Create_View_Edit_Delete extends AbstractTest {
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-Condition: Open browser");
		driver = getBrowserDriver(browserName);
		
		log.info("Pre-Condition- STEP 01: Open Login Page");
		loginPage = PageGeneratorManager.getLoginAdminPage(driver);
		
		log.info("Pre-Condition- STEP 02: Input to Email textbox");
		loginPage.inputToEmailTextbox("automationeditor");
		
		log.info("Pre-Condition- STEP 03: Click To Login Button");
		loginPage.clickToContinueOrLoginButton();
		
		log.info("Pre-Condition- STEP 04: Input to Password textbox");
		loginPage.inputToPassowrdTextbox("automationfc");
		
		log.info("Pre-Condition- STEP 05: Click to login button");
		loginPage.clickToContinueOrLoginButton();
		
		log.info("Pre-Condition- STEP 06: Verify Header Text display");
		dashboardPage = PageGeneratorManager.getDashboardAdminPage(driver);
		verifyTrue(dashboardPage.isHeaderTextDisplayed());
	}

	@Test
	public void TC_01_Element_Undisplay_In_DOM() {
		
	}

	@AfterClass
	public void afterClass() {
		log.info("Post-Condition: Quit browser");
		driver.quit();
	}

}
