package com.pageObject.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	BasePage basePage = new BasePage();
	
	@BeforeMethod
	public void loginToApp() {
		basePage.loginToApplication();
	}
	
	@AfterMethod
	public void logoutOfApp() {
		basePage.logoutOfApplication();
	}
	
	@AfterSuite
	public void tearDown() {
		basePage.quit();
	}
	
}
