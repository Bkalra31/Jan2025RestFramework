package com.qa.schemavalidation.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserAPISchemaTest extends BaseTest {
	
	@Test
	public void userAPISchemaTest()
	{
//		RestAssured.given()
//		           .baseUri("https://gorest.co.in")
//		           .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
//		           .when()
//		           .get("/public/v2/users/7657254")
//		           .then()
//		           .assertThat()
//		           .statusCode(200)
//		           .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/user-schema.json"));
		
		
		//Post 
		User user = User.builder()
				        .name("Bhushan")
				        .email("bhushan@nal6.com")
				        .gender("male")
				        .status("active")
				        .build();
		
		Response responsePost = restClient.post(BASE_URL_GOREST, GOREST_USERS_ALL_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Integer userId = responsePost.jsonPath().get("id");
		
		//Get
		Response responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ALL_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
	    boolean flag = SchemaValidator.schemaValidator(responseGet, "schema/user-schema.json");
	    Assert.assertEquals(flag, true);
	}

}
