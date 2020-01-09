package com.pennanttech.emp.view;

import java.math.BigDecimal;
import java.sql.Date;

public class DownPayBean {

	private int Payment_Id;
	private int EMIindex;
	private Date Payment_st_date;
	private Date Payment_pay_date;
	private double Begining_balnce;
	private BigDecimal EMI;
	private double Principle;
	private int interest;
	private double Rem_Balance;
	private String Status;

	public int getPayment_Id() {
		return Payment_Id;
	}

	public void setPayment_Id(int payment_Id) {
		Payment_Id = payment_Id;
	}

	public int getEMIindex() {
		return EMIindex;
	}

	public void setEMIindex(int eMIindex) {
		EMIindex = eMIindex;
	}

	public Date getPayment_st_date() {
		return Payment_st_date;
	}

	public void setPayment_st_date(Date payment_st_date) {
		Payment_st_date = payment_st_date;
	}

	public Date getPayment_pay_date() {
		return Payment_pay_date;
	}

	public void setPayment_pay_date(Date payment_pay_date) {
		Payment_pay_date = payment_pay_date;
	}

	public double getBegining_balnce() {
		return Begining_balnce;
	}

	public void setBegining_balnce(double begining_balnce) {
		Begining_balnce = begining_balnce;
	}

	public BigDecimal getEMI() {
		return EMI;
	}

	public void setEMI(BigDecimal eMI) {
		EMI = eMI;
	}

	public double getPrinciple() {
		return Principle;
	}

	public void setPrinciple(double principle) {
		Principle = principle;
	}

	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	public double getRem_Balance() {
		return Rem_Balance;
	}

	public void setRem_Balance(double rem_Balance) {
		Rem_Balance = rem_Balance;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
