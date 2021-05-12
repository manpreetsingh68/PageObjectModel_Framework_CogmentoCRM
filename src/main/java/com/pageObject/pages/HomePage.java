package com.pageObject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageObject.base.BasePage;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends BasePage {
	
	@FindBy(xpath = "(//i[@class='settings icon'])[1]")
	private WebElement btnSettingsGear;
	
	@FindBy(xpath = "//span[text()='Home']//preceding::i")
	private WebElement btnHome;
	
	@FindBy(xpath = "//span[text()='Log Out']")
	private WebElement lnkLogOut;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void logoutOfApplication() {
		waitForElementToBeVisible(btnSettingsGear, 60);
		click(btnSettingsGear);
		click(lnkLogOut);
		
		log.debug("Logged out of application");
		test.log(LogStatus.INFO, "Logged out of application");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean verifyHomePageLoads() {
		return isElementPresent(btnHome);
	}
}
