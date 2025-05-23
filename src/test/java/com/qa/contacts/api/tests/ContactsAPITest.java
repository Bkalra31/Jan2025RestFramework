package com.qa.contacts.api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactsCredentials;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsAPITest extends BaseTest {
	
	private String tokenID;
	
	@BeforeMethod
	public void getToken()
	{
		ContactsCredentials creds = ContactsCredentials.builder()
				                                       .email("naveenanimation20@gmail.com")
				                                       .password("test@123")
				                                       .build();
						
		Response response = restClient.post(BASE_URL_CONTACTS, "/users/login", creds, null, null, AuthType.NO_AUTH, ContentType.JSON);
		tokenID = response.jsonPath().getString("token");
		System.out.println("token id is = "+tokenID);
		ConfigManager.set("contacts_BearerToken", tokenID);
	}
	
	@Test
	public void getContactsTest()
	{
		Response response = restClient.get(BASE_URL_CONTACTS, "/contacts", null, null, AuthType.CONTACTS_BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
