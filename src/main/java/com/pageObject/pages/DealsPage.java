package com.pageObject.pages;

import com.pageObject.base.BasePage;

public class DealsPage extends BasePage {
	
	public CreateNewDealPage addDeal() {
		click(OR.getProperty("btnCreateDeal"));
		
		return new CreateNewDealPage();
	}
	
	public boolean verifyNewDealAdded() {
		return isElementPresent(OR.getProperty("btnHome"));
	}
	
}
