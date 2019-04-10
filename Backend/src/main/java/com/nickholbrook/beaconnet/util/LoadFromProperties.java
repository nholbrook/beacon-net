package com.nickholbrook.beaconnet.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class LoadFromProperties {
	public static String fetchValue(Map<String, String> config, String name) {
		String val = config.get(name);

		return ((val != null) ? val : "");
	}

	public static Map<String, String> loadFromProperties() {
		Properties properties = new Properties();
		InputStream input = null;

		try {
			input = com.nickholbrook.beaconnet.dynamodb.DynamoDBUtil.class.getResourceAsStream("/application.properties");
			properties.load(input);
		} catch (IOException e) {
			throw new RuntimeException("can not load configuration file", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					throw new RuntimeException("error occurs will closing input stream: ", e);
				}
			}
		}

		Set<Entry<Object, Object>> set = properties.entrySet();
		Map<String, String> mapFromSet = new HashMap<String, String>();

		for (Map.Entry<Object, Object> entry : set) {
			mapFromSet.put((String) entry.getKey(), (String) entry.getValue());
		}

		return mapFromSet;
	}
}
