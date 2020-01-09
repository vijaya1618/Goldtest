package com.pennanttech.Payments;

import java.sql.Date;

public class PaymentBean {

	private int Payment_Id;
	private String Payment_Type;
	private int Customer_Id;
	private int Vehicle_Id;
	private long Vehicle_Amount;
	private int Loan_Tenure;
	private int RateOfInterest;
	private long Paid_Amount;
	private long Principle_Amount;
	private Date PaymentDate;
	private long EMI_Money;

	public int getPayment_Id() {
		return Payment_Id;
	}

	public void setPayment_Id(int payment_Id) {
		Payment_Id = payment_Id;
	}

	public String getPayment_Type() {
		return Payment_Type;
	}

	public void setPayment_Type(String payment_Type) {
		Payment_Type = payment_Type;
	}

	public int getCustomer_Id() {
		return Customer_Id;
	}

	public void setCustomer_Id(int customer_Id) {
		Customer_Id = customer_Id;
	}

	public int getVehicle_Id() {
		return Vehicle_Id;
	}

	public void setVehicle_Id(int vehicle_Id) {
		Vehicle_Id = vehicle_Id;
	}

	public long getVehicle_Amount() {
		return Vehicle_Amount;
	}

	public void setVehicle_Amount(long vehicle_Amount) {
		Vehicle_Amount = vehicle_Amount;
	}

	public int getLoan_Tenure() {
		return Loan_Tenure;
	}

	public void setLoan_Tenure(int loan_Tenure) {
		Loan_Tenure = loan_Tenure;
	}

	public int getRateOfInterest() {
		return RateOfInterest;
	}

	public void setRateOfInterest(int rateOfInterest) {
		RateOfInterest = rateOfInterest;
	}

	public long getPaid_Amount() {
		return Paid_Amount;
	}

	public void setPaid_Amount(long paid_Amount) {
		Paid_Amount = paid_Amount;
	}

	public long getPrinciple_Amount() {
		return Principle_Amount;
	}

	public void setPrinciple_Amount(long principle_Amount) {
		Principle_Amount = principle_Amount;
	}

	public Date getPaymentDate() {
		return PaymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		PaymentDate = paymentDate;
	}

	public long getEMI_Money() {
		return EMI_Money;
	}

	public void setEMI_Money(long eMI_Money) {
		EMI_Money = eMI_Money;
	}

}
