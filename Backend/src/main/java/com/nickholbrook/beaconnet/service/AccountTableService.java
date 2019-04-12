package com.nickholbrook.beaconnet.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.nickholbrook.beaconnet.dynamodb.DynamoDBUtil;
import com.nickholbrook.beaconnet.model.Account;
import com.nickholbrook.beaconnet.model.Summary;

import org.springframework.stereotype.Service;

@Service
public class AccountTableService {

	private static DynamoDBService dynamoDBService;
	final private Logger log;
	private final String tableName = "beacon-net-entries-account";

	public AccountTableService() {
		log = Logger.getLogger( this.getClass().getName() );
		if (dynamoDBService == null) {
			dynamoDBService = new DynamoDBService();
		}
	}


	public List<Account> getAccount(String accountId) {
		AmazonDynamoDB client = dynamoDBService.getClient();
		ScanRequest scanRequest = new ScanRequest().withTableName(tableName);
		ScanResult result = client.scan(scanRequest);
		List<Account> accountList = new ArrayList<Account>();
		if (result.getCount() > 0) {
			// The itemList is a set of one or more DynamoDB row values stored in a attribute name/value map.
			List<Map<String, AttributeValue>> itemList = result.getItems();
			try {
				for (Map<String, AttributeValue> item : itemList) {
					if (item.get("accountId").getS().equals(accountId)) {
						Account info = new Account();
						DynamoDBUtil.attributesToObject(info, item);
						System.out.println("info:" + info);
						accountList.add(info);
					}
				}
				if (accountList.size() > 1) {
					Account[] infoArray = accountList.toArray( new Account[1] );
					System.out.println("0:" + infoArray[0] + " 1:" + infoArray[1]);
					//Arrays.sort(infoArray, new SummaryComparator() );
					accountList.clear();
					accountList.addAll(Arrays.asList( infoArray ) );
				}
			}
			catch (ReflectiveOperationException e) {
				log.severe("getAccounts: " + e.getLocalizedMessage());
			}
		}
		return accountList;
	}
}
