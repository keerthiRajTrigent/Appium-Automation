package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


import driver.Base;
import utility.Utility;

public class LoginPage extends Base{

	final WebDriver driver;
	Utility utility;
	WebElement emailInput;
	WebElement passwordInput;
	WebElement signInButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		utility = new Utility(driver);
	}

	
	public void SetUp(){
		try {
			 emailInput = utility.findByID("com.trigent.empconnect:id/activity_login_editText_employeeID");
			 passwordInput = utility.findByID("com.trigent.empconnect:id/activity_login_editText_pwd");
			 signInButton = utility.findByID("com.trigent.empconnect:id/activity_login_button_login");
			 
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

	public void enterEmail(String email) {
		emailInput.clear();
		emailInput.sendKeys(email);
	}



	/**
	 * @param password the password
	 */
	public void enterPassword(String password) {
		passwordInput.clear();
		passwordInput.sendKeys(password);
	}

	/**
	 * Click sign in button.
	 */
	public void clickSignInButton(){
		signInButton.click();
	}


	public HomePage login(String email, String password) throws InterruptedException{
		SetUp();
		enterEmail(email);
		Utility.veryShortSleep();
		enterPassword(password);
		Utility.veryShortSleep();
		driver.navigate().back();
		Utility.veryShortSleep();
		clickSignInButton();
		Utility.shortSleep();
		return PageFactory.initElements(driver, HomePage.class);
	}

}
