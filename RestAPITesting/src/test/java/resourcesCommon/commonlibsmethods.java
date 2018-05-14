package resourcesCommon;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import URL.URLandHeader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class commonlibsmethods extends URLandHeader {

//Generate Unique email id :
	@Test
	protected String getuniqueemail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString()+"@mailinator.com";
        return saltStr;

    }
	@Test
	public void loaduniqueemail()
	{
		System.out.println(getuniqueemail());
	}
	//Generate Unique email id :
		@Test
		protected String getuniquenumber() {
	        String SALTCHARS = "8923498982492348839482394023939023904923";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 10) { // length of the random string.
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;

	    }
		@Test
		public void loaduniquenumber()
		{
			System.out.println(getuniquenumber());
		}
		
	
	@Test
	public void postreq12()
	{
		RequestSpecification request=RestAssured.given();
		request.header("Content-Type","application/json");
		JSONObject json=new JSONObject();
		json.put("id", 123);
		json.put("title", getuniquenumber());
		json.put("author", getuniqueemail());
				
		request.body(json.toJSONString());
		
		Response res=request.post("http://localhost:3000/posts");
		int a=res.getStatusCode();
		
		Assert.assertEquals(a, 201);
	}

	
	
	
	
	//@Test
	public void test_03responsevalidate() throws IOException
	{
		Response respon=
				when().
		get("https://reqres.in/api/users/2").		
		then().extract().response();
		
		String S2=getobject("ID");
		int f = Integer.parseInt(S2);
		
		Assert.assertSame(respon.path("data.id"), f);
		Assert.assertEquals(respon.path("data.first_name"), getobject("FIRSTNAME"));
		Assert.assertEquals(respon.path("data.last_name"), getobject("LASTName"));
		Assert.assertEquals(respon.path("data.avatar"), getobject("AVATOR"));
		

		

		
	}

private String get(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	//	@Test
	public void hello() throws IOException, Exception
	{		
		FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Excel/Book1.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		XSSFRow row=sheet.getRow(0);
		XSSFCell cell=null;
		int colNum=-1;
		for (int i=0;i<row.getLastCellNum();i++)
		{
			if(row.getCell(i).getStringCellValue().trim().equals("URL"))
			{
				colNum=i;
			}
		}
	row=sheet.getRow(1);
	 cell=row.getCell(colNum);
	 
	 String url=String.valueOf(cell.getStringCellValue());
	
	 

	int colNum1=-1;
	for (int i=0;i<row.getLastCellNum();i++)
	{
		if(row.getCell(i).getStringCellValue().trim().equals("PARAMETERVALUE1"))
		{
			colNum1=i;
		}
	}
row=sheet.getRow(1);
 cell=row.getCell(colNum1);
 
 String param1valu=String.valueOf(cell.getStringCellValue());
 int param1value=Integer.parseInt(param1valu);
 
 int colNum2=-1;
	for (int i=0;i<row.getLastCellNum();i++)
	{
		if(row.getCell(i).getStringCellValue().trim().equals("PARAMETERINPUT1"))
		{
			colNum2=i;
		}
	}
row=sheet.getRow(1);
cell=row.getCell(colNum2);

String param1input=String.valueOf(cell.getStringCellValue());
	
	
	
	//newr n=new newr();
	
	
	}
	
}
