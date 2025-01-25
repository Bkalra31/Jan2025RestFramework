package com.qa.basicauth.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasicAuthTest extends BaseTest {
	
	@Test
	public void basicAuthTest()
	{
		Response response = restClient.get(BASE_URL_BASIC_AUTH, "/basic_auth", null, null, AuthType.BASIC_AUTH, ContentType.JSON);
		Assert.assertEquals(response.getBody().asString().contains("Congratulations!"), true);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
