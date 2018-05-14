package resourcesCommon;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import  com.jayway.restassured.*;

import URL.URLandHeader;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;


import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class trytest extends URLandHeader {
	@Test
	public void testss() throws IOException 
	{
		
		try{
			Response res=(Response) given().
			get("https://reqres.in/api/users/").getBody(); 
			String bodyAsString = res.asString();
			System.out.println(bodyAsString);
			Assert.assertEquals(bodyAsString.contains(getobject("ID")), true);
			Assert.assertEquals(bodyAsString.contains(getobject("FIRSTNAME")), true);
			Assert.assertEquals(bodyAsString.contains(getobject("LASTNAME")), true);
			Assert.assertEquals(bodyAsString.contains(getobject("AVATOR")), true);
			
			}
			
			catch(IllegalStateException e){
		        System.out.println("Response Assert Failed");
		        System.out.println(e.getMessage());
				}  


	}
	
	
	
 

}



