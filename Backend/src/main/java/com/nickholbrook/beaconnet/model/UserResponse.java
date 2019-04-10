package com.nickholbrook.beaconnet.model;

import java.util.Date;

public class UserResponse {

	private String username;
	private String companyName;
	private String userStatus;
	private Date userCreateDate;
	private Date lastModifiedDate;
	private String companyPosition;
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getCompanyPosition() {
		return companyPosition;
	}

	public void setCompanyPosition(String companyPosition) {
		this.companyPosition = companyPosition;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getUserCreateDate() {
		return userCreateDate;
	}

	public void setUserCreateDate(Date userCreateDate) {
		this.userCreateDate = userCreateDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String toString() {
		return "UserResponse{" +
				"username='" + username + '\'' +
				", companyName='" + companyName + '\'' +
				", userStatus='" + userStatus + '\'' +
				", userCreateDate=" + userCreateDate +
				", lastModifiedDate=" + lastModifiedDate +
				", companyPosition='" + companyPosition + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
