package com.apitesting.TestAPI;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

import  com.jayway.restassured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;



public class POSTAPITestCases {

	
	@Test
	public void TestCase01_POSTAPIRequest()
	{
		RequestSpecification request=RestAssured.given();
		request.header("Content-Type","application/json");
		JSONObject json=new JSONObject();
		json.put("id", "10");
		json.put("title", "Hello Mumbai");
		json.put("author", "StaffWriter");
				
		request.body(json.toJSONString());
		
		Response res=request.post("http://localhost:3000/posts");
		int a=res.getStatusCode();
		
		Assert.assertEquals(a, 201);
		      
	}
	@Test
	public void TestCase02_POSTAPI()
	{
		   given().body ("{\"id\":\14\","+"\"title\":\"Hello Mumbai\","
				    +"\"author\":\"StaffWriter\"}")
				    .when ()
				    .contentType (ContentType.JSON)
				    .post ("http://localhost:3000/posts").then().log().all();
				      	}
	
	
	@Test
	public void TestCase03_POSTAPIRequestvalidate()
	{
	Response res=	given().get("http://localhost:3000/posts/10");
	
	String s=String.valueOf(res.then().statusCode(200).extract().path("id"));
	
	System.out.println(s);	
		
	}
@Test
	public void TestCase04_PutAPI()
	{
		RequestSpecification request=RestAssured.given();
		request.header("Content-Type","application/json");
		JSONObject json=new JSONObject();
		json.put("id", "10");
		json.put("title", "Hello Mumbai");
		json.put("author", "StaffWriter");
				
		request.body(json.toJSONString());
		
		Response res=request.put("http://localhost:3000/posts/13");
		int a=res.getStatusCode();
		
		Assert.assertEquals(a, 200);
	
	}
	
	@Test
	public void TestCase_05DeleteAPI(){
		Response res= given().when().delete("http://localhost:3000/posts/1");
		
	Assert.assertEquals(res.statusCode(), 200);
		
	}
	
}
