package com.webservicesassignment.components;

import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebComponent;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

public class OrderComponent extends QAFWebComponent {

	public OrderComponent(String locator) {
		super(locator);
	}

	@FindBy(locator = "order.id.orderComponent")
	private QAFWebElement ordersId;
	
	@FindBy(locator = "name.client.orderComponent")
	private QAFWebElement orderClient;
	
	@FindBy(locator = "order.amount.orderComponent")
	private QAFWebElement orderAmount;
	
	@FindBy(locator = "view.btn.orderComponent")
	private QAFWebElement viewBtn;
	
	@FindBy(locator = "edit.btn.orderComponent")
	private QAFWebElement editBtn;
	
	@FindBy(locator = "delete.btn.orderComponent")
	private QAFWebElement deleteBtn;

	public QAFWebElement getOrderClient() {
		return orderClient;
	}

	public QAFWebElement getOrderAmount() {
		return orderAmount;
	}

	public QAFWebElement getOrderId() {
		return ordersId;
	}

	public QAFWebElement getEditBtn() {
		return editBtn;
	}

	public QAFWebElement getViewBtn() {
		return viewBtn;
	}

	public QAFWebElement getDeleteBtn() {
		return deleteBtn;
	}

	

}
