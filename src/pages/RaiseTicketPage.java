package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

import driver.Base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utility.TestUtil;
import utility.Utility;

public class RaiseTicketPage extends Base {
	
	final AndroidDriver<WebElement> driver;
	Utility utility;
	WebElement summaryInput;
	WebElement descriptionInput;
	WebElement submitButton;
	WebElement spinner;
	WebElement ImageToZoom;
	WebElement attachmentButton;
	WebElement notiRadioButton;

	public RaiseTicketPage(AndroidDriver<WebElement> driver) {
		this.driver = driver;
		utility = new Utility(driver);
	}
	
	public void SetUp(){
		try {
			summaryInput = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_editText_summaryVal");
			descriptionInput = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_editText_descVal");
			submitButton = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_button_submit");
			spinner = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_spinner_categoryVal");
			ImageToZoom = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_imageView_imageUser");
			attachmentButton = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_btn_attach");
			notiRadioButton = utility.findByID("com.trigent.empconnect:id/fragment_raiseTicket_radioButton_noti");
			
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
		
		if (!TestUtil.isExecuatable("RaiseTicketTest", xls) ) {
//			throw new SkipException("Skipping the Row");
			System.out.println("Skipping the Test");		
			Utility.updateToLog("RaiseTicketPage", "raiseTicket", utility.deviceInfo());
			
		}
		else{
			utility.buttonClick(spinner);
			Utility.veryShortSleep();
			driver.findElementByXPath("//android.widget.TextView[@text='Others' and @index='2']").click();
			//Enter Summary in to EditText field
			utility.editTextInput(summaryInput, summary);
			Utility.veryShortSleep();
			//Enter Description in to Edittext field
			utility.editTextInput(descriptionInput, description);
			Utility.veryShortSleep();

			//Landscape and portrait mode
			driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
			Utility.shortSleep();
			driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
			Utility.shortSleep();
			
			utility.buttonClick(attachmentButton);
			Utility.veryShortSleep();
			
			driver.findElementByXPath("//android.widget.Button[contains(@resource-id,'permission_allow_button')]").click();
			Utility.veryShortSleep();
			utility.buttonClick(attachmentButton);
			driver.findElementByXPath("//android.widget.TextView[@text='DCIM' and @index='0']").click();
			Utility.veryShortSleep();
			driver.findElementByXPath("//android.widget.TextView[@text='Screenshots' and @index='0']").click();
			Utility.veryShortSleep();
			driver.findElementByXPath("//android.widget.TextView[@text='Screenshot_20170330-195818.png' and @index='0']").click();
			
			Utility.veryShortSleep();

//			utility.buttonClick(ImageToZoom);
//			Utility.veryShortSleep();
//			Utility.veryLongSleep();
			
			//Image zoom in function
			
//			WebElement imageChild = driver.findElementByXPath("//android.widget.ImageView[contains(@resource-id,'myimage1')]");
//			int leftX = imageChild.getLocation().getX();
//			int rightX = leftX + imageChild.getSize().getWidth();
//			int upperY = imageChild.getLocation().getY();
//			int lowerY = upperY + imageChild.getSize().getHeight();
//			int middleY = (upperY + lowerY) / 3;
//			int middleX = (leftX + rightX) / 3;
//			System.out.println("middleY : " + middleY + "  middleX : "+middleX);		
//			TouchAction action0 = new TouchAction(driver).press(middleX,middleY).release();
//			action0.perform();
//			driver.zoom(middleX,middleY);
			
//			Utility.veryShortSleep();
			Utility.veryShortSleep();
			
//			driver.findElementByXPath("//android.widget.ImageView[contains(@resource-id,'zoom_imageView_back')]").click();
//			Utility.veryShortSleep();
			
//			Utility.veryShortSleep();
			Utility.takeScreenShot("Login", driver);

			Utility.veryShortSleep();
			utility.buttonClick(notiRadioButton);
			Utility.veryShortSleep();
			//Click on SubmitTicket Button
			utility.buttonClick(submitButton);
			driver.findElementByXPath("//android.widget.Button[contains(@resource-id,'dialog_experience_button_ok')]").click();
			Utility.takeScreenShot("Ticket created Successfully", driver);
			Utility.longSleep();

			
			PageFactory.initElements(new AppiumFieldDecorator(driver), HomePage.class);
		}

		return new HomePage(driver);
	}

}
