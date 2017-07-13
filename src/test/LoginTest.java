package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import driver.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import pages.RaiseTicketPage;
import utility.TestUtil;
import utility.Utility;

public class LoginTest implements Runnable{

	static String loginEmail = null;
	static String loginPassword = null;
	static String summary = null;
	static String description = null;
	Base base;
	protected AndroidDriver<WebElement> driver;

	String port;
	String udid;
	String version;

	public LoginTest(String portNumber, String udid, String platformVersion_) {
		this.port = portNumber;
		this.udid = udid;
		version = platformVersion_;
	}

	@BeforeClass
	public void setUp(){	
		base = new Base();

		System.out.println("In Before Class of LoginTest");
		loginEmail = "demo@demo.com";
		loginPassword = "password";
		summary = "summary";
		description = "This is a short description for testing purpose";	
	}


	//@Test(priority = 1, enabled = true)
	public void logintoApp() throws InterruptedException {
		try
		{
			if (!TestUtil.isExecuatable("LoginTest", Base.xls) ) {
				System.out.println("Skipping the Test");		
			}

			Utility.updateToLog("Logintest", "logintoApp", "login to Application Starts");
			Utility.updateToLog("Logintest", "logintoApp", "login Starts");
			System.out.println("login to Application Starts");		
			PageFactory.initElements( new AppiumFieldDecorator((AppiumDriver<WebElement>)driver), BasePage.class);
			BasePage basePage = new BasePage(driver);
			LoginPage loginPage = basePage.signIn();
			Utility.veryShortSleep();
			HomePage homePage = loginPage.login(loginEmail, loginPassword);
			Utility.veryShortSleep();
			System.out.println("login to Application successfully");

			System.out.println("Raising ticket");		
			Utility.veryShortSleep();
			RaiseTicketPage raisePage = homePage.goToRaiseTicket();
			Utility.veryShortSleep();
			raisePage.raiseTicket(summary, description);
			System.out.println("Ticket Raised successfully");	
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	private void openAppAndPerformSomeActions() {


		base = new Base();

		System.out.println("In Before Class of LoginTest");
		loginEmail = "demo@demo.com";
		loginPassword = "password";
		summary = "summary";
		description = "This is a short description for testing purpose";	

		DesiredCapabilities capabilities = new DesiredCapabilities();


		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", udid);
		capabilities.setCapability("udid", udid);
		capabilities.setCapability("platformVersion", version);
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.trigent.empconnect");
		capabilities.setCapability("appActivity", "com.trigent.empconnect.Activities.SplashScreenActivity");

		try {
			driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:" + port + "/wd/hub"), capabilities);
			Thread.sleep(10000);
			logintoApp();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		openAppAndPerformSomeActions();
	}

	public static void main(String args[]) {
		Runnable r1 = new LoginTest("5000", "64cd1224","5.0.2"); //device id of black tablet
		Runnable r2 = new LoginTest("4723", "e9b94aea1ca24f6a","6.0.1"); //device id of white tablet
		new Thread(r1).start();
		new Thread(r2).start();
	}

}
