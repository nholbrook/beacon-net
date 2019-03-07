package com.nickholbrook.beaconnet.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.nickholbrook.beaconnet.dynamodb.DynamoDBUtil;
import com.nickholbrook.beaconnet.dynamodb.IDynamoDBKeys;
import com.nickholbrook.beaconnet.model.EntryInfo;
import com.nickholbrook.beaconnet.model.EntryInfoComparator;

public class EntryService implements IDynamoDBKeys {
	/** The name of the DynamoDB table used to store the book information */
	private final String tableName;
	private static DynamoDBService dynamoDBService = null;
	final private Logger log;

	private String getTableName() {
		return tableName;
	}

	/**
	 *
	 * @param entryTableName the name of the DynamoDB table that stores the book information.
	 */
	public EntryService(final String entryTableName ) {
		tableName = entryTableName;
		log = Logger.getLogger( this.getClass().getName() );
		if (dynamoDBService == null) {
			dynamoDBService = new DynamoDBService( region, full_dynamodb_access_ID, IDynamoDBKeys.full_dynamodb_access_KEY);
		}
	}

	/**
	 * A query on mac-address and last-time (note the withHashKeyValues clause).
	 * @param entryInfo
	 * @return
	 */
	protected DynamoDBQueryExpression<EntryInfo> buildEntryInfoAuthorEQQuery(EntryInfo entryInfo) {
		Condition rangeCondition = new Condition();
		rangeCondition.setComparisonOperator("EQ");
		AttributeValue attribute = new AttributeValue( entryInfo.getLastTime() );
		ArrayList<AttributeValue> attrs = new ArrayList<AttributeValue>();
		attrs.add( attribute );
		rangeCondition.setAttributeValueList( attrs );
		DynamoDBQueryExpression<EntryInfo> queryExpression = new DynamoDBQueryExpression<EntryInfo>()
				.withHashKeyValues(entryInfo)
				.withRangeKeyCondition("author", rangeCondition);
		return queryExpression;
	}


	/**
	 * Write a EntryInfo object to a book information table, but override the table
	 * name. This is used for testing, so that test data can be written to a temporary table.
	 *
	 * @param info the book table information
	 * @param tableName the name of the DynamoDbTable
	 */
	public void writeToEntryTable(EntryInfo info, String tableName ) {
		if (info != null) {
			DynamoDBMapper mapper = dynamoDBService.getMapper();
			mapper.save( info, new TableNameOverride( tableName ).config());
		}
	}


	/**
	 * <p>
	 * Write a EntryInfo object to DynamoDB. Note that if the object with the title and author already
	 * exists, it will be overwritten.
	 * </p>
	 *
	 * @param info
	 */
	public void writeToEntryTable(EntryInfo info) {
		if (info != null) {
			DynamoDBMapper mapper = dynamoDBService.getMapper();
			mapper.save( info );
		}
	}

	public boolean hasEntry(EntryInfo entryInfo, String tableName ) {
		boolean foundBook = false;
		DynamoDBQueryExpression<EntryInfo> queryExpression = buildEntryInfoAuthorEQQuery( entryInfo );
		List<EntryInfo> itemList = dynamoDBService.getMapper(tableName).query(EntryInfo.class, queryExpression);
		if (itemList != null && itemList.size() > 0) {
			foundBook = true;
		}
		return foundBook;
	}

	/**
	 * <p>
	 * Find one or more DynamoDB table EntryInfo entries by searching for the author.
	 * </p>
	 * <p>
	 * The author attribute is a global secondary index. This query is designed to return values
	 * on the basis of this secondary index (the main index is on the {title, author} pair).
	 * </p>
	 * @param author
	 * @param tableName
	 * @return
	 */
	public List<EntryInfo> findBookByAuthor( String author, String tableName ) {
		List<EntryInfo> bookList = new ArrayList<EntryInfo>();
		AmazonDynamoDB client = dynamoDBService.getClient();

		Condition hashKeyCondition = new Condition();
		Condition authorCondition = new Condition();
		authorCondition.setComparisonOperator("EQ");
		hashKeyCondition.withComparisonOperator( ComparisonOperator.EQ )
				.withAttributeValueList(new AttributeValue().withS(author));

		Map<String, Condition> keyConditions = new HashMap<String, Condition>();
		//keyConditions.put(CreateBookTable.AUTHOR_HASH_NAME, hashKeyCondition);

		QueryRequest queryRequest = new QueryRequest();
		queryRequest.withTableName( tableName );
		//queryRequest.withIndexName(CreateBookTable.AUTHOR_INDEX_NAME);
		queryRequest.withKeyConditions(keyConditions);

		QueryResult result = client.query(queryRequest);
		if (result.getCount() > 0) {
			// The itemList is a set of one or more DynamoDB row values stored in a attribute name/value map.
			List<Map<String, AttributeValue>> itemList = result.getItems();
			try {
				for (Map<String, AttributeValue> item : itemList) {
					EntryInfo info = new EntryInfo();
					DynamoDBUtil.attributesToObject(info, item);
					bookList.add(info);
				}
			}
			catch (ReflectiveOperationException e) {
				log.severe("findBookByAuthor: " + e.getLocalizedMessage());
			}
		}
		return bookList;
	}


	/**
	 * Find all the books by a particular author. This query uses the global secondary author index.
	 * @param author the name of the author to be searched for.
	 * @return A list of zero or more books.
	 */
	public List<EntryInfo> findBookByAuthor( String author ) {
		List<EntryInfo> bookList = findBookByAuthor(author, getTableName() );
		return bookList;
	}


	public List<EntryInfo> findBookByTitleAuthor(String lastTime, String macAddress) {
		EntryInfo searchObj = new EntryInfo();
		searchObj.setLastTime(lastTime);
		searchObj.setMacAddress(macAddress);
		DynamoDBQueryExpression<EntryInfo> queryExp = buildEntryInfoAuthorEQQuery( searchObj );
		List<EntryInfo> info = dynamoDBService.getMapper(tableName).query(EntryInfo.class, queryExp );
		return info;
	}

	/**
	 * <p>
	 * Find one or more books by title or partial title. For example, if there is a book titled "The Peripheral",
	 * searching for "Peripheral" will return the book. Similarly, if there are two books, "Bangkok 8" and "Bangkok Haunts"
	 * searching for "Bangkok" will return both books.
	 * </p>
	 * <p>
	 * This query performs a table scan.
	 * </p>
	 *
	 * @param titleWords a string to search for in the book titles.
	 * @return
	 */
	public List<EntryInfo> findBookByTitle(String titleWords ) {
		List<EntryInfo> bookList = new ArrayList<EntryInfo>();
		Map<String, AttributeValue> expressionAttributeValues = new HashMap<String, AttributeValue>();
		Condition containsCondition = new Condition()
				.withComparisonOperator(ComparisonOperator.CONTAINS.toString())
				.withAttributeValueList(new AttributeValue().withS( titleWords ));
		Map<String, Condition> keyConditions = new HashMap<String, Condition>();
		keyConditions.put("title", containsCondition);

		ScanRequest scanRequest = new ScanRequest()
				.withTableName( getTableName() )
				.withScanFilter(keyConditions);

		AmazonDynamoDB client = dynamoDBService.getClient();
		ScanResult result = client.scan(scanRequest);
		if (result.getCount() > 0) {
			// The itemList is a set of one or more DynamoDB row values stored in a attribute name/value map.
			List<Map<String, AttributeValue>> itemList = result.getItems();
			try {
				for (Map<String, AttributeValue> item : itemList) {
					EntryInfo info = new EntryInfo();
					DynamoDBUtil.attributesToObject(info, item);
					bookList.add(info);
				}
			}
			catch (ReflectiveOperationException e) {
				log.severe("findBookByTitle: " + e.getLocalizedMessage());
			}
		}
		return bookList;
	}

	/**
	 * Read the entire book database. This function does a scan, which can be expensive on DynamoDB. However,
	 * the assumption here is that the size of the book database is in the thousands, not millions (e.g., it's
	 * not the Library of Congress).
	 *
	 * @return
	 */
	public List<EntryInfo> getBooks() {
		AmazonDynamoDB client = dynamoDBService.getClient();
		ScanRequest scanRequest = new ScanRequest().withTableName( getTableName() );
		ScanResult result = client.scan(scanRequest);
		List<EntryInfo> bookList = new ArrayList<EntryInfo>();
		if (result.getCount() > 0) {
			// The itemList is a set of one or more DynamoDB row values stored in a attribute name/value map.
			List<Map<String, AttributeValue>> itemList = result.getItems();
			try {
				for (Map<String, AttributeValue> item : itemList) {
					EntryInfo info = new EntryInfo();
					DynamoDBUtil.attributesToObject(info, item);
					bookList.add(info);
				}
				if (bookList.size() > 1) {
					EntryInfo[] infoArray = bookList.toArray( new EntryInfo[1] );
					Arrays.sort(infoArray, new EntryInfoComparator() );
					bookList.clear();
					bookList.addAll(Arrays.asList( infoArray ) );
				}
			}
			catch (ReflectiveOperationException e) {
				log.severe("getBooks: " + e.getLocalizedMessage());
			}
		}
		return bookList;
	}

	/*

	private static Map<String, Entry> entrySet = new HashMap<String, Entry>();

	public ResponseEntity<Object> deleteEntry(@PathVariable("id") String id) {
		if(!entrySet.containsKey(id))throw new EntryNotfoundException();
		entrySet.remove(id);
		return new ResponseEntity<>("Entity is deleted successfully", HttpStatus.OK);
	}

	public ResponseEntity<Object> updateEntry(@PathVariable("id") String id, @RequestBody Entry entry) {
		if(!entrySet.containsKey(id))throw new EntryNotfoundException();
		entrySet.remove(id);
		entry.setMacAddress(id);
		entrySet.put(id, entry);
		return new ResponseEntity<>("Entity is updated successfully", HttpStatus.OK);
	}

	public ResponseEntity<Object> createEntry(@RequestBody Entry entry) {
		entrySet.put(entry.getMacAddress(), entry);
		return new ResponseEntity<>("Entity is created successfully", HttpStatus.CREATED);
	}

	public ResponseEntity<Object> getEntry(@PathVariable("id") String id) {
		return new ResponseEntity<>(entrySet.getOrDefault(id, null), HttpStatus.OK);
	}

	public ResponseEntity<Object> getAllEntries() {
		return new ResponseEntity<>(entrySet.values(), HttpStatus.OK);
	}

	*/
}
