package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import driver.Base;
import utility.Utility;

public class RaiseTicketPage extends Base {
	
	final WebDriver driver;
	Utility utility;
	WebElement summaryInput;
	WebElement descriptionInput;
	WebElement submitButton;

	public RaiseTicketPage(WebDriver driver) {
		this.driver = driver;
		utility = new Utility(driver);
	}
	
	public void SetUp(){
		try {
			summaryInput = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_editText_summaryVal");
			descriptionInput = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_editText_descVal");
			submitButton = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_button_submit");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
