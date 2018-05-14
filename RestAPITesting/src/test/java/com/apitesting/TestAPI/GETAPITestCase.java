package com.apitesting.TestAPI;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import  com.jayway.restassured.*;

import URL.URLandHeader;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GETAPITestCase extends URLandHeader {
	
	public static String apiBaseUrl() throws IOException
	{
		return getobject("BaseURL");
	}
@BeforeClass
	
	public  void init() throws IOException
	{
	loaddata();	
		}
	
//To Check the GET API URL status code
	@Test
	public void Test_01_CheckAPIGETURLStatus() throws IOException
	{
		
		    try{

				Response res=given().param(("param1value"), getobject("param1input")).param(getobject("param1value"), getobject("param1input")).
				param(	getobject("param3value"), getobject("param3input")).
				get(apiBaseUrl()+getobject("URL"));
			String s=getobject("Statuscode200");
			int a=Integer.parseInt(s);	
	        if(res.getStatusCode() == a){
	        	 Assert.assertEquals(res.getStatusCode(), a);	
		        }else{
		            throw new IOException("Status Code not equal to 200");
		        }       
		    }
		    catch(IllegalStateException e){
		        
		        System.out.println(e.getMessage());
		    }        
		}
		
		
		
     
	
	
	
//@Test
	public void Test_02_CheckAPISchemavalidation() throws IOException
	{
		{try{
			
			given().header("Content-Type",getobject("Content-Type")).
			
			get(getobject("URL")).then().
		assertThat()
		      .body(matchesJsonSchemaInClasspath(getobject("jsonfileschema")));
		}catch(IllegalStateException e){
	        System.out.println("Schema Validation Failed");
	        System.out.println(e.getMessage());
	    }  }
		
	}
	
	//@Test
	public void Test_03CheckGETAPIResponseDataForSingle() throws IOException
	{
		given().
		get(getobject("URL1")).
		then().
		root(getobject("ROOT")).
		body("last_name",is(getobject("LASTNAME"))).
		body("id",is(getobject("ID"))).
		body("first_name", is(getobject("FIRSTNAME"))).
		body("avatar", is(getobject("AVATOR")));
			
	}


	//@Test
	public void Test_04GETAPIExtractResponseForSingle()
	{
		String href=when().get("https://reqres.in/api/users/2").
				then().contentType(ContentType.JSON).body("data.id", equalTo(2)).
				extract().path("data.avatar");
		System.out.println(href);
		
		when().get(href).then().statusCode(200).log().all();
	}
	
	//@Test
	public void Test_05GETAPIparameter()
	{
Response res=	given().param("q", "London").
		param("appid", "628e4033e8607b565793b58d12119bb5").
	when().get("http://api.openweathermap.org/data/2.5/weather");

	Assert.assertEquals(res.getStatusCode(), 200);

	}
	
	
//@Test
	public void Test_06GETAPIparameter1() throws IOException
	{
	given().param("appid", "628e4033e8607b565793b58d12119bb5").param("q", "London").
	when().get("http://api.openweathermap.org/data/2.5/weather");
	

	}
	
	//@Test
	public void Test_07GETAPIparameter2() throws IOException
	{
		
	Response res1=given().
		param(getobject("param1value"), getobject("param1input")).
		param(getobject("param2value"), getobject("param2input")).
		param(getobject("param3value"), getobject("param3input")).
	when().get(getobject("urlnew"));
	
	
	
	String s= getobject("Statuscode200");
	int a=Integer.parseInt(s);
	Assert.assertEquals(res1.statusCode(),a);
	System.out.println(res1.asString());

	
	
	}
	
	
//@Test
	public void Test_08CheckGETAPIAssertResponseDataForMultiple() throws IOException
	{
		Response res=given().
		get("https://reqres.in/api/users/");
		
		
		//System.out.println(res.path("data[0].last_name"));
		
		Assert.assertEquals(res.path("data[0].last_name"), getobject("LASTNAME"));
		
		
	}
	
	//@Test
		public void Test_09CheckGETAPIExtractResponseDataForMultiple()
		{
			Response res=given().get("https://reqres.in/api/users/");
			
			
			String restp= res.then().contentType(ContentType.JSON).extract().path("data[0].avatar");
			System.out.println(restp);
			
			
		when().get(restp).then().statusCode(200).log().all();
		
		
						
		}
	
//@Test
	
	public void Test_10()
	{
	
		Response res=given().param("q", "London").
				param("appid", "628e4033e8607b565793b58d12119bb5").
				when().get("http://api.openweathermap.org/data/2.5/weather");
		
			
	
	String lon=String.valueOf(res.then().contentType(ContentType.JSON).extract().path("coord.lon"));
		
	String lat=String.valueOf(res.then().contentType(ContentType.JSON).extract().path("coord.lat"));
	
	given().parameter("lat", lat).parameter("lon", lon).parameter("appid", "628e4033e8607b565793b58d12119bb5").
	get("http://api.openweathermap.org/data/2.5/weather").then().statusCode(200).log().all();
	
	
	}
	

//@Test

public void Test_11GETApITime()
{
Response res=given().when().get("http://api.openweathermap.org/data/2.5/weather");

long longg=res.then().extract().time();
System.out.println(longg);




}


	


}



