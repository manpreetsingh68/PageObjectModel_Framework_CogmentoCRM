package com.pageObject.pages;

import java.util.Hashtable;

import com.pageObject.base.BasePage;

public class CreateNewContactPage extends BasePage {
	
	public void enterNewContactData(Hashtable<String, String> data) {
		sendKeys(OR.getProperty("txtFirstName"), data.get("firstname"));
		sendKeys(OR.getProperty("txtLastName"), data.get("lastname"));
		sendKeys(OR.getProperty("txtCompany"), data.get("company"));
		click(OR.getProperty("btnSave"));
	}
}
