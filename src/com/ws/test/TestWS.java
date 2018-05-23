package com.ws.test;

import org.testng.annotations.Test;

import com.qmetry.qaf.automation.util.Validator;
import com.qmetry.qaf.automation.ws.Response;
import com.qmetry.qaf.automation.ws.RestWSTestCase;

public class TestWS extends RestWSTestCase{

	@Test
	public void registerUser() {
		getWebResource("/register").header("Content-Type", "application/json").post("{\"username\": \"goutamp\", \"password\": \"hnjceg\",\"email\":\"gou@gmail.com\"}");
		Response responce = getResponse();
		//Validator.verifyThat("", matcher)
	}
}
