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

public class HomePage extends Base {
	
	final AndroidDriver<WebElement> driver;
	Utility utility;
	WebElement riaseTicketButton;

	public HomePage(AndroidDriver<WebElement> driver) {
		this.driver = driver;
		utility = new Utility(driver);
	}
	
	public void SetUp(){
		try {
			riaseTicketButton = utility.findByID("com.trigent.empconnect:id/fragment_home_button_raiseTicket");
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
	
	
	
	public RaiseTicketPage goToRaiseTicket() throws InterruptedException{
		SetUp();
		clickRaiseTicket();
		Utility.shortSleep();
		
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), RaiseTicketPage.class);
		
		return new RaiseTicketPage(driver);
	}

}
