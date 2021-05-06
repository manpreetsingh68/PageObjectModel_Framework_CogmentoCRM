package com.pageObject.pages;

import java.util.Hashtable;

import com.pageObject.base.BasePage;

public class CreateNewDealPage extends BasePage {
	
	public void enterNewDealData(Hashtable<String, String> data) {
		sendKeys(OR.getProperty("txtTitle"), data.get("title"));
		sendKeys(OR.getProperty("txtCompany"), data.get("company"));
		sendKeys(OR.getProperty("txtDescription"), data.get("description"));
		click(OR.getProperty("btnSave"));
	}
}
