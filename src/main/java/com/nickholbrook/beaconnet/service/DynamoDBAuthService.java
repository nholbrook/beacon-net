package com.nickholbrook.beaconnet.service;

import java.util.Map;

import com.amazonaws.regions.Regions;
import com.nickholbrook.beaconnet.util.LoadFromProperties;

public abstract class DynamoDBAuthService {
	final static String ACCESS_KEY;
	final static String SECRET_KEY;
	final static Regions REGION;

	static {
		Map<String, String> config = LoadFromProperties.loadFromProperties();

		ACCESS_KEY = LoadFromProperties.fetchValue(config, "aws.accessKey");
		SECRET_KEY = LoadFromProperties.fetchValue(config, "aws.secretKey");
		REGION = Regions.valueOf(LoadFromProperties.fetchValue(config, "aws.region"));
	}
}