package com.pennanttech.Paymentmethods;
import org.zkoss.zul.Include;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.*;
public class PaymentCntl extends SelectorComposer<Component> {
	@Wire
	public Include payments;

	@Listen("onClick=#downpayment")
	public void downpayment()
	{
		payments.setSrc("/Paymentmethods/downpayment.zul");
	}
	@Listen("onClick=#fullpayment")
	public void fullpayment()
	{
		payments.setSrc("/Paymentmethods/fullpayment.zul");
	}
	
	@Listen("onClick=#emipayment")
	public void emipayment()
	{
		payments.setSrc("/Paymentmethods/EmiPayments.zul");
	}
	
	@Listen("onClick=#viewpayment")
	public void viewpayment()
	{
		payments.setSrc("/Paymentmethods/viewpayment.zul");
	}
	
	@Listen("onClick=#viewschedule")
	public void viewschedule()
	{
		payments.setSrc("/Paymentmethods/ViewSchedule.zul");
	}
	

}
