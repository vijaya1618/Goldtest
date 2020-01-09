package com.pennant.customer;

public class paymentmodel {
	private String deladdress;
	private String Billaddress;
	private String paytype;
	private int totprice;
	public String getDeladdress() {
		return deladdress;
	}
	public void setDeladdress(String deladdress) {
		this.deladdress = deladdress;
	}
	
	public String getBilladdress() {
		return Billaddress;
	}
	public void setBilladdress(String billaddress) {
		Billaddress = billaddress;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public int getTotprice() {
		return totprice;
	}
	public void setTotprice(int totprice) {
		this.totprice = totprice;
	}
}
