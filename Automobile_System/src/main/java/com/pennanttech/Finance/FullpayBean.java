package com.pennanttech.Finance;

import java.math.BigDecimal;

public class FullpayBean {
	
	private long payment_id;
	private String Payment_type;
	private int customerid;	
	private int vehicleid;
	private String vehicleType;
	private double principle_amount;
	private double vehiclecost;
	private int tenure;
	private int roi;
	private java.sql.Date paymentdate;
	private BigDecimal EMI;
	private double paidamount;
	
	
	
	public long getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(long payment_id) {
		this.payment_id = payment_id;
	}
	public String getPayment_type() {
		return Payment_type;
	}
	public void setPayment_type(String payment_type) {
		Payment_type = payment_type;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public double getPrinciple_amount() {
		return principle_amount;
	}
	public void setPrinciple_amount(double principle_amount) {
		this.principle_amount = principle_amount;
	}
	public double getVehiclecost() {
		return vehiclecost;
	}
	public void setVehiclecost(double vehiclecost) {
		this.vehiclecost = vehiclecost;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public int getRoi() {
		return roi;
	}
	public void setRoi(int roi) {
		this.roi = roi;
	}
	public java.sql.Date getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(java.sql.Date paymentdate) {
		this.paymentdate = paymentdate;
	}
	public BigDecimal getEMI() {
		return EMI;
	}
	public void setEMI(BigDecimal eMI) {
		EMI = eMI;
	}
	public double getPaidamount() {
		return paidamount;
	}
	public void setPaidamount(double paidamount) {
		this.paidamount = paidamount;
	}
	
	
	
}
