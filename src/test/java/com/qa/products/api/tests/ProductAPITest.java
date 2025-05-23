package com.qa.products.api.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITest extends BaseTest{
	
	@Test
	public void getProductsAPITest()
	{
		Response response = restClient.get(BASE_URL_PRODUCT, "/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
	    Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void getProductAPILimitTest()
	{
		Map<String, String> queryParams = Map.of("limit", "5");
		Response response = restClient.get(BASE_URL_PRODUCT, "/products", queryParams, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
