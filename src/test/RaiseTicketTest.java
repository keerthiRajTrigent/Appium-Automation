package test;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import driver.Base;
import pages.BasePage;
import pages.HomePage;
import utility.Utility;

public class RaiseTicketTest extends Base {

	
	static String summary = null;
	static String description = null;
	
	
	@BeforeClass
	public void setUp() throws Exception {	
		System.out.println("In Before Class of LoginTest");
		summary = CONFIG.getProperty("summary");
		description = CONFIG.getProperty("description");
	}
	
	@Test(priority = 1, enabled = true)
	public void riaseTicket() throws InterruptedException {
	try
	{
		System.out.println("Raising ticket");		
		BasePage basePage = PageFactory.initElements(getDriver(), BasePage.class);
		HomePage homePage = basePage.raiseTicket();
		Utility.veryShortSleep();
		homePage.raiseTicket(summary,description);
		Utility.veryShortSleep();
		System.out.println("Ticket Raised successfully");		
		}catch (Exception e) {
			e.printStackTrace();
			 Assert.fail();
		}
	}
}
