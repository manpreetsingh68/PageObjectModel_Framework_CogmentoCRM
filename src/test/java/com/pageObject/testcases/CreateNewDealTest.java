package com.pageObject.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.pageObject.base.BasePage;
import com.pageObject.base.BaseTest;
import com.pageObject.pages.CreateNewDealPage;
import com.pageObject.pages.DealsPage;
import com.pageObject.pages.HomePage;
import com.pageObject.utils.TestUtil;

public class CreateNewDealTest extends BaseTest {
	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void createNewDealTest(Hashtable<String, String> data) {
		
		if(data.get("runmode").equalsIgnoreCase("N") || data.get("runmode").equalsIgnoreCase("No")) {
			throw new SkipException("Skipping the test case as run mode is set to NO");
		}
		
		HomePage homePage = new HomePage();
		Assert.assertTrue(homePage.verifyHomePageLoads(), "User is not able to land on Home Page");
		
		DealsPage dealsPage = BasePage.sideMenuBar.navigateToDeals();
		CreateNewDealPage createNewDealPage = dealsPage.addDeal();
		createNewDealPage.enterNewDealData(data);
		
		Reporter.log("Contact added successfully-> " + data.get("firstname") + " " + data.get("lastname"));
		Assert.assertTrue(dealsPage.verifyNewDealAdded(), "User is not able to create a new contact");
	}
}
