package com.pageObject.pages;

import com.pageObject.base.BasePage;

public class ContactsPage extends BasePage {
	
	public CreateNewContactPage addContact() {
		click(OR.getProperty("btnCreateContact"));
		
		return new CreateNewContactPage();
	}
	
	public boolean verifyNewContactAdded() {
		return isElementPresent(OR.getProperty("btnHome"));
	}
	
}
