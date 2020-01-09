package com.pennant.customer;

public interface PaymentDao {
	public void paymentAdd(paymentmodel pm) throws Exception;
	public void orderInsert() throws Exception;

	public void cartdisplay();
	public void deletecart() throws Exception;
}
