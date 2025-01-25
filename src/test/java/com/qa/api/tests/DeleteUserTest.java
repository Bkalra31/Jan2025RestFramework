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


public class DeleteUserTest extends BaseTest {
	
	@Test
	public void deleteUserWithBuilderTest()
	{
		User user = User.builder()
				        .name("apiAutomation")
				        .email(StringUtility.getRandomEmailId())
				        .gender("male")
				        .status("active")
				        .build();
		
		Response response = restClient.post(BASE_URL_GOREST, "/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(),201);
		
		//fetch the user id
		String userId = response.jsonPath().getString("id");
		System.out.println("user id is = "+userId);
		
		//get the response based on id
		Response responseGet = restClient.get(BASE_URL_GOREST, "/public/v2/users/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.getStatusCode(),200);
		Assert.assertEquals(responseGet.jsonPath().getString("id"), userId);
		Assert.assertEquals(responseGet.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(responseGet.jsonPath().getString("email"), user.getEmail());
		
		//delete the same user using user id 
		Response responsePut = restClient.delete(BASE_URL_GOREST, "/public/v2/users/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.getStatusCode(),200);
		
	}

}
