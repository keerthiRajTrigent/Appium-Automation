package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import driver.Base;

public class BasePage extends Base {

	final WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage signIn() throws InterruptedException{
		return PageFactory.initElements(driver, LoginPage.class);  
	}
	
	public HomePage raiseTicket() throws InterruptedException{
		return PageFactory.initElements(driver, HomePage.class);  
	}

}
