package com.pageObject.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageObject.base.BasePage;

public class CreateNewContactPage extends BasePage {
	
	@FindBy(xpath = "//input[@name='first_name']")
	private WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@name='last_name']")
	private WebElement txtLastName;
	
	@FindBy(xpath = "//div[@name='company']//input")
	private WebElement txtCompany;
	
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnSave;
	
	public CreateNewContactPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void enterNewContactData(Hashtable<String, String> data) {
		sendKeys(txtFirstName, data.get("firstname"));
		sendKeys(txtLastName, data.get("lastname"));
		sendKeys(txtCompany, data.get("company"));
		click(btnSave);
	}
}
