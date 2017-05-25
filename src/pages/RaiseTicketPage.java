package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import driver.Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.Utility;

public class RaiseTicketPage extends Base {
	
	final AndroidDriver<WebElement> driver;
	Utility utility;
	WebElement summaryInput;
	WebElement descriptionInput;
	WebElement submitButton;

	public RaiseTicketPage(AndroidDriver<WebElement> driver) {
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
	
	
//	public void enterSummary(String summary) {
//		summaryInput.clear();
//		summaryInput.sendKeys(summary);
//	}

//	public void enterDescription(String desciption) {
//		descriptionInput.clear();
//		descriptionInput.sendKeys(desciption);
//	}

//	public void submitTicket() {
//		submitButton.click();
//	}
	
	
	public HomePage raiseTicket(String summary, String description) throws InterruptedException{
		SetUp();
		//Enter Summary in to EditText field
		utility.editTextInput(summaryInput, summary);
//		enterSummary(summary);
		Utility.veryShortSleep();
		//Enter Description in to Edittext field
		utility.editTextInput(descriptionInput, description);
//		enterDescription(description);
		Utility.veryShortSleep();
		driver.hideKeyboard();
		Utility.veryShortSleep();
		//Click on SubmitTicket Button
		utility.buttonClick(submitButton);
//		submitTicket();
		Utility.shortSleep();
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), HomePage.class);
		return new HomePage(driver);
	}

}
