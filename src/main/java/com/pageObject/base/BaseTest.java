package com.pageObject.base;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.pageObject.pages.HomePage;
import com.pageObject.pages.LoginPage;

public class BaseTest {
	
	BasePage basePage = new BasePage();
	LoginPage loginPage = new LoginPage();
	HomePage homePage = new HomePage();
	
	@BeforeMethod
	public void loginToApp() {
		loginPage.loginToApplication();
	}
	
	@AfterMethod
	public void logoutOfApp() {
		homePage.logoutOfApplication();
	}
	
	@AfterSuite
	public void tearDown() {
		basePage.quit();
	}
	
	public void isTestRunnable(Hashtable<String, String> data) {
		if(data.get("runmode").equalsIgnoreCase("N") || data.get("runmode").equalsIgnoreCase("No")) { 
			throw new SkipException("Skipping the test case as run mode is set to NO");
		}	
	}
}
	

