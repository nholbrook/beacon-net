package com.nickholbrook.beaconnet.service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;

public class DynamoDBService extends DynamoDBAuthService {

	private static BasicAWSCredentials credentials = null;
	private static AmazonDynamoDB mClient = null;
	private static DynamoDBMapper mMapper = null;

	protected AWSCredentials getCredentials() {
		if (credentials == null) {
			credentials = new BasicAWSCredentials( ACCESS_KEY, SECRET_KEY );
		}
		return credentials;
	}

	public AmazonDynamoDB getClient() {
		if (mClient == null) {
			AWSCredentials credentials = getCredentials();
			ClientConfiguration config = new ClientConfiguration();
			config.setProtocol(Protocol.HTTP);
			mClient = AmazonDynamoDBClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
					.withClientConfiguration(config)
					.withRegion( REGION )
					.build();
		}
		return mClient;
	}

	public DynamoDBMapper getMapper( String tableName ) {
		AmazonDynamoDB client = getClient();
		DynamoDBMapper mapper = new DynamoDBMapper( client,  new TableNameOverride( tableName ).config() );
		return mapper;
	}

	public DynamoDBMapper getMapper() {
		if (mMapper == null) {
			AmazonDynamoDB client = getClient();
			mMapper = new DynamoDBMapper( client );
		}
		return mMapper;
	}



}
