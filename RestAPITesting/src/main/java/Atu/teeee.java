package Atu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
/*import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;*/

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
//import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class teeee {
	{
		
		System.setProperty("atu.reporter.config", "C://Users//Praveen Ashok//workspace//RestAPITesting//atu.properties");
	}
	static WebDriver driver;
	
	//@BeforeTest
	public void setup()
	{
		/*System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
		DesiredCapabilities d = new DesiredCapabilities();
		d.setCapability("marionette", true);  // to disable marionette, by default true
		 driver = new ChromeDriver(d);*/
	  
		driver=new FirefoxDriver();
		
		 driver.get("https://www.amazon.in/");
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
		 // ATU Reports
	      /* ATUReports.setWebDriver(driver);
	 ATUReports.indexPageDescription = "Test Project";*/
	        	/*
		   
		/*ATUReports.add("INfo Step", LogAs.INFO, new CaptureScreen(
	                ScreenshotOf.BROWSER_PAGE));
	        ATUReports.add("Pass Step", LogAs.PASSED, new CaptureScreen(
	                ScreenshotOf.DESKTOP));
	        ATUReports.add("Fail step", LogAs.FAILED, new CaptureScreen(
	                ScreenshotOf.DESKTOP));
	       */
	       /* ATUReports.add("Warning Step", LogAs.WARNING,
	                new CaptureScreen(element));
	        ATUReports.add("Fail step", LogAs.FAILED, new CaptureScreen(
	                ScreenshotOf.DESKTOP));*/
	      /*  WebElement element = driver
	                .findElement(By.xpath("//input[@placeholder='First Name']"));
	        System.out.println(element);*/
		  /*  
			*/
	}

	//@Test
	public void openBrowser(){
	   
	}
	//@Test
	public void verify_Menus(){
		JavascriptExecutor	js = (JavascriptExecutor)driver;
	    js.executeScript("document.getElementById('twotabsearchtextbox').value='moto';");
		js.executeScript("document.getElementsByClassName('nav-input')[0].click();");
		/*
		ATUReports.add("INfo Step", LogAs.INFO, new CaptureScreen(
                ScreenshotOf.BROWSER_PAGE));
        ATUReports.add("Pass Step", LogAs.PASSED, new CaptureScreen(
                ScreenshotOf.DESKTOP));
        ATUReports.add("Fail step", LogAs.FAILED, new CaptureScreen(
                ScreenshotOf.DESKTOP));*/
		
		}
	
	//@AfterClass
	public void quit()
	{
		driver.quit();
	}
	
}
