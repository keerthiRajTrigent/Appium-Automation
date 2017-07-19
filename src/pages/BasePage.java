package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import driver.Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage extends Base {

	final AndroidDriver<WebElement> driver;

	public BasePage(AndroidDriver<WebElement> driver) {
		this.driver = driver;
	}

	public LoginPage signIn() throws InterruptedException{
		PageFactory.initElements(new AppiumFieldDecorator(driver), LoginPage.class);
		return new LoginPage(driver);  
	}
	
//	public HomePage raiseTicket() throws InterruptedException{
//		return PageFactory.initElements(driver, HomePage.class);  
//	}

}
