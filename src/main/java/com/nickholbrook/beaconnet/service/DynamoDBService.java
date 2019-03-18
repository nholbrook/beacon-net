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

public class DynamoDBService {
	private String AWS_ID = null;
	private String AWS_KEY = null;
	private Regions region = null;
	private static BasicAWSCredentials credentials = null;
	private static AmazonDynamoDB mClient = null;
	private static DynamoDBMapper mMapper = null;

	public DynamoDBService(Regions region, String AWS_ID, String AWS_KEY) {
		setRegion( region );
		setAWS_ID( AWS_ID );
		setAWS_KEY( AWS_KEY );
	}

	public String getAWS_ID() {
		return AWS_ID;
	}

	public void setAWS_ID(String AWS_ID) {
		this.AWS_ID = AWS_ID;
	}

	public String getAWS_KEY() {
		return AWS_KEY;
	}

	public void setAWS_KEY(String AWS_KEY) {
		this.AWS_KEY = AWS_KEY;
	}

	public Regions getRegion() {
		return region;
	}

	public void setRegion(Regions region) {
		this.region = region;
	}

	protected AWSCredentials getCredentials() {
		if (credentials == null) {
			credentials = new BasicAWSCredentials( getAWS_ID(), getAWS_KEY() );
		}
		return credentials;
	}

	/**
	 * <p>
	 * Get a DynamoDB client. If the client does not exist, allocate the client. The client that is returned
	 * will be initialized with the credentials and region from the class constructor.
	 * </p>
	 *
	 * @return the thread safe, static, AmazonDynamoDBClient
	 */
	public AmazonDynamoDB getClient() {
		if (mClient == null) {
			AWSCredentials credentials = getCredentials();
			ClientConfiguration config = new ClientConfiguration();
			config.setProtocol(Protocol.HTTP);
			mClient = AmazonDynamoDBClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
					.withClientConfiguration(config)
					.withRegion(getRegion())
					.build();
		}
		return mClient;
	}


	/**
	 * Build a mapper for a specific table.  Note that this method does not set the mMapper class variable.
	 *
	 * @param tableName
	 * @return
	 */
	public DynamoDBMapper getMapper( String tableName ) {
		AmazonDynamoDB client = getClient();
		DynamoDBMapper mapper = new DynamoDBMapper( client,  new TableNameOverride( tableName ).config() );
		return mapper;
	}

	/**
	 *
	 * @return a DynamoDBMapper object, initialized with a static instance AmazonDynamoDB
	 */
	public DynamoDBMapper getMapper() {
		if (mMapper == null) {
			AmazonDynamoDB client = getClient();
			mMapper = new DynamoDBMapper( client );
		}
		return mMapper;
	}
}