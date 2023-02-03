package api_TestClasses;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithValidatingResponseBody 
{
@Test
	
	public void validatingResponceBody()
	{
		
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
		
		RequestSpecification httprequest = RestAssured.given();
		
		Response response = httprequest.request(Method.GET,"/users");
		
		String responsebody = response.getBody().asString();
		
		System.out.println("Response Body is "+responsebody);
			
	}

}


