package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathValidator {
	
	private static String getResponseAsString(Response response)
	{
		return response.getBody().asString();
	}
	
	public static <T> T read(Response response, String jsonPath)
	{
		String jsonResponse = response.getBody().asString();
		ReadContext readContext = JsonPath.parse(jsonResponse);
		T value= readContext.read(jsonPath);
		return value;
	}
	
	public static <T> List<T> readList(Response response, String jsonPath)
	{
		String jsonResponse = getResponseAsString(response);
		ReadContext readContext = JsonPath.parse(jsonResponse);
		List<T> list = readContext.read(jsonPath);
		return list;
	}
	
	public static <T> List<Map<String, T>> readListOfMaps(Response response, String jsonPath)
	{
		String jsonResponse = getResponseAsString(response);
		ReadContext readContext = JsonPath.parse(jsonResponse);
		List<Map<String, T>> list = readContext.read(jsonPath);
		return list;
	}

}
