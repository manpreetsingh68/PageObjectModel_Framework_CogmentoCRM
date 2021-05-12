package com.pageObject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageObject.base.BasePage;

public class DealsPage extends BasePage {
	
	@FindBy(xpath = "//button[text()='Create']")
	private WebElement btnCreateDeal;
	
	@FindBy(xpath = "//span[text()='Home']//preceding::i")
	private WebElement btnHome;
	
	public DealsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public CreateNewDealPage addDeal() {
		click(btnCreateDeal);
		
		return new CreateNewDealPage();
	}
	
	public boolean verifyNewDealAdded() {
		return isElementPresent(btnHome);
	}
	
}
