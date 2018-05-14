package com.apitesting.TestAPI;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import  io.restassured.*;
import io.restassured.RestAssured;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import  com.jayway.restassured.*;

import com.relevantcodes.extentreports.model.Log;

import URL.URLandHeader;
import atu.testng.reports.ATUReports;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class GETTEST extends URLandHeader {
	
	
	static String urlpass;
	


	@Parameters({ "ENVURL" })
	@BeforeClass
	public static void setup(String ENVURL) throws Exception{
		
		System.setProperty("atu.reporter.config", "C://Users//Praveen Ashok//workspace//RestAPITesting//atu.properties");
	
		if(ENVURL.equalsIgnoreCase("QA"))
			{		  
				urlpass =	getobject("QAURL");
			}
	   else if(ENVURL.equalsIgnoreCase("DEV"))
	        {
				urlpass =getobject("DEVURL");
			}
			else if(ENVURL.equalsIgnoreCase("PRODUCTION"))
			   {
				urlpass =	getobject("PRODUCTIONURL");	
				}
			else
			{
			throw new Exception("Envrionment is not correct");
			}


	}
	
	
	
	
	@Test
	public void test01_get_status_code( )throws IOException
	{
		System.out.println(urlpass);
		 // ATU Reports
		
	// ATUReports.indexPageDescription = "Test Project";
	 
	 try{
	    	
			Response res=given().param(("param1value"), getobject("param1input")).
			param(getobject("param1value"), getobject("param1input")).
			param(	getobject("param3value"), getobject("param3input")).
			get(urlpass+"users/");
		    String s=getobject("Statuscode200");
		    int a=Integer.parseInt(s);	
		    ATUReports.add("Get Status code Test_Case Pass", LogAs.PASSED, new CaptureScreen(
		             ScreenshotOf.DESKTOP)); 
		
     if(res.getStatusCode() == a){
     	 Assert.assertEquals(res.getStatusCode(), a);	
	        }else{
	        	 ATUReports.add("Get Status code Test_Case Fail", LogAs.FAILED, new CaptureScreen(
	 	                ScreenshotOf.DESKTOP)); 
	            throw new IOException("Status Code not equal to 200");
	        }       
    
	 }
	    catch(IllegalStateException e){
	        
	        System.out.println(e.getMessage());
	        ATUReports.add("Get Status code Try_block Fail", LogAs.FAILED, new CaptureScreen(
	                ScreenshotOf.DESKTOP)); 
	    }
		
	}
	
	@Test
	public void test_02_get_schema_validation() throws IOException
	{
		{
			try{
			given().header("Content-Type",getobject("Content-Type")).
			get(urlpass+"users/").then().assertThat()
		      .body(matchesJsonSchemaInClasspath(getobject("jsonfileschema")));
			ATUReports.add("Get schema_validation Test_Case Pass", LogAs.PASSED, new CaptureScreen(
		             ScreenshotOf.DESKTOP)); 
		}	
			catch(IllegalStateException e){
	        System.out.println("Schema Validation Failed");
	        System.out.println(e.getMessage());
	        ATUReports.add("Get schema_validation Test_Case Fail", LogAs.FAILED, new CaptureScreen(
		             ScreenshotOf.DESKTOP)); 
				}  			
		}
		
	}
	
	@Test
	public void test_03_response_validate() throws IOException
	{
		try{
		Response respon=when().get(urlpass+"users/2").		
		then().extract().response();
		String S2=getobject("ID");
		int f = Integer.parseInt(S2);
		Assert.assertSame(respon.path("data.id"), f);
		Assert.assertEquals(respon.path("data.first_name"),getobject("FIRSTNAME"));
		Assert.assertEquals(respon.path("data.last_name"), getobject("LASTNAME"));
		Assert.assertEquals(respon.path("data.avatar"), getobject("AVATOR"));	
		ATUReports.add("Get response_validate Test_Case Pass", LogAs.PASSED, new CaptureScreen(
	             ScreenshotOf.DESKTOP)); 
		}
		catch(IllegalStateException e){
	    System.out.println("Response Validation Failled");
	    System.out.println(e.getMessage());
	      ATUReports.add("Get response_validate Test_Case Fail", LogAs.FAILED, new CaptureScreen(
		             ScreenshotOf.DESKTOP)); 
		}  
		
	}
	
	
	@Test
	public void test_04_get_api_response_extract_single() throws IOException
	{
		
		try{			
		String S2=getobject("ID");
		int f = Integer.parseInt(S2);
		String href=when().get(urlpass+"users/"+getobject("ID")).
		then().contentType(ContentType.JSON).body("data.id", equalTo(f)).
		extract().path("data.avatar");
		System.out.println(href);
		Response res =get(href);
		String i=getobject("Statuscode200");
		int a=Integer.parseInt(i);
		Assert.assertEquals(res.getStatusCode(), a);
		
		ATUReports.add("Get response_extract_single Test_Case Pass", LogAs.PASSED, new CaptureScreen(
	             ScreenshotOf.DESKTOP)); 
				}
		catch(IllegalStateException e){
	        System.out.println("Response Extraction Failed");
	        System.out.println(e.getMessage());
	        ATUReports.add("Get response_extract_single Test_Case Fail", LogAs.FAILED, new CaptureScreen(
		             ScreenshotOf.DESKTOP)); 
				}  
			}



@Test
public void test_05_get_api_response_extract_multiple() throws IOException
{
	try{
		
		Response res=(Response) given().
		get(urlpass+"users").getBody(); 
		String bodyAsString = res.asString();
		System.out.println(bodyAsString);
		Assert.assertEquals(bodyAsString.contains(getobject("ID")), true);
	    Assert.assertEquals(bodyAsString.contains(getobject("FIRSTNAME")),true);
		Assert.assertEquals(bodyAsString.contains(getobject("LASTNAME")), true);
		Assert.assertEquals(bodyAsString.contains(getobject("AVATOR")), true);
		ATUReports.add("Get response_extract_multiple Test_Case Pass", LogAs.PASSED, new CaptureScreen(
	             ScreenshotOf.DESKTOP)); 
		
		}
		
		catch(IllegalStateException e){
	        System.out.println("Response Assert Failed");
	        System.out.println(e.getMessage());
	        ATUReports.add("Get response_extract_multiple Test_Case Fail", LogAs.FAILED, new CaptureScreen(
		             ScreenshotOf.DESKTOP)); 
			}  

}
}
