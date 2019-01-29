package com.nickholbrook.beaconnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@RestController
public class BeaconNetApplication {

	HashMap<String, AttributeValue> key_to_get = new HashMap<String, AttributeValue>();

	@RequestMapping("/")
	public String home() {
		String table_name = "beacon-net";
		String name = "DC:0C:5C:81:AB:D4";
		String projection_expression = null;

		System.out.format("Retrieving item \"%s\" from \"%s\"\n",
				name, table_name);

		HashMap<String,AttributeValue> key_to_get =
				new HashMap<String, AttributeValue>();

		key_to_get.put("DATABASE_NAME", new AttributeValue(name));

		GetItemRequest request = null;
		if (projection_expression != null) {
			request = new GetItemRequest()
					.withKey(key_to_get)
					.withTableName(table_name)
					.withProjectionExpression(projection_expression);
		} else {
			request = new GetItemRequest()
					.withKey(key_to_get)
					.withTableName(table_name);
		}

		final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();

		try {
			System.out.println(ddb.getItem(request).getItem());
			Map<String,AttributeValue> returned_item =
					ddb.getItem(request).getItem();
			if (returned_item != null) {
				System.out.println(returned_item);
				Set<String> keys = returned_item.keySet();
				for (String key : keys) {
					System.out.format("%s: %s\n",
							key, returned_item.get(key).toString());
				}
			} else {
				System.out.println(returned_item);
				System.out.format("No item found with the key %s!\n", name);
			}
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			System.exit(1);
		}

		return "Error";
	}

	public static void main(String[] args) {
		SpringApplication.run(BeaconNetApplication.class, args);
	}

}

