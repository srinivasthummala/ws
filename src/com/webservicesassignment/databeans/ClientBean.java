package com.webservicesassignment.databeans;

import com.qmetry.qaf.automation.data.BaseDataBean;
import com.qmetry.qaf.automation.util.Randomizer;

public class ClientBean extends BaseDataBean{
	
	@Randomizer(dataset={"client1","client2","client3","client4","client5"})
	private String clientName;
	
	@Randomizer(dataset={"145","586","453","566","485"})
	private String amount;

	public String getClientName() {
		return clientName;
	}

	public String getAmount() {
		return amount;
	}
	
}
