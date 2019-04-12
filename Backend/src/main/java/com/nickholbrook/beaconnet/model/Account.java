package com.nickholbrook.beaconnet.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "beacon-net-accounts")
public class Account {

	@Pattern(regexp = Util.UUID_PATTERN, message = "TokenFormatError")
	private String accountId;
	@NotBlank(message="A account type is required")
	private String accountType;
	@NotBlank(message="A name is required")
	private String name;
	@NotBlank(message="Options is required")
	private String options;

	@DynamoDBHashKey(attributeName="accountId")
	public String getAccountId() { return accountId; }
	public void setAccountId(String accountId) { this.accountId = trimString( accountId ); }

	@DynamoDBAttribute(attributeName="name")
	public String getName() { return name; }
	public void setName(String name) { this.name = trimString( name ); }

	@DynamoDBAttribute(attributeName="accountType")
	public String getAccountType() { return accountType; }
	public void setAccountType(String accountType) { this.accountType = trimString( accountType ); }

	@DynamoDBAttribute(attributeName="options")
	public String getOptions() { return options; }
	public void setOptions(String y) { this.options = trimString( options ); }


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
		return "Beacon [accountId=" + accountId + ",name=" + name + ", options=" + options + ", accountType=" + accountType + "]";
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
		if (!(obj instanceof Account)) {
			return false;
		}
		Account other = (Account) obj;
		if (this.accountId != other.accountId) {
			return false;
		}
		return true;
	}
}