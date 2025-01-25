package com.qa.products.api.tests;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonUtils;
import com.qa.api.pojo.Product;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITestWithDeserialization extends BaseTest {
	
	@Test
	public void getProductsTest()
	{
		Response response = restClient.get(BASE_URL_PRODUCT, "/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
		
		Product[] product = JsonUtils.deserialize(response, Product[].class);
		
		System.out.println(Arrays.toString(product));
		
		for(Product p : product)
		{
			System.out.println("the id is = "+p.getId());
			System.out.println("the price is = "+p.getPrice());
			System.out.println("the title is = "+p.getTitle());
			System.out.println("the rate is = "+p.getRating().getRate());
			System.out.println("the count is = "+ p.getRating().getCount());
			
			System.out.println("---------------------------");
		}
	}

}
