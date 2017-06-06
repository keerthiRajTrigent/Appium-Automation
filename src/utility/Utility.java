package utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.SkipException;

import driver.Base;
import io.appium.java_client.android.AndroidDriver;

public class Utility extends Base {

	final WebDriver webDriver;

	public Utility(WebDriver webDriver) {
		// TODO Auto-generated constructor stub
		this.webDriver = webDriver;
	}

	public static void veryShortSleep() throws InterruptedException{
		Thread.sleep(Integer.parseInt(CONFIG.getProperty("VeryShort")));
	}

	public static void shortSleep() throws InterruptedException{
		Thread.sleep(Integer.parseInt(CONFIG.getProperty("Short")));
	}

	public static void mediumSleep() throws InterruptedException{
		Thread.sleep(Integer.parseInt(CONFIG.getProperty("Medium")));
	}

	public static void longSleep() throws InterruptedException{
		Thread.sleep(Integer.parseInt(CONFIG.getProperty("Long")));
	}


	public static void veryLongSleep() throws InterruptedException{
		Thread.sleep(Integer.parseInt(CONFIG.getProperty("VeryLong")));
	}

	/*Method to initialize element by ID*/
	public WebElement findByID(String elementID) throws InterruptedException{
		WebElement webElement = webDriver.findElement(By.id(elementID));
		return webElement;
	}

	/*Method to initialize element by Name*/
	public WebElement findByName(String elementName) throws InterruptedException{
		WebElement webElement = webDriver.findElement(By.name(elementName));
		return webElement;
	}

	/*Method to initialize element by XPath using class and text attribute*/
	public WebElement findByXPathClassText(String widgetname, String elementText) throws InterruptedException{
		/* Any android.widget.RadioButton with text value ' like 'Submit'' */
		WebElement webElement = webDriver.findElement(By.xpath("//android.widget."+widgetname+"[@text='"+elementText+"']")); 
		return webElement;
	}

	/*Method to initialize element by XPath using class and resource-id*/
	public WebElement findByXPathID(String widgetname, String elementID) throws InterruptedException{
		/* Any android.widget.Edittext with resource-id value 'com.automation.test:id/editText1' */
		WebElement webElement = webDriver.findElement(By.xpath("//android.widget."+widgetname+"[@resource-id='"+elementID+"']")); 
		return webElement;
	}

	/*Method to initialize element by XPath using class, text attribute and resource-id*/
	public WebElement findByXPathClassTextID(String widgetname, String elementText, String elementID) throws InterruptedException{
		WebElement webElement = webDriver.findElement(By.xpath("//android.widget."+widgetname+"[@resource-id='"+elementID+"and @text='"+elementText+"'']")); 
		return webElement;
	}

	/*Method to initialize element by XPath using class, text attribute and index*/
	public WebElement findByXPathClassTextIndex(String widgetname, String elementText, int elementIndex) throws InterruptedException{
		WebElement webElement = webDriver.findElement(By.xpath("//android.widget."+widgetname+"[@resource-id='"+elementText+"and @index='"+elementIndex+"'']")); 
		return webElement;
	}
	
	/*Common Functions*/
	
	/*Button - Button click function*/
	public void  buttonClick(WebElement button){
		button.click();
	}
	
	/*EditText - Pass value to edittext*/
	public void editTextInput(WebElement editText, String value){
		editText.clear();
		editText.sendKeys(value);
	}
	
	/*Scroll - Scroll down to the end of the page */
	public void scroll(){
		Dimension dimensions = driver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.5;
		int scrollStart = screenHeightStart.intValue();
		System.out.println("s="+scrollStart);
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int scrollEnd = screenHeightEnd.intValue();
		driver.swipe(0,scrollStart,0,scrollEnd,2000);
	}
	
	public String deviceInfo(){
		String deviceInfo = "";
		deviceInfo = xls.getCellData("Device Data", "DeviceName", 2);
		deviceInfo = deviceInfo + " , " +xls.getCellData("Device Data", "OSType", 2);
		deviceInfo = deviceInfo + " , " +xls.getCellData("Device Data", "OSVersion", 2);
		deviceInfo = deviceInfo + " , " +xls.getCellData("Device Data", "OSType", 2);
		deviceInfo = deviceInfo + " , " +CONFIG.getProperty("appPackage");
		return deviceInfo;
	}
	


//	public static void takeScreenShot(String fileName, WebDriver driver) {
//		try {
//			if (CONFIG.getProperty("Enable_Screenshot").equalsIgnoreCase("Yes")) {
//				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//				FileUtils.copyFile(scrFile, new File(CONFIG.getProperty("ScreenshotLocation") + fileName + ".png"));
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}         
//
//	}
	
	 public static void takeScreenShot(String fileName, AndroidDriver<WebElement> driver) {
		  // Set folder name to store screenshots.
		 String destDir = "screenshots";
		  // Capture screenshot.
		  File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  // Set date format to set It as screenshot file name.
		  DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  // Create folder under project with name "screenshots" provided to destDir.
		  new File(destDir).mkdirs();
		  // Set file name using current date time.
		  String destFile = dateFormat.format(new Date()) + ".png";

		  try {
		   // Copy paste file at destination folder location
		   FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 }

	public static void updateToLog(String className, String MethodName, String message){
		applogger.debug("ClassName==>  " + className + "   MethodName ==> " + MethodName + "   Message==> " + message );
	}

	public static void sendmail() throws Exception {
		Utility.veryLongSleep();
		System.out.println("Finished very long sleep");
		try {			
			String frommail = CONFIG.getProperty("FromMail");
			String tomail = CONFIG.getProperty("ToMail");
			String Titleemail = CONFIG.getProperty("TitleOfeMail");
			String Message = CONFIG.getProperty("eMailMessage");	

			List<String> items = Arrays.asList(tomail.split("\\s*,\\s*"));
			Iterator<String> iterator = items.iterator();
			while (iterator.hasNext()) {
				System.out.println("Coming to send email section");
				/*
				 * Need to work on this
				 */
				//SendResults sr = new SendResults(frommail,iterator.next(), Titleemail, Message);
				//sr.sendTestNGResult();
			}
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}

}
