package com.qa.products.api.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITestWithJsonPathValidator extends BaseTest {
	
	@Test
	public void getProductTest()
	{
		Response response = restClient.get(BASE_URL_PRODUCT, "/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		List<Number> prices = JsonPathValidator.readList(response, "$[?(@.price>50)].price");
		System.out.println(prices);
		
		List<Number> id = JsonPathValidator.readList(response, "$[?(@.price>50)].id");
		System.out.println(id);
		
		List<Double> rates = JsonPathValidator.readList(response, "$[?(@.price>50)].rating.rate");
		System.out.println(rates);
		
		List<Integer> count = JsonPathValidator.readList(response, "$[?(@.price>50)].rating.count");
		System.out.println(count);
		
		//list of map
		
		List<Map<String, Object>> jeweleryList = JsonPathValidator.readListOfMaps(response, "$[?(@.category =='jewelery')].['title','price']");
		System.out.println(jeweleryList.size());
		for(Map<String,Object> product : jeweleryList)
		{
			String title = (String) product.get("title");
			Number price = (Number) product.get("price");
			System.out.println("title is = "+title);
			System.out.println("price is = "+price);
			
		//min price
			
			Double minPrice = JsonPathValidator.read(response, "min($[*].price)");
			System.out.println("min price is = "+minPrice);
			
			
			
			
			
			
		}
	}

}
