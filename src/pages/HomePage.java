package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import driver.Base;
import io.appium.java_client.android.AndroidDriver;
import utility.Utility;

public class HomePage extends Base {
	
	final WebDriver driver;
	Utility utility;
	WebElement riaseTicketButton;
	WebElement summaryInput;
	WebElement descriptionInput;
	WebElement submitButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		utility = new Utility(driver);
	}
	
	public void SetUp(){
		try {
			riaseTicketButton = utility.findByID("com.trigent.empconnect:id/fragment_home_button_raiseTicket");
			summaryInput = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_editText_summaryVal");
			descriptionInput = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_editText_descVal");
			submitButton = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_button_submit");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
//	@FindBy(how = How.ID, using = "com.trigent.empconnect:id/fragment_home_button_raiseTicket")
//	private WebElement riaseTicketButton;

//	@FindBy(how = How.ID, using = "com.trigent.empconnect:id/fragment_raiseTicket_editText_summaryVal")
//	private WebElement summaryInput;

//	@FindBy(how = How.ID, using = "com.trigent.empconnect:id/fragment_raiseTicket_editText_descVal")
//	private WebElement descriptionInput;
	
//	@FindBy(how = How.ID, using = "com.trigent.empconnect:id/fragment_raiseTicket_button_submit")
//	private WebElement submitButton;
	
	public void clickRaiseTicket() {
		riaseTicketButton.click();
	}
	
	public void enterSummary(String summary) {
		summaryInput.clear();
		summaryInput.sendKeys(summary);
	}

	public void enterDescription(String desciption) {
		descriptionInput.clear();
		descriptionInput.sendKeys(desciption);
	}

	public void submitTicket() {
		submitButton.click();
	}
	
	public HomePage raiseTicket(String summary, String description) throws InterruptedException{
		SetUp();
		clickRaiseTicket();
		Utility.veryShortSleep();
		enterSummary(summary);
		Utility.veryShortSleep();
		enterDescription(description);
		Utility.veryShortSleep();
		driver.navigate().back();
		Utility.veryShortSleep();
		submitTicket();
		Utility.shortSleep();
		return PageFactory.initElements(driver, HomePage.class);
	}

}
