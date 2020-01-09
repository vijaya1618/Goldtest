package com.pennanttech.CustInfo;

import java.sql.Date;

public class CustInfoBean {
	private long customer_id;
	private String name;
	private String gender;
	private long mobileno1;
	private long mobileno2;
	private Date dob;
	private String profession;
	private long NetIncome;
	private long Aadhar;
	private String PAN;
	private String Address1;
	private String Address2;
	private long VehicleId;
	private String VehicleType;
	private long VehicleAmount;
	private String status;
	
	
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getMobileno1() {
		return mobileno1;
	}
	public void setMobileno1(long mobileno1) {
		this.mobileno1 = mobileno1;
	}
	public long getMobileno2() {
		return mobileno2;
	}
	public void setMobileno2(long mobileno2) {
		this.mobileno2 = mobileno2;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public long getNetIncome() {
		return NetIncome;
	}
	public void setNetIncome(long netIncome) {
		NetIncome = netIncome;
	}
	public long getAadhar() {
		return Aadhar;
	}
	public void setAadhar(long aadhar) {
		Aadhar = aadhar;
	}
	public String getPAN() {
		return PAN;
	}
	public void setPAN(String pAN) {
		PAN = pAN;
	}
	public String getAddress1() {
		return Address1;
	}
	public void setAddress1(String address1) {
		Address1 = address1;
	}
	public String getAddress2() {
		return Address2;
	}
	public void setAddress2(String address2) {
		Address2 = address2;
	}
	public long getVehicleId() {
		return VehicleId;
	}
	public void setVehicleId(long vehicleId) {
		VehicleId = vehicleId;
	}
	public String getVehicleType() {
		return VehicleType;
	}
	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}
	public long getVehicleAmount() {
		return VehicleAmount;
	}
	public void setVehicleAmount(long vehicleAmount) {
		VehicleAmount = vehicleAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
