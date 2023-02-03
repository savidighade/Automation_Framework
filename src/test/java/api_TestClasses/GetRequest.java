package api_TestClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest 
{
	@Test
	public void getrequestresponse() 
	{
		RestAssured.baseURI="https://gorest.co.in/public/v2"; // here we pass the uri
		
		RequestSpecification httprequest = RestAssured.given(); // here we gave the request
		
		Response response = httprequest.request(Method.GET,"/users"); // which http method u have to be passed
		
		String responsebody = response.getBody().asString(); // by this we get response body
		
		System.out.println("Response body is "+responsebody); // printing the response body
		
		int statuscode = response.getStatusCode(); // achieving the status code
		
		System.out.println("Status Code is "+statuscode); // printing the status code
		
		Assert.assertEquals(statuscode, 200); // validate the status code by assertion
		
		String statusline = response.getStatusLine(); // getting the status line by this method
		 
		System.out.println("Status line is "+statusline); // printing the status line
		
	}
	
	
}




