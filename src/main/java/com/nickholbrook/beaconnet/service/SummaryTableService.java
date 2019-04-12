package com.nickholbrook.beaconnet.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.amazonaws.Response;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.nickholbrook.beaconnet.dynamodb.DynamoDBUtil;
import com.nickholbrook.beaconnet.model.Summary;

import org.springframework.stereotype.Service;

@Service
public class SummaryTableService {
	private static DynamoDBService dynamoDBService;
	final private Logger log;
	private final String tableName = "beacon-net-entries-summary";

	public SummaryTableService() {
		log = Logger.getLogger( this.getClass().getName() );
		if (dynamoDBService == null) {
			dynamoDBService = new DynamoDBService();
		}
	}


	public List<Summary> getSummaries() {
		AmazonDynamoDB client = dynamoDBService.getClient();
		ScanRequest scanRequest = new ScanRequest().withTableName(tableName);
		ScanResult result = client.scan(scanRequest);
		List<Summary> summaryList = new ArrayList<Summary>();
		if (result.getCount() > 0) {
			// The itemList is a set of one or more DynamoDB row values stored in a attribute name/value map.
			List<Map<String, AttributeValue>> itemList = result.getItems();
			try {
				for (Map<String, AttributeValue> item : itemList) {
					System.out.println("item: " + item);
					Summary info = new Summary();
					DynamoDBUtil.attributesToObject(info, item);
					System.out.println("info:" + info);
					summaryList.add(info);
				}
				if (summaryList.size() > 1) {
					Summary[] infoArray = summaryList.toArray( new Summary[1] );
					System.out.println("0:" + infoArray[0] + " 1:" + infoArray[1]);
					//Arrays.sort(infoArray, new SummaryComparator() );
					summaryList.clear();
					summaryList.addAll(Arrays.asList( infoArray ) );
				}
			}
			catch (ReflectiveOperationException e) {
				log.severe("getSummaries: " + e.getLocalizedMessage());
			}
		}
		return summaryList;
	}



	/*public void  addSummaries(){

		if(getSummaries()>0){

			for(int i=0; i<15; i++){

				List<Summary> ids=new ArrayList<>();
				ids.add(summaryId);

			}
		}


	}*/


	/*public Response Endpoints(){

		return  Response.ok().build();
	}*/

}
