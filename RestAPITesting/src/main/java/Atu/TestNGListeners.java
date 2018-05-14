package Atu;



	import java.awt.AWTException;
	import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.Test;

/*import org.testng.Assert;
	import org.testng.Reporter;
	import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
	import org.testng.annotations.Listeners;
	import org.testng.annotations.Test;

	import atu.testng.reports.ATUReports;*/
	import atu.testng.reports.listeners.ATUReportsListener;
	import atu.testng.reports.listeners.ConfigurationListener;
	import atu.testng.reports.listeners.MethodListener;
	import atu.testng.reports.logging.LogAs;
	import atu.testng.selenium.reports.CaptureScreen;
	import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

	/*@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
			MethodListener.class })
*/	public class TestNGListeners {
		
		{
			
			System.setProperty("atu.reporter.config", "C://Users//Praveen Ashok//workspace//RestAPITesting//atu.properties");
		}
		  WebDriver driver;
		  //Creating the JavascriptExecutor interface object by Type casting		
	        JavascriptExecutor js = (JavascriptExecutor)driver;	
		    private String baseUrl;
		// @BeforeMethod
		    public void setUp() throws Exception {
		        driver = new FirefoxDriver();
		        baseUrl = "https://www.flipkart.com";
		        driver.manage().deleteAllCookies();
		        driver.get(baseUrl + "/");
		      
		       	
		        		
		        // ATU Reports
		      /*  ATUReports.setWebDriver(driver);
		        ATUReports.indexPageDescription = "FLIPKART PROJECT";*/
		    }

		  
		  
		 //  @Test
		    public void login_flipkart() throws Exception, SQLException {
			   
			 driver.get("https://www.flipkart.com/");
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		     driver.findElement(By.xpath("*[class='_2zrpKA']")).sendKeys("dsf");
		     driver.findElement(By.xpath("*[class='_2zrpKA _3v41xv']")).sendKeys("sdfd");
		     
		     WebElement button =driver.findElement(By.xpath("*[class='_2AkmmA _1LctnI _7UHT_c']"));
		     js.executeScript("arguments[0].click();", button);
		     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		     String element =driver.findElement(By.xpath("[class='_2cyQi_']")).getText();
		     System.out.println(element);
		     		    
		     
		    }

		    // ATU Reports Method
		   // @Test
		    public void testNewLogs() throws AWTException, IOException {
/*
		        ATUReports.add("INfo Step", LogAs.INFO, new CaptureScreen(
		                ScreenshotOf.BROWSER_PAGE));
		        ATUReports.add("Pass Step", LogAs.PASSED, new CaptureScreen(
		                ScreenshotOf.DESKTOP));
		        WebElement element = driver
		                .findElement(By.xpath(".//*[@id='tsf']/div[2]/div[3]/center/input[1]"));
		        ATUReports.add("Warning Step", LogAs.WARNING,
		                new CaptureScreen(element));
		        ATUReports.add("Fail step", LogAs.FAILED, new CaptureScreen(
		                ScreenshotOf.DESKTOP));*/
		    }

		//  @AfterMethod
		    public void tearDown() throws Exception {
		        driver.quit();
		    }
	}
