package com.qa.mocking.api.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIMockGetUserTest extends BaseTest {

	@Test
	public void getDummyUserTest()
	{
		APIMocks.getDummyUser();
		Response response = restClient.get(BASE_URL_LOCALHOST_PORT, "/api/users", null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("name"), "Tom");
	}
	
	@Test
	public void getDummyUserTestWithJsonFile()
	{
		APIMocks.getDummyUserWithJsonFile();;
		Response response = restClient.get(BASE_URL_LOCALHOST_PORT, "/api/users", null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("name"), "api");
	}
	
	@Test
	public void getDummyUserTestWithQueryParamsTest()
	{
		APIMocks.getDummyUserWithQueryParams();
		Map<String, String> queryParams = Map.of("name", "queryTest");
		Response response = restClient.get(BASE_URL_LOCALHOST_PORT, "/api/users", queryParams, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("name"), "api");
	}
	
	
}
