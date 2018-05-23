package com.webservicesassignment.pages;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;
import java.text.MessageFormat;
import java.util.List;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import com.webservicesassignment.components.OrderComponent;

public class HomeTestPage extends WebDriverBaseTestPage<WebDriverTestPage> {

	@FindBy(locator = "orders.lst.home")
	private List<OrderComponent> orderlst;

	@FindBy(locator = "createnew.btn.home")
	private QAFWebElement createnewBtn;

	// @FindBy(locator = "order.particular.loc")
	// private QAFWebElement orderParticular;

	@Override
	protected void openPage(PageLocator pageLocator, Object... args) {
		driver.get("http://qas.qmetry.com/struts2-rest-showcase-2.3.16.3/orders");
		driver.navigate().refresh();
	}

	public List<OrderComponent> getOrderLst() {
		return orderlst;
	}

	public OrderComponent getOrderParticular(String orderdetail) {
		OrderComponent orderParticular = new OrderComponent(
				String.format(getBundle().getString("order.particular.orderComponent"), orderdetail));
		return orderParticular;
	}

	public QAFWebElement getCreatenewBtn() {
		return createnewBtn;
	}

	public void getAllOrders() {
		for (OrderComponent orders : orderlst) {
			Reporter.log("Client Name : " + orders.getOrderClient().getText() + " " + "\n Amount : "
					+ orders.getOrderAmount().getText());
			
		}
	}

	public void launchPage() {
		driver.navigate().refresh();
		super.launchPage(null);
	}
}
