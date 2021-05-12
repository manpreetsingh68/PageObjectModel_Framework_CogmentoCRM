package com.pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageObject.base.BasePage;

public class LoginPage extends BasePage {
	
	@FindBy(xpath = "//input[@name='email']")
	private WebElement txtEmailAddress;
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement txtPassword;
	
	@FindBy(xpath = "//div[text()='Login']")
	private WebElement btnLogin;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void loginToApplication() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(config.getProperty("email"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(config.getProperty("password"));
		driver.findElement(By.xpath("//div[text()='Login']")).click();
		
		log.debug("Logged into application");
	}
	
}
