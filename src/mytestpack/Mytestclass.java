package mytestpack;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.jndi.toolkit.url.Uri;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Mytestclass {


	AppiumDriver driver;

	 @BeforeTest
	 public void setUp() throws MalformedURLException {
	  // Created object of DesiredCapabilities class.
	  DesiredCapabilities capabilities = new DesiredCapabilities();

	  // Set android deviceName desired capability. Set your device name.
//	  capabilities.setCapability("deviceName", "980bd4637d43");
	  capabilities.setCapability("deviceName", "e9b94aea1ca24f6a"); //6.0.1 White Tab
//	  capabilities.setCapability("deviceName", "64cd1224");	//5.0.2 Tab black
//	  capabilities.setCapability("deviceName", "1ae487aa"); //7.1.1
//	  capabilities.setCapability("deviceName", "VGEQAUGMLVNVOVFU");

	  // Set BROWSER_NAME desired capability. It's Android in our case here.
	  capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

	  // Set android VERSION desired capability. Set your mobile device's OS version.
	  capabilities.setCapability(CapabilityType.VERSION, "6.0.1");

	  // Set android platformName desired capability. It's Android in our case here.
	  capabilities.setCapability("platformName", "Android");

	  // Set android appPackage desired capability. It is
	  // com.android.calculator2 for calculator application.
	  // Set your application's appPackage if you are using any other app.
	  capabilities.setCapability("appPackage", "com.trigent.empconnect");

	  // Set android appActivity desired capability. It is
	  // com.android.calculator2.Calculator for calculator application.
	  // Set your application's appPackage if you are using any other app.
	  capabilities.setCapability("appActivity", "com.trigent.empconnect.Activities.SplashScreenActivity");
	  

	  // Created object of RemoteWebDriver will all set capabilities.
	  // Set appium server address and port number in URL string.
	  // It will launch calculator app in android device.
	  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//	  driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 }

	 @Test
	 public void Login() {
	  //Defining EditText and passing values to it
	  driver.findElement(By.id("com.trigent.empconnect:id/activity_login_editText_employeeID")).sendKeys("Appium User");
	  driver.findElement(By.id("com.trigent.empconnect:id/activity_login_editText_pwd")).sendKeys("Appium");
	  
	  //Clicking on the Login button
	  driver.findElement(By.id("com.trigent.empconnect:id/activity_login_button_login")).click();	  

	 }
	 @Test
	 public void RaiseTicket() {
	  
	  //Clicking on the RaiseTicket button
	  driver.findElement(By.id("com.trigent.empconnect:id/fragment_home_button_raiseTicket")).click();	  
	  
	  //Clicking on the Submit button
	  driver.findElement(By.id("com.trigent.empconnect:id/fragment_raiseTicket_button_submit")).click();	  

	 }

	@AfterTest
	 public void End() {
//	  driver.quit();
	 }
	}