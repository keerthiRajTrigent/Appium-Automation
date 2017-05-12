package driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

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

	protected AppiumDriver<WebElement> driver;

	@BeforeClass
	public void setUp() throws IOException, InterruptedException {
		// Created object of DesiredCapabilities class.
		DesiredCapabilities capabilities = new DesiredCapabilities();

		// Set android deviceName desired capability. Set your device name.
		capabilities.setCapability("deviceName", CONFIG.getProperty("deviceName")); 
		capabilities.setCapability(CapabilityType.BROWSER_NAME, CONFIG.getProperty("OSType")); 
		capabilities.setCapability(CapabilityType.VERSION, CONFIG.getProperty("OSVersion")); 
		capabilities.setCapability("platformName", CONFIG.getProperty("OSType")); 
		capabilities.setCapability("appPackage", CONFIG.getProperty("appPackage"));
		capabilities.setCapability("appActivity", CONFIG.getProperty("appActivity"));

		driver = new AndroidDriver<WebElement>(new URL(CONFIG.getProperty("HUB_URL")), capabilities);
		//	  driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public AppiumDriver<WebElement> getDriver() {    
		return driver;
	}


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
		}

		if(DATA == null){
			DATA = new Properties();
			FileInputStream da = new FileInputStream(System.getProperty("user.dir") + "\\data.properties");
			DATA.load(da);
		}			
	}    

}
