package com.qa.api.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FrameworkException;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserAPITestWithDynamicJsonFile extends BaseTest {
	
	@Test
	public void createUserWithJsonFileTest()
	{
		String jsonFilePath = "src/test/resources/jsons/user.json";
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode jsonNode =  mapper.readTree(Files.readAllBytes(Paths.get(jsonFilePath)));
			
			ObjectNode objNode = (ObjectNode)jsonNode;
			objNode.put("email", StringUtility.getRandomEmailId());
			
			String updatedJson = mapper.writeValueAsString(objNode);
			
			Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ALL_ENDPOINT, updatedJson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
			Assert.assertEquals(response.getStatusCode(), 201);
			Assert.assertEquals(SchemaValidator.schemaValidator(response, "schema/user-schema.json"), true);
			
		} catch (IOException e) {
			throw new FrameworkException("Dynamic json update failed");
		}
		
	}

}
