package com.qa.api.tests;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
public class GetUserTest extends BaseTest {
	
	@Test
	public void getUserTest()
	{
    	Map<String, String> queryParams = new HashMap<String, String>();
    	queryParams.put("name", "Bhushan");
    	queryParams.put("status", "active");
    	Response response = restClient.get(BASE_URL_GOREST, "/public/v2/users", queryParams, null, AuthType.BEARER_TOKEN, ContentType.JSON);
    	Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void singleUserTest()
	{
		Response response = restClient.get(BASE_URL_GOREST, "/public/v2/users/7009102", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	    Assert.assertEquals(response.getStatusCode(), 200);
	
	}

}
