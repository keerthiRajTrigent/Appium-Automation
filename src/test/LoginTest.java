package test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import driver.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import pages.RaiseTicketPage;
import utility.TestUtil;
import utility.Utility;

public class LoginTest extends Base{
	
	static String loginEmail = null;
	static String loginPassword = null;
	static String summary = null;
	static String description = null;
	
	@BeforeClass
	public void setUp(){	
		System.out.println("In Before Class of LoginTest");
//		loginEmail = "demo@demo.com";
//		loginPassword = "password";
		loginEmail = CONFIG.getProperty("loginEmail");
		loginPassword = CONFIG.getProperty("loginPassword");
		
		summary = CONFIG.getProperty("summary");
		description = CONFIG.getProperty("description");	
	}
	
	
	@Test(priority = 1, enabled = true)
	public void logintoApp() throws InterruptedException {
	try
	{
		if (!TestUtil.isExecuatable("LoginTest", xls) ) {
			System.out.println("Skipping the Test");		
		}
		
		Utility.updateToLog("Logintest", "logintoApp", "login to Application Starts");
		System.out.println("login to Application Starts");		
		PageFactory.initElements( new AppiumFieldDecorator((AppiumDriver<WebElement>)getDriver()), BasePage.class);
		BasePage basePage = new BasePage(getDriver());
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

}
