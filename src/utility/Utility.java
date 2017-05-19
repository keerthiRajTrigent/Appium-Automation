package utility;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import driver.Base;

public class Utility extends Base {

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
