package utility;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driver.Base;

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


	public static void takeScreenShot(String fileName, WebDriver driver) {
		try {
			if (CONFIG.getProperty("Enable_Screenshot").equalsIgnoreCase("Yes")) {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(CONFIG.getProperty("ScreenshotLocation") + fileName + ".png"));
			}
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
