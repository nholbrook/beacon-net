package com.nickholbrook.beaconnet.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "beacon-net-beacons")
public class Beacon {

	@Pattern(regexp = Util.UUID_PATTERN, message = "TokenFormatError")
	private String beaconId;
	@Pattern(regexp = Util.UUID_PATTERN, message = "TokenFormatError")
	private String accountId;
	@Pattern(regexp = Util.UUID_PATTERN, message = "TokenFormatError")
	private String mapId;
	@NotBlank(message="A name is required")
	private String name;
	@NotBlank(message="A x coord is required")
	private String x;
	@NotBlank(message="A y coord is required")
	private String y;
	@NotBlank(message="A last time is required")
	private String lastTime;

	@DynamoDBHashKey(attributeName = "beaconId")
	public String getBeaconId() { return beaconId; }
	public void setBeaconId(String beaconId) { this.beaconId = trimString( beaconId ); }

	@DynamoDBAttribute(attributeName="accountId")
	public String getAccountId() { return accountId; }
	public void setAccountId(String accountId) { this.accountId = trimString( accountId ); }

	@DynamoDBAttribute(attributeName="mapId")
	public String getMapId() { return mapId; }
	public void setMapId(String mapId) { this.mapId = trimString( mapId ); }

	@DynamoDBAttribute(attributeName="name")
	public String getName() { return name; }
	public void setName(String name) { this.name = trimString( name ); }

	@DynamoDBAttribute(attributeName="x")
	public String getX() { return x; }
	public void setX(String x) { this.x = trimString( x ); }

	@DynamoDBAttribute(attributeName="y")
	public String getY() { return y; }
	public void setY(String y) { this.y = trimString( y ); }

	@DynamoDBRangeKey(attributeName="lastTime")
	public String getLastTime() { return lastTime; }
	public void setLastTime(String lastTime) { this.lastTime = trimString( lastTime ); }

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
		return "Beacon [beaconId=" + beaconId + ", accountId=" + accountId + ", mapId=" + mapId + ", name=" + name + ", x=" + x + ", y=" + y + ", lastTime=" + lastTime + "]";
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
		if (!(obj instanceof Beacon)) {
			return false;
		}
		Beacon other = (Beacon) obj;
		if (this.beaconId != other.beaconId) {
			return false;
		}
		return true;
	}
}