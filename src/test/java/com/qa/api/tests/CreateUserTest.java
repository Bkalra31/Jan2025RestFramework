package com.qa.api.tests;

import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;

public class CreateUserTest extends BaseTest{
	
	@Test
	public void createUserTest()
	{
		User user = new User(null,"Bhushan", StringUtility.getRandomEmailId(), "male", "active");
		Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users",user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test
	public void createUserWithBuilderTest()
	{
		User user =  User.builder()
				         .name("Naveen")
				         .email(StringUtility.getRandomEmailId())
				         .gender("male")
				         .status("active")
				         .build();
		Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	    Assert.assertEquals(response.getStatusCode(), 201);
	    
	    //fetch the user id
	    String userId = response.jsonPath().getString("id");
	    System.out.println("user id is = " + userId);
	    
	    //get the details 
	    Response responseGet = restClient.get(BASE_URL_GOREST, "/public/v2/users/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	    Assert.assertEquals(responseGet.getStatusCode(), 200);
	    Assert.assertEquals(responseGet.jsonPath().getString("id"), userId);
	    Assert.assertEquals(responseGet.jsonPath().getString("name"), user.getName());
	    Assert.assertEquals(responseGet.jsonPath().getString("email"), user.getEmail());
	    
	}
	
	@Test
	public void createUserWithJsonFileTest()
	{
		File userJsonFile = new File("./src/test/resources/jsons/user.json");
		Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users", userJsonFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}

}
