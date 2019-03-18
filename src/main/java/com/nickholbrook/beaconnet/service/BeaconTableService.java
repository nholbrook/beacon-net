package com.nickholbrook.beaconnet.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.nickholbrook.beaconnet.dynamodb.DynamoDBUtil;
import com.nickholbrook.beaconnet.model.Beacon;
import com.nickholbrook.beaconnet.model.Beacon;

import org.springframework.stereotype.Service;

@Service
public class BeaconTableService {
	private static DynamoDBService dynamoDBService = null;
	final private Logger log;
	private final String tableName = "beacon-net-beacons";

	public BeaconTableService() {
		log = Logger.getLogger( this.getClass().getName() );
		if (dynamoDBService == null) {
			dynamoDBService = new DynamoDBService();
		}
	}

	public List<Beacon> getAllBeacons(String accountId) {
		List<Beacon> beaconList = new ArrayList<Beacon>();
		Condition containsCondition = new Condition()
				.withComparisonOperator(ComparisonOperator.CONTAINS.toString())
				.withAttributeValueList(new AttributeValue().withS( accountId ));
		Map<String, Condition> keyConditions = new HashMap<String, Condition>();
		keyConditions.put("accountId", containsCondition);

		ScanRequest scanRequest = new ScanRequest()
				.withTableName( tableName )
				.withScanFilter(keyConditions);

		AmazonDynamoDB client = dynamoDBService.getClient();
		ScanResult result = client.scan(scanRequest);
		if (result.getCount() > 0) {
			// The itemList is a set of one or more DynamoDB row values stored in a attribute name/value map.
			List<Map<String, AttributeValue>> itemList = result.getItems();
			try {
				for (Map<String, AttributeValue> item : itemList) {
					Beacon info = new Beacon();
					DynamoDBUtil.attributesToObject(info, item);
					beaconList.add(info);
				}
			}
			catch (ReflectiveOperationException e) {
				log.severe("getAllBeacons: " + e.getLocalizedMessage());
			}
		}
		return beaconList;
	}

}
