package com.qa.api.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;

public class APIMocks {
	
	public static void getDummyUser()
	{
		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse()
			    .withStatus(200)
			    .withHeader("ContentType", "application/json")
			    .withBody("{\r\n"
			    		+ "	\"name\" : \"Tom\"\r\n"
			    		+ "}")
			    )
			);	
				
	}
	
	public static void getDummyUserWithJsonFile()
	{
		stubFor(get(urlEqualTo("/api/users"))
				.willReturn(aResponse()
				.withStatus(200)
				.withHeader("ContentType", "application/json")
				.withBodyFile("user.json")
				)
			);	
				
	}
	
	public static void getDummyUserWithQueryParams()
	{
		stubFor(get(urlPathEqualTo("/api/users"))
				.withQueryParam("name", equalTo("queryTest"))
				.willReturn(aResponse()
				.withStatus(200)
				.withHeader("ContentType", "application/json")
				.withBodyFile("user.json")
				)
			);	
				
	}
	
	public static void getDummyProductsWithJsonFile()
	{
		stubFor(get(urlEqualTo("/api/products"))
				.willReturn(aResponse()
			    .withStatus(200)
			    .withHeader("ContentType", "application/json")
			    .withBodyFile("product.json")
			    )
			);	
	}
	
	
	//******************CreateDummyUser(POST)***************************
	
	public static void createDummyUser()
	{
		stubFor(post(urlEqualTo("/api/users"))
				.withHeader("Content-Type", equalTo("application/json"))
				.willReturn(aResponse()
						.withStatus(201)
						.withHeader("Content-Type", "application/json")
						.withStatusMessage("user is created")
						.withBody("{\"id\" : 1,\"name\" : \"Naveen\"}")
						)
				);
	}
	
	public static void createDummyUserWithJsonFile()
	{
		stubFor(post(urlEqualTo("/api/users"))
				.withHeader("Content-Type", equalTo("application/json"))
				.willReturn(aResponse()
						.withStatus(201)
						.withHeader("Content-Type", "application/json")
						.withStatusMessage("user is created")
						.withBodyFile("user.json")
						)
				);
	}
	
	//******************DeleteDummyUser(Stub call for Delete)***************************
	
	public static void deleteDummyUserWithJsonFile()
	{
		stubFor(delete(urlEqualTo("/api/users/1"))
				.willReturn(aResponse()
						.withStatus(204)
						.withBody("No Content")
						)
				);
	}

}
