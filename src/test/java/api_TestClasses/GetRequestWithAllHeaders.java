package api_TestClasses;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithAllHeaders 
{

@Test
	
	public void allHeaders() 
	{
		
		RestAssured.baseURI = "https://gorest.co.in/public/v2";
		
		RequestSpecification httprequest = RestAssured.given();
		
		Response response = httprequest.request(Method.GET,"/users");
		
		String responsebody = response.getBody().asString();
		
		System.out.println("Response Body is "+responsebody);
		
		Headers allheader = response.headers();
		
		for(Header headers :allheader) // iteration of all headers
		{
			System.out.println(headers.getName()+"         "+headers.getValue());
		}
		
	}
}
