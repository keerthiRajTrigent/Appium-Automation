package driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import pages.HomePage;
import utility.TestUtil;
import utility.Utility;
import utility.Xls_Reader;

public class Base {


	public static Properties CONFIG = null;

	/** The element. */
	public static Properties ELEMENT = null;

	/** The data. */
	public static Properties DATA = null;

	/** The econfig. */
	public static Properties ECONFIG = null;    

	/** The applogger. */
	public static Logger applogger = null;

	/** The Operationsmenu. */
	public static Actions Operationsmenu;
	
	public static Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir") + "\\TestData\\Data.xlsx");

	protected AndroidDriver<WebElement> driver;

	@BeforeClass
	 @Parameters("browser")
	public void setUp(String Browser) throws IOException, InterruptedException {
		// Created object of DesiredCapabilities class.
		System.out.println("In Before Class for Browser:: " + Browser);
		System.out.println("Device Name from XLS:: " + xls.getCellData("Device Data", "DeviceName", 2));
		Base.init();
		DesiredCapabilities capabilities = new DesiredCapabilities();

		// Set android deviceName desired capability. Set your device name.
//		capabilities.setCapability("deviceName", CONFIG.getProperty("deviceName")); 
//		capabilities.setCapability(CapabilityType.BROWSER_NAME, CONFIG.getProperty("OSType")); 
//		capabilities.setCapability(CapabilityType.VERSION, CONFIG.getProperty("OSVersion")); 
//		capabilities.setCapability("platformName", CONFIG.getProperty("OSType")); 
		capabilities.setCapability("deviceName", xls.getCellData("Device Data", "DeviceName", 2));
		capabilities.setCapability(CapabilityType.BROWSER_NAME, xls.getCellData("Device Data", "OSType", 2));
		capabilities.setCapability(CapabilityType.VERSION, xls.getCellData("Device Data", "OSVersion", 2));
		capabilities.setCapability("platformName", xls.getCellData("Device Data", "OSType", 2));
		capabilities.setCapability("appPackage", CONFIG.getProperty("appPackage"));
		capabilities.setCapability("appActivity", CONFIG.getProperty("appActivity"));

		driver = new AndroidDriver<WebElement>(new URL(CONFIG.getProperty("HUB_URL")), capabilities);
//		threadDriver.set(new RemoteWebDriver(new URL(CONFIG.getProperty("HUB_URL")), capabilities));;

		String timout = CONFIG.getProperty("IMPLICIT_WAIT");
		getDriver().manage().timeouts().implicitlyWait(Long.parseLong(timout.trim()), TimeUnit.SECONDS);
	}

	public AndroidDriver<WebElement> getDriver() {    
		return driver;
	}
	
	
//	public static AndroidDriver<WebElement> setupAndroidDriver(){
//		
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//
//		// Set android deviceName desired capability. Set your device name.
//		capabilities.setCapability("deviceName", CONFIG.getProperty("deviceName")); 
//		capabilities.setCapability(CapabilityType.BROWSER_NAME, CONFIG.getProperty("OSType")); 
//		capabilities.setCapability(CapabilityType.VERSION, CONFIG.getProperty("OSVersion")); 
//		capabilities.setCapability("platformName", CONFIG.getProperty("OSType")); 
//		capabilities.setCapability("appPackage", CONFIG.getProperty("appPackage"));
//		capabilities.setCapability("appActivity", CONFIG.getProperty("appActivity"));
//		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(capabilities);
//		return driver;
//		
//	}
	



	/**
	 * Quit driver.
	 */
	@AfterClass
	public void quitDriver(){
		getDriver().quit();

	}

	public static void init() throws IOException{	
		System.out.println("In Driver Init");
		if(CONFIG == null){
			CONFIG = new Properties();
			FileInputStream fn = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
			CONFIG.load(fn);
		}
		if(ELEMENT == null){
			ELEMENT = new Properties();
			FileInputStream en = new FileInputStream(System.getProperty("user.dir") + "\\elementRep.properties");
			ELEMENT.load(en);
		}

		if(applogger == null){
			System.out.println("Intializing the logger");
			applogger = Logger.getLogger("devpinoyLogger");
			System.out.println(applogger);
		}

		if(DATA == null){
			DATA = new Properties();
			FileInputStream da = new FileInputStream(System.getProperty("user.dir") + "\\data.properties");
			DATA.load(da);
		}			
	}    
	
//	public WebElement findByID(String how, String using) throws InterruptedException{
//		WebElement riaseTdd = getDriver().findElement(By.id("com.trigent.empconnect:id/activity_login_editText_employeeID"));
//
//		return riaseTdd;
//	}

}
