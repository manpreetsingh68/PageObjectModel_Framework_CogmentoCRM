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
	
	public void quit() {
		driver.quit();
	}
	
	public WebElement findElement(By by) {
		return driver.findElement(by);
		
	}
	
	public void click(WebElement element) {
		waitForElementToBeClickable(element, 60);
		element.click();
		
		log.debug("Clicked on element " + element);
		test.log(LogStatus.INFO, "Clicked on element " + element);
	}
	
	public void sendKeys(WebElement element, String value) {
		element.sendKeys(value);
		
		log.debug("Entered data in element " + element + " with value " + value);
		test.log(LogStatus.INFO, "Entered data in element " + element + " with value " + value);
	}  
	
	public void moveToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		
		log.debug("Moved to element: " + element.toString());
		test.log(LogStatus.INFO, "Moved to element: " + element.toString());
	}
	
	public void waitForElementToBeVisible(WebElement element, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element)); 
	}
	
	public void waitForElementToBeClickable(WebElement element, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public boolean isElementPresent(WebElement element) {
		waitForElementToBeVisible(element, 60);
		
		boolean isElementFound = element.isDisplayed();
		if(isElementFound == true) {
			log.debug("Found element: " + element + " on the page.");
			test.log(LogStatus.INFO, "Found element: " + element + " on the page.");
		}
		return isElementFound;
	}
}
