package com.nickholbrook.beaconnet.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "beacon-net-entries")
public class Entry {

	@Pattern(regexp = Util.UUID_PATTERN, message = "TokenFormatError")
	private String entryId;
	@NotBlank(message="A last time is required")
	private String lastTime;
	@NotBlank(message="A mac address title is required")
	private String macAddress;
	@NotBlank(message="A first time is required")
	private String firstTime;
	private String manufacturer;
	@Pattern(regexp = Util.UUID_PATTERN, message = "TokenFormatError")
	private String accountId;
	@Pattern(regexp = Util.UUID_PATTERN, message = "TokenFormatError")
	private String beaconId;

	@DynamoDBHashKey(attributeName = "entryId")
	public String getEntryId() { return entryId; }
	public void setEntryId(String entryId) { this.entryId = trimString( entryId ); }

	@DynamoDBRangeKey(attributeName="lastTime")
	//@DynamoDBIndexHashKey(attributeName="last-time", globalSecondaryIndexName="last-time-index")
	public String getLastTime() { return lastTime; }
	public void setLastTime(String lastTime) { this.lastTime = trimString( lastTime ); }

	@DynamoDBAttribute(attributeName="macAddress")
	public String getMacAddress() { return macAddress; }
	public void setMacAddress(String macAddress) { this.macAddress = trimString( macAddress ); }

	@DynamoDBAttribute(attributeName="firstTime")
	public String getFirstTime() { return firstTime; }
	public void setFirstTime(String firstTime) { this.firstTime = trimString( firstTime ); }

	@DynamoDBAttribute(attributeName="manufacturer")
	public String getManufacturer() { return manufacturer; }
	public void setManufacturer(String manufacturer) { this.manufacturer = trimString( manufacturer ); }

	@DynamoDBAttribute(attributeName="accountId")
	public String getAccountId() { return accountId; }
	public void setAccountId(String accountId) { this.accountId = trimString( accountId ); }

	@DynamoDBAttribute(attributeName="beaconId")
	public String getBeaconId() { return beaconId; }
	public void setBeaconId(String beaconId) { this.beaconId = trimString( beaconId ); }

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
		return "Entry [entryId=" + entryId + ", lastTime=" + lastTime + ", macAddress=" + macAddress + ", firstTime=" + firstTime + ", manufacturer=" + manufacturer + ", accountId=" + accountId + ", beaconId=" + beaconId + "]";
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
		if (!(obj instanceof Entry)) {
			return false;
		}
		Entry other = (Entry) obj;
		if (this.macAddress != other.macAddress) {
			return false;
		}
		return true;
	}
}