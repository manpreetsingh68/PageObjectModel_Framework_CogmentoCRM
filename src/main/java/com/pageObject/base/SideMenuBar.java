package com.pageObject.base;

import com.pageObject.pages.ContactsPage;
import com.pageObject.pages.DealsPage;

public class SideMenuBar extends BasePage {

	public void navigateToHome() {
	}

	public void navigateToCalendar() {

	}

	public ContactsPage navigateToContacts() {
		moveToElement(findElement(OR.getProperty("lnkContacts")));
		click(OR.getProperty("lnkContactsSpan"));
		
		return new ContactsPage();
	}

	public void navigateToCompanies() {

	}

	public DealsPage navigateToDeals() {
		moveToElement(findElement(OR.getProperty("lnkDeals")));
		click(OR.getProperty("lnkDealsSpan"));
		
		return new DealsPage();
	}

	public void navigateToTasks() {

	}

	public void navigateToCases() {

	}

}
