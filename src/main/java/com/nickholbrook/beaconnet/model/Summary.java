package com.nickholbrook.beaconnet.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "beacon-net-entries-summary")
public class Summary {

	@Pattern(regexp = Util.UUID_PATTERN, message = "TokenFormatError")
	private String summaryId;
	@NotBlank(message="A summary time is required")
	private String summaryTime;
	@Pattern(regexp = Util.UUID_PATTERN, message = "TokenFormatError")
	private String accountId;
	@Pattern(regexp = Util.UUID_PATTERN, message = "TokenFormatError")
	private String beaconId;
	@NotBlank(message="A count is required")
	private String count;
	@NotBlank(message="A y coord is required")
	private String x;
	@NotBlank(message="A x coord is required")
	private String y;

	@DynamoDBHashKey(attributeName = "summaryId")
	public String getSummaryId() { return summaryId; }
	public void setSummaryId(String summaryId) { this.summaryId = trimString( summaryId ); }

	@DynamoDBRangeKey(attributeName="summaryTime")
	public String getSummaryTime() { return summaryTime; }
	public void setSummaryTime(String summaryTime) { this.summaryTime = trimString( summaryTime ); }

	@DynamoDBAttribute(attributeName="accountId")
	@DynamoDBIndexHashKey(attributeName="accountId", globalSecondaryIndexName="accountId-index")
	public String getAccountId() { return accountId; }
	public void setAccountId(String accountId) { this.accountId = trimString( accountId ); }

	@DynamoDBAttribute(attributeName="beaconId")
	public String getBeaconId() { return beaconId; }
	public void setBeaconId(String beaconId) { this.beaconId = trimString( beaconId ); }

	@DynamoDBAttribute(attributeName="count")
	public String getCount() { return count; }
	public void setCount(String count) { this.count = count; }

	@DynamoDBAttribute(attributeName="x")
	public String getX() { return x; }
	public void setX(String x) { this.x = x; }

	@DynamoDBAttribute(attributeName="y")
	public String getY() { return y; }
	public void setY(String y) { this.y = y; }


	@DynamoDBIgnore
	private String trimString(String input) {
		String rslt = "";
		if (input != null && input.length() > 0) {
			rslt = input.trim();
		}
		return rslt;
	}

	@Override
	@DynamoDBIgnore
	public String toString() {
		return "Summary [summaryId=" + summaryId + ", summaryTime=" + summaryTime + ", accountId=" + accountId + ", beaconId=" + beaconId + ", count=" + count + ", x=" + x + ", y=" + y +  "]";
	}

	@Override
	@DynamoDBIgnore
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Summary)) {
			return false;
		}
		Summary other = (Summary) obj;
		if (this.summaryId != other.summaryId) {
			return false;
		}
		return true;
	}
}