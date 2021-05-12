package com.pageObject.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageObject.pages.ContactsPage;
import com.pageObject.pages.DealsPage;

public class SideMenuBar extends BasePage {
	
	@FindBy(xpath = "//span[text()='Contacts']")
	private WebElement lnkContactsSpan;
	
	@FindBy(xpath = "//a[@class='item'][@href='/contacts']")
	private WebElement lnkContacts;
	
	@FindBy(xpath = "//span[text()='Deals']")
	private WebElement lnkDealsSpan;
	
	@FindBy(xpath = "//a[@class='item'][@href='/deals']")
	private WebElement lnkDeals;
	
	public SideMenuBar() {
		PageFactory.initElements(driver, this);
	}
	
	public void navigateToHome() {
	}

	public void navigateToCalendar() {

	}

	public ContactsPage navigateToContacts() {
		moveToElement(lnkContacts);
		click(lnkContactsSpan);
		
		return new ContactsPage();
	}

	public void navigateToCompanies() {

	}

	public DealsPage navigateToDeals() {
		moveToElement(lnkDeals);
		click(lnkDealsSpan);
		
		return new DealsPage();
	}

	public void navigateToTasks() {

	}

	public void navigateToCases() {

	}

}
