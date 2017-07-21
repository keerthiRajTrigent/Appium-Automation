package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


import driver.Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.Utility;

public class LoginPage extends Base{

	final AndroidDriver<WebElement> driver;
	Utility utility;
	WebElement emailInput;
	WebElement passwordInput;
	WebElement signInButton;
	WebElement saveToggl;

	public LoginPage(AndroidDriver<WebElement> driver) {
		this.driver = driver;
		utility = new Utility(driver);
	}

	
	public void SetUp(){
		try {
			 emailInput = utility.findByID("com.trigent.empconnect:id/activity_login_editText_employeeID");
			 passwordInput = utility.findByID("com.trigent.empconnect:id/activity_login_editText_pwd");
			 signInButton = utility.findByID("com.trigent.empconnect:id/activity_login_button_login");
			 saveToggl = utility.findByID("com.trigent.empconnect:id/activity_login_checkBox");
			 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
//	@FindBy(how = How.ID, using = "com.trigent.empconnect:id/activity_login_editText_employeeID")
//	private WebElement emailInput1;

//	@FindBy(how = How.ID, using = "com.trigent.empconnect:id/activity_login_editText_pwd")
//	private WebElement passwordInput;

//	@FindBy(how = How.ID, using = "com.trigent.empconnect:id/activity_login_button_login")
//	private WebElement signInButton;

//	public void enterEmail(String email) {
//		emailInput.clear();
//		emailInput.sendKeys(email);
//	}



	/**
	 * @param password the password
	 */
//	public void enterPassword(String password) {
//		passwordInput.clear();
//		passwordInput.sendKeys(password);
//	}

	/**
	 * Click sign in button.
	 */
//	public void clickSignInButton(){
//		signInButton.click();
//	}


	public HomePage login(String email, String password) throws InterruptedException{
		SetUp();

		Utility.takeScreenShot("Login", driver);
		utility.editTextInput(emailInput, email);
		Utility.veryShortSleep();
		utility.buttonClick(saveToggl);
//		enterPassword(password);
		utility.editTextInput(passwordInput, password);
		Utility.veryShortSleep();
		driver.hideKeyboard();
//		Utility.veryShortSleep();
//		Utility.veryShortSleep();
//		clickSignInButton();
		utility.buttonClick(signInButton);
		Utility.shortSleep();
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), HomePage.class);
		return new HomePage(driver);
	}

}
