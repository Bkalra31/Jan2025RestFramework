package com.qa.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.exceptions.FrameworkException;

import io.restassured.response.Response;

public class JsonUtils {
	
	private static ObjectMapper objectmapper = new ObjectMapper();
	
	public static <T> T deserialize(Response response, Class<T> targetClass)
	{
		try {
			return objectmapper.readValue(response.getBody().asString(), targetClass);
		} catch (Exception e) {
			throw new FrameworkException("deserialization is failed "+ targetClass.getName());
		} 
	}
 
}
