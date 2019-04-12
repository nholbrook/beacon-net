package com.nickholbrook.beaconnet.api;

import java.util.List;

import com.nickholbrook.beaconnet.model.Account;
import com.nickholbrook.beaconnet.model.Beacon;
import com.nickholbrook.beaconnet.model.Entry;
import com.nickholbrook.beaconnet.service.AccountTableService;
import com.nickholbrook.beaconnet.service.BeaconTableService;
import com.nickholbrook.beaconnet.service.DynamoDBAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountAPI {

	private final static AccountTableService accountTableService = new AccountTableService();
	protected static final String ACCOUNT_LIST = "accountList";
	protected AccountTableService getAccountTableService() {
		return accountTableService;
	}

	@CrossOrigin
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	@ResponseBody
	public List<Account> getAccount(@RequestParam("accountId") String accountId) {
		List<Account> accountList = getAccountTableService().getAccount(accountId); //"41d4bac2-cd9f-42dd-852b-003703fc6bca"
		return accountList;
	}
}
