package com.qa.api.client;

import java.io.File;
import java.util.Base64;
import java.util.Map;

import org.eclipse.jetty.client.api.Request;
import static org.hamcrest.Matchers.*;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FrameworkException;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.expect;

public class RestClient {
	/**
	 * Storing Responses to avoid code duplication
	 */
	private ResponseSpecification response200 = expect().statusCode(200);
	private ResponseSpecification response200or201 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));
	private ResponseSpecification response201 = expect().statusCode(201);
	private ResponseSpecification response404 = expect().statusCode(404);
	private ResponseSpecification response500 = expect().statusCode(500);
	private ResponseSpecification response204or200 = expect().statusCode(anyOf(equalTo(204),equalTo(200)));
	private ResponseSpecification response403 = expect().statusCode(403);
	private ResponseSpecification response401 = expect().statusCode(401);
	
//	private String baseUrl = ConfigManager.get("baseUrl");
	
	private RequestSpecification setupRequest(String baseUrl, AuthType authType, ContentType contentType)
	{
		RequestSpecification request = RestAssured.given().log().all()
				                                  .baseUri(baseUrl)
				                                  .contentType(contentType)
                                                  .accept(contentType);
		
		switch(authType)
		{
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer "+ConfigManager.get("bearerToken"));
			break;
		case CONTACTS_BEARER_TOKEN:
			request.header("Authorization","Bearer "+ConfigManager.get("contacts_BearerToken"));
			break;
		case OAUTH2:
			request.header("Authorization","Bearer "+generateOAUTH2Token());
			break;
		case BASIC_AUTH:
			request.header("Authorization","Basic "+generateBasicAuthToken());
			break;
		case API_KEY:
			request.header("x-api-key",ConfigManager.get("apiKey"));
			break;
		case NO_AUTH:
			System.out.println("NO AUTH IS REQUIRED");
			break;
		default :
			System.out.println("Please provide the valid authhentication type");
			throw new FrameworkException("NO AUTH SUPPORTED");
		}
		
      return request;
      
	}
	
	/**
	 * This method will return the basic Auth token
	 * @return
	 */
	
	private String generateBasicAuthToken()
	{
		String credentials = ConfigManager.get("basicUsername")+":"+ConfigManager.get("basicPassword");
		return Base64.getEncoder().encodeToString(credentials.getBytes());
	}
	
	
	/**
	 * This method will return the OAuth2Token
	 * @return
	 */
	private String generateOAUTH2Token()
	{
		return RestAssured.given()
				          .formParam("client_id", ConfigManager.get("clientId"))
				          .formParam("client_secret", ConfigManager.get("clientSecret"))
				          .formParam("grant_type",ConfigManager.get("grantType"))
				          .post(ConfigManager.get("tokenUrl"))
				          .then()
				          .extract()
				          .path("access_token");
	}
	
  //**************************CRUD METHODS********************************
	
	/**
	 * This method is used to call the get APIs
	 * @param endPoint
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return
	 */
	
	public Response get(String baseUrl, String endPoint, Map<String,String> queryParams, Map<String,String> pathParams, AuthType authType, ContentType contentType)
	{
	  RequestSpecification request =  setupRequest(baseUrl, authType, contentType);
	  applyParams(request,queryParams,pathParams);
	  Response response = request.get(endPoint).then().spec(response200).extract().response(); 
	  response.prettyPrint();
	  return response;
	}
	
	
	/**
	 * This method is used to call the post APIs
	 * @param <T>
	 * @param endPoint
	 * @param body
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return
	 */
	public <T> Response post(String baseUrl, String endPoint, T body, Map<String,String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType)
	{
		RequestSpecification request = setupRequest(baseUrl, authType,contentType);
		applyParams(request,queryParams,pathParams);
		Response response = request.body(body).post(endPoint).then().spec(response200or201).extract().response();
		response.prettyPrint();
		return response;
	}
	
	public  Response post(String baseUrl, String endPoint, File file, Map<String,String> queryParams, Map<String, String> pathParams, AuthType authType, ContentType contentType)
	{
		RequestSpecification request = setupRequest(baseUrl, authType,contentType);
		applyParams(request,queryParams,pathParams);
		Response response = request.body(file).post(endPoint).then().spec(response201).extract().response();
		response.prettyPrint();
		return response;
	}
	
	public <T> Response put(String baseUrl, String endPoint, T body, Map<String,String> queryParams, Map<String,String> pathParams, AuthType authType, ContentType contentType)
	{
		
		RequestSpecification request = setupRequest(baseUrl, authType,contentType);
		applyParams(request,queryParams,pathParams);
		Response response = request.body(body).put(endPoint).then().spec(response200).extract().response();
		response.prettyPrint();
		return response;
	}
	
	public <T> Response patch(String baseUrl, String endPoint, T body, Map<String,String> queryParams, Map<String,String> pathParams, AuthType authType, ContentType contentType)
	{
		
		RequestSpecification request = setupRequest(baseUrl, authType,contentType);
		applyParams(request,queryParams,pathParams);
		Response response = request.body(body).patch(endPoint).then().spec(response200).extract().response();
		response.prettyPrint();
		return response;
	}
	
	public Response delete(String baseUrl, String endPoint, Map<String,String> queryParams, Map<String,String> pathParams, AuthType authType, ContentType contentType)
	{
		
		RequestSpecification request = setupRequest(baseUrl, authType,contentType);
		applyParams(request,queryParams,pathParams);
		Response response = request.delete(endPoint).then().spec(response204or200).extract().response();
		response.prettyPrint();
		return response;
	}
	
	
	public void applyParams(RequestSpecification request, Map<String,String> queryParams, Map<String,String> pathParams)
	{
		if(queryParams!=null)
		{
			request.queryParams(queryParams);
		}
		if(pathParams!=null)
		{
			request.pathParams(pathParams);
		}
	}
	
	
}
