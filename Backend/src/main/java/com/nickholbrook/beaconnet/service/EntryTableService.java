package com.nickholbrook.beaconnet.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.nickholbrook.beaconnet.dynamodb.DynamoDBUtil;
import com.nickholbrook.beaconnet.model.Entry;

import org.springframework.stereotype.Service;

@Service
public class EntryTableService {
	private static DynamoDBService dynamoDBService;
	final private Logger log;
	private final String tableName = "beacon-net-entries";

	public EntryTableService() {
		log = Logger.getLogger( this.getClass().getName() );
		if (dynamoDBService == null) {
			dynamoDBService = new DynamoDBService();
		}
	}


	public List<Entry> getEntries() {
		AmazonDynamoDB client = dynamoDBService.getClient();
		ScanRequest scanRequest = new ScanRequest().withTableName(tableName);
		ScanResult result = client.scan(scanRequest);
		List<Entry> entryList = new ArrayList<Entry>();
		if (result.getCount() > 0) {
			// The itemList is a set of one or more DynamoDB row values stored in a attribute name/value map.
			List<Map<String, AttributeValue>> itemList = result.getItems();
			try {
				for (Map<String, AttributeValue> item : itemList) {
					System.out.println("item: " + item);
					Entry info = new Entry();
					DynamoDBUtil.attributesToObject(info, item);
					System.out.println("info:" + info);
					entryList.add(info);
				}
				if (entryList.size() > 1) {
					Entry[] infoArray = entryList.toArray( new Entry[1] );
					System.out.println("0:" + infoArray[0] + " 1:" + infoArray[1]);
					//Arrays.sort(infoArray, new EntryComparator() );
					entryList.clear();
					entryList.addAll(Arrays.asList( infoArray ) );
				}
			}
			catch (ReflectiveOperationException e) {
				log.severe("getEntrys: " + e.getLocalizedMessage());
			}
		}
		System.out.println(entryList);
		return entryList;
	}

}
