package com.webservicesassignment.apiautomation;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;
import static com.qmetry.qaf.automation.step.CommonStep.requestForResource;
import static com.qmetry.qaf.automation.step.CommonStep.response_should_have_status;
import static com.qmetry.qaf.automation.step.CommonStep.response_should_have_statuscode;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.testng.dataprovider.QAFDataProvider;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.ws.Response;
import com.qmetry.qaf.automation.ws.RestWSTestCase;
import com.sun.jersey.api.client.ClientResponse;
import com.webservicesassignment.databeans.ClientBean;


import jxl.Workbook;

public class RestAPIsAutomation extends RestWSTestCase {

	@QAFTestStep(description = "user view all the orders")
	public void getOrders() {

		String resource = getBundle().getString("get.orders.api");
		requestForResource(resource);
		Response response = getResponse();
		String jsonResponse = new Gson().toJson(response.getMessageBody());
		System.out.println("Response status code : " + response.getStatus().getStatusCode());
		System.out.println("Response body : " + jsonResponse);
		System.out.println("Response type : " + response.getMediaType());
		// Validator.assertThat("validate get orders status", , matcher);
	}

	@QAFTestStep(description="user stores all orders in report")
	public void storeAllOrders() {
//		HomeTestPage homeTestPage=new HomeTestPage();
//		homeTestPage.launchPage();
//		homeTestPage.waitForPageToLoad();
//		homeTestPage.getAllOrders();
	}
	
	@QAFDataProvider()
	@QAFTestStep(description = "user creates new order with details {0},{1}")
	public void addOrder(String name, String amount) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("clientName", name);
		data.put("amount", amount);

		ClientBean bean = new ClientBean();
		bean.fillData(data);

		String requestBody = String.format("{'clientName':'%s','amount':'%s'}", bean.getClientName(), bean.getAmount());
		String resource = getBundle().getString("env.baseurl") + getBundle().getString("add.order.api");

		//getClient().resource(resource).post(requestBody);
		getClient().resource(resource).method("post",requestBody);
	}

	@QAFTestStep(description = "user verifies order created")
	public void verifyOrderStatus() {
		response_should_have_status("Created");
		response_should_have_statuscode(201);

		String location = getResponse().getHeaders().getFirst("Location");
		String orderId = location.substring(location.lastIndexOf("/") + 1, location.lastIndexOf("."));

		// HomeTestPage homeTestPage=new HomeTestPage();
		// homeTestPage.launchPage();
		// homeTestPage.waitForPageToLoad();
		// homeTestPage.getOrderParticular(orderId).assertPresent();
	}

	@QAFTestStep(description = "store order id in {0}")
	public void clientId(String orderId) {
		String location = getResponse().getHeaders().getFirst("Location");
		assertThat("Order location", location, Matchers.notNullValue());
		getBundle().setProperty(orderId, location.substring(location.lastIndexOf("/") + 1, location.lastIndexOf(".")));
	}

	@QAFTestStep(description = "user checks order exist with id {0}")
	public void getOrder(String orderId) {
		String resource = String.format(getBundle().getString("view.order.api"), orderId);
		requestForResource(resource);
		response_should_have_status("OK");
	}

	@QAFTestStep(description = "User deletes order with id {0}")
	public void deleteOrder(String orderId) {
		String resource = getBundle().getString("env.baseurl")
				+ String.format(getBundle().getString("delete.order.api"), orderId);
		getClient().resource(resource).delete();
	}

	@QAFTestStep(description = "user verifies order with id {0} deleted")
	public void verifyOrderDeleted(String orderId) {
		response_should_have_status("OK");
		response_should_have_statuscode(200);

		// HomeTestPage homeTestPage=new HomeTestPage();
		// homeTestPage.launchPage();
		// homeTestPage.waitForPageToLoad();
		// homeTestPage.getOrderParticular(orderId).getOrderId().assertNotPresent();
	}

	@QAFTestStep(description = "user updates order details {0},{1} with id {2}")
	public void updateOrder(String name, String amount, String orderId) {

		Map<String, String> data = new HashMap<String, String>();
		data.put("clientName", name);
		data.put("amount", amount);

		ClientBean bean = new ClientBean();
		bean.fillData(data);

		String requestBody = String.format("{'clientName':'%s','amount':'%s'}", bean.getClientName(), bean.getAmount());
		String resource = getBundle().getString("env.baseurl")
				+ String.format(getBundle().getString("edit.order.api"), orderId);

		getClient().resource(resource).put(requestBody);

		// HomeTestPage homeTestPage=new HomeTestPage();
		// homeTestPage.launchPage();
		// homeTestPage.waitForPageToLoad();
		// homeTestPage.getOrderParticular(orderId).getOrderId().assertPresent();
	}

	@QAFTestStep(description = "user verifies order updated with id {0}")
	public void verifyOrderUpdateStatus(String orderId) {
		response_should_have_status("OK");
		response_should_have_statuscode(200);

		// HomeTestPage homeTestPage=new HomeTestPage();
		// homeTestPage.launchPage();
		// homeTestPage.waitForPageToLoad();
		// homeTestPage.getOrderParticular(orderId).assertPresent();
	}

	

	@QAFTestStep(description = "user updates order details {0} with id {1}")
	public void updateOrderPartially(String amount, String orderId) {
System.out.println("step 1 <====================");
//		Map<String, String> data = new HashMap<String, String>();
//		data.put("amount", amount);
//
//		ClientBean bean = new ClientBean();
//		bean.fillData(data);

		String requestBody = String.format("{'amount':'%s'}", amount);
		String resource = getBundle().getString("env.baseurl")
				+ String.format(getBundle().getString("edit.order.api"), orderId);
		System.out.println(requestBody);
		System.out.println(resource);
		System.out.println("step 2 <====================");
		
		getClient().resource(resource).method("patch", requestBody);
		
		System.out.println("step 3 <====================");
		response_should_have_status("OK");
		response_should_have_statuscode(200);
		System.out.println("-=====> Patch success full");
	}
	
	@QAFTestStep(description="user verifies order with id {0}")
	public void verifyOrder(String orderId){
		String resource = getBundle().getString("env.baseurl")
				+ String.format(getBundle().getString("edit.order.api"), orderId);
		
		requestForResource(resource);
		
		JSONObject jsonResponse=new JSONObject(getResponse().getMessageBody());
		jsonResponse.get("amount");
		jsonResponse.get("clientName");
		jsonResponse.get("id");
		Reporter.log("Response "+jsonResponse);
		
	}
	@QAFTestStep(description = "navigates to site")
	public void hitURI() {

		String resource=getBundle().getString("env.baseurl") + getBundle().getString("post.endpoints");
		getClient().resource(resource).get(ClientResponse.class);
	}

}

