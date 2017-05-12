package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import driver.Base;
import io.appium.java_client.AppiumDriver;

public class BasePage extends Base {
	
	final AppiumDriver<WebElement> driver;
	
	public BasePage(AppiumDriver<WebElement> driver) {
		this.driver = driver;
		}
	
	public LoginPage signIn() throws InterruptedException{
		return PageFactory.initElements(driver, LoginPage.class);  
  }

}
