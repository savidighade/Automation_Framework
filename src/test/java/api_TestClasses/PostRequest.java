package api_TestClasses;

import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest extends util_Classes.TestDataProvider
{

@Test(dataProvider = "data")
	
	public void postrequest( Map<String, String> str) 
	{
		RestAssured.baseURI="https://gorest.co.in/public/v2";  //for passing the uri
		
		RequestSpecification httprequest = RestAssured.given();  //giving the http request
		
		JSONObject requestParam = new JSONObject(); //creating the object for put/post method
		
		requestParam.put("name",str.get("name")); 
		
		requestParam.put("email",str.get("email"));
		
		requestParam.put("gender",str.get("gender"));
		
		requestParam.put("status",str.get("status"));
		
		//httprequest.header("Content-Type","application/json");
		
		httprequest.body(requestParam.toJSONString()); // here we pass the json body.
		
		String token = "771933d3a3d353d4de3e0289691b5e79afd7125f63fa2108706a35971f0ddf26"; // here pass the token
		
		String authtoken = "Bearer "+token;
		
		httprequest.header("Authorization",authtoken); // here pass the authorization in the header
		
		httprequest.header("Content-Type","application/json"); // here pass the header
		
		Response response = httprequest.request(Method.POST,"/users"); // here pass the which request we want to execute
		
		String responsebody = response.getBody().asString(); // response body takes in string.
		
		System.out.println("Response Body is "+responsebody);  
		
		int statuscode = response.getStatusCode(); // here get the response code
		
		System.out.println("Status Code is "+statuscode);
		
		Assert.assertEquals(statuscode, 201); //validating the status code
		
		
		
		
	}

}

