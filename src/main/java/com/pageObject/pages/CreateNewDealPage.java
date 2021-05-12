package com.pageObject.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageObject.base.BasePage;

public class CreateNewDealPage extends BasePage {
	
	@FindBy(xpath = "//input[@name='title']")
	private WebElement txtTitle;
	
	@FindBy(xpath = "//div[@name='company']//input")
	private WebElement txtCompany;
	
	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement txtDescription;
	
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement btnSave;
	
	public CreateNewDealPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void enterNewDealData(Hashtable<String, String> data) {
		sendKeys(txtTitle, data.get("title"));
		sendKeys(txtCompany, data.get("company"));
		sendKeys(txtDescription, data.get("description"));
		click(btnSave);
	}
}
