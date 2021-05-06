package com.pageObject.pages;

import com.pageObject.base.BasePage;

public class HomePage extends BasePage {
	
	public boolean verifyHomePageLoads() {
		return isElementPresent(OR.getProperty("btnHome"));
	}
}
