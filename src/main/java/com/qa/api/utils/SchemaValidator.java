package com.qa.api.utils;

import com.qa.api.exceptions.FrameworkException;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidator {
	
	public static boolean schemaValidator(Response response, String schemaFileName)
	{
		try 
		{
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFileName));
		System.out.println("Schema Validation Passed");
		return true;
		}
		catch(Exception e)
		{
			throw new FrameworkException("Schema Validation Failed");
			
		}
	}

}
