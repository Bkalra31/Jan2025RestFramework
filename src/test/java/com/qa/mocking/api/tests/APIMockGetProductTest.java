package com.qa.mocking.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIMockGetProductTest extends BaseTest{
	
	@Test
	public void getDummyProductsTestWithJsonFile()
	{
		APIMocks.getDummyProductsWithJsonFile();
		Response response = restClient.get(BASE_URL_LOCALHOST_PORT, "/api/products", null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
		
		
	}

}
