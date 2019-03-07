package com.nickholbrook.beaconnet.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "beacon-net")
public class EntryInfo {
	private String macAddress;
	private String lastTime;
	private String firstTime;
	private String manufacturer;

	@DynamoDBHashKey(attributeName="mac-address")
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = trimString(macAddress);
	}

	@DynamoDBRangeKey(attributeName="last-time")
	@DynamoDBIndexHashKey(attributeName="last-time", globalSecondaryIndexName="last-time-index")
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = trimString(lastTime);
	}

	@DynamoDBAttribute
	public String getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(String firstTime) {
		this.firstTime = trimString(firstTime);
	}

	@DynamoDBAttribute
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = trimString(manufacturer);
	}

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
		return "Entry [mac-address=" + macAddress + ", last-time=" + lastTime + ", first-time=" + firstTime + ", manufacturer=" + manufacturer + "]";
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
		if (!(obj instanceof EntryInfo)) {
			return false;
		}
		EntryInfo other = (EntryInfo) obj;
		if (macAddress == null) {
			if (other.macAddress != null) {
				return false;
			}
		} else if (!macAddress.equals(other.macAddress)) {
			return false;
		}
		if (lastTime == null) {
			if (other.lastTime != null) {
				return false;
			}
		} else if (!lastTime.equals(other.lastTime)) {
			return false;
		}
		if (firstTime == null) {
			if (other.firstTime != null) {
				return false;
			}
		} else if (!firstTime.equals(other.firstTime)) {
			return false;
		}
		if (manufacturer == null) {
			if (other.manufacturer != null) {
				return false;
			}
		} else if (!manufacturer.equals(other.manufacturer)) {
			return false;
		}
		return true;
	}
}