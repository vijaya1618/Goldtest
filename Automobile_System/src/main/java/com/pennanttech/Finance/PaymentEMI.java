package com.pennanttech.Finance;

public class PaymentEMI {
	private int customerid;
	private int vehicleid;
	private int payment_id;
	private double emi;
	private double cashpay;
	private java.sql.Date paymentdate;
	
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
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public double getEmi() {
		return emi;
	}
	public void setEmi(double emi) {
		this.emi = emi;
	}
	public double getCashpay() {
		return cashpay;
	}
	public void setCashpay(double cashpay) {
		this.cashpay = cashpay;
	}
	public java.sql.Date getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(java.sql.Date paymentdate) {
		this.paymentdate = paymentdate;
	}	
	
	
}
