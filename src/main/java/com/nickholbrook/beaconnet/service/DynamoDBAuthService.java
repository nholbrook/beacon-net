package com.nickholbrook.beaconnet.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.amazonaws.regions.Regions;
import com.sun.org.apache.regexp.internal.RE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public abstract class DynamoDBAuthService {
	final static String ACCESS_KEY;
	final static String SECRET_KEY;
	final static Regions REGION;

	static {
		Map<String, String> config = loadFromProperties();

		ACCESS_KEY = fetchValue(config, "AWS_ACCESS_KEY");
		SECRET_KEY = fetchValue(config, "AWS_SECRET_KEY");
		REGION = Regions.valueOf(fetchValue(config, "AWS_REGION"));
	}

	private static String fetchValue(Map<String, String> config, String name) {
		String val = config.get(name);

		return ((val != null) ? val : "");
	}

	private static Map<String, String> loadFromProperties() {
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