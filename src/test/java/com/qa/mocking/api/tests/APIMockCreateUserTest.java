package com.qa.mocking.api.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class APIMockCreateUserTest extends BaseTest {
	
	@Test
	public void createDummyUserTest()
	{
		APIMocks.createDummyUser();
		String dummyJson = "{\r\n"
				+ "    \"name\": \"naveen\"\r\n"
				+ "}";
		Response response = restClient.post(BASE_URL_LOCALHOST_PORT, "/api/users", dummyJson, null, null, AuthType.NO_AUTH, ContentType.JSON);
	    response.then()
	            .assertThat()
	            .statusCode(201)
	            .statusLine(equalTo("HTTP/1.1 201 user is created"))
	            .body("id", equalTo(1));
	
	}
	
	@Test
	public void createDummyUserTestWithJsonFile()
	{
		APIMocks.createDummyUserWithJsonFile();;
		String dummyJson = "{\r\n"
				+ "    \"name\": \"naveen\"\r\n"
				+ "}";
		Response response = restClient.post(BASE_URL_LOCALHOST_PORT, "/api/users", dummyJson, null, null, AuthType.NO_AUTH, ContentType.JSON);
	    response.then()
	            .assertThat()
	            .statusCode(201)
	            .statusLine(equalTo("HTTP/1.1 201 user is created"))
	            .body("name", equalTo("api"));
	            
	
	}

}
