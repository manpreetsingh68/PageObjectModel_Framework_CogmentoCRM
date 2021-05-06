package com.pageObject.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageObject.utils.ExcelUtil;
import com.pageObject.utils.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasePage {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelUtil excel = new ExcelUtil(
			System.getProperty("user.dir") + "\\src\\test\\resources\\com\\pageObject\\excel\\TestData.xlsx");
	public static ExtentReports report = ExtentManager.getReportInstance();
	public static ExtentTest test;
	public static String browser;
	public static SideMenuBar sideMenuBar;

	public BasePage() {

		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\pageObject\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				config.load(fis);
				log.debug("Config File loaded!");
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\pageObject\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				OR.load(fis);
				log.debug("OR File loaded!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Browser Build parameter from Jenkins (Optional)
			if(System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			}else {
				browser = config.getProperty("browser");
			}
			
			config.setProperty("browser", browser);
			
			//Launch user provided browser
			if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\pageObject\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome browser launched!");
			} else if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\com\\pageObject\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("Firefox browser launched!");
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("timeOuts")),
					TimeUnit.SECONDS);
			driver.get(config.getProperty("url"));
			log.debug("Navigated to : " + config.getProperty("url"));
			
			sideMenuBar = new SideMenuBar();
		}
	}
	
	public void loginToApplication() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath(OR.getProperty("txtEmailAddress"))).sendKeys(config.getProperty("email"));
		driver.findElement(By.xpath(OR.getProperty("txtPassword"))).sendKeys(config.getProperty("password"));
		driver.findElement(By.xpath(OR.getProperty("btnLogin"))).click();
		
		log.debug("Logged into application");
	}
	
	public void logoutOfApplication() {
		waitForElementToBeVisible(OR.getProperty("btnSettingsGear"), 60);
		click(OR.getProperty("btnSettingsGear"));
		click(OR.getProperty("lnkLogOut"));
		
		log.debug("Logged out of application");
		test.log(LogStatus.INFO, "Logged out of application");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void quit() {
		driver.quit();
	}
	
	public WebElement findElement(String locator) {
		waitForElementToBeVisible(locator, 60);
		return driver.findElement(By.xpath(locator));
		
	}
	
	public void click(String locator) {
		waitForElementToBeClickable(locator, 60);
		driver.findElement(By.xpath(locator)).click();
		
		log.debug("Clicked on element " + locator);
		test.log(LogStatus.INFO, "Clicked on element " + locator);
	}
	
	public void sendKeys(String locator, String value) {
		waitForElementToBeVisible(locator, 60);
		driver.findElement(By.xpath(locator)).sendKeys(value);
		
		log.debug("Entered data in element " + locator + " with value " + value);
		test.log(LogStatus.INFO, "Entered data in element " + locator + " with value " + value);
	}  
	
	public void moveToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		
		log.debug("Moved to element: " + element.toString());
		test.log(LogStatus.INFO, "Moved to element: " + element.toString());
	}
	
	public void waitForElementToBeVisible(String locator, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void waitForElementToBeClickable(String locator, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}

	public boolean isElementPresent(String locator) {
		try {
			waitForElementToBeVisible(locator, 60);
			driver.findElement(By.xpath(locator));
			log.debug("Found element: " + locator + " on the page.");
			test.log(LogStatus.INFO, "Found element: " + locator + " on the page.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
