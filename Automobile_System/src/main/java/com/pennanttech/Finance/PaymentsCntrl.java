package com.pennanttech.Finance;


import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Div;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Doublebox;

import com.pennanttech.Register.RegistrationDao;

import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;


public class PaymentsCntrl extends Div {
	
	private static final long serialVersionUID = 1L;
	protected RegistrationDao regdao;
	

	
	public void vehicleid() throws Exception 
    {	
	    ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)(getDesktop()).getWebApp().getNativeContext());
	    regdao=(RegistrationDao)ctx.getBean("taskDAO");	
	    
		PaymentEMI lb=new PaymentEMI();
		lb.setCustomerid(((Intbox)this.getFellow("customer_id")).getValue());

		PaymentEMI lb1=regdao.findById(lb);

		
		((Intbox)this.getFellow("vehicle_id")).setValue(lb1.getVehicleid());
		((Intbox)this.getFellow("Payment_id")).setValue(lb1.getPayment_id());
		((Doublebox)this.getFellow("emi")).setValue(lb1.getEmi());

	}
	public void payEMI()
	{
	    ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)(getDesktop()).getWebApp().getNativeContext());
	    regdao=(RegistrationDao)ctx.getBean("taskDAO");	
	    
	    PaymentEMI lb=new PaymentEMI();
	    lb.setCustomerid(((Intbox)this.getFellow("customer_id")).getValue());
	    lb.setVehicleid(((Intbox)this.getFellow("vehicle_id")).getValue());
	    lb.setPayment_id(((Intbox)this.getFellow("Payment_id")).getValue());
	    lb.setEmi(((Doublebox)this.getFellow("emi")).getValue());
	    lb.setCashpay(((Doublebox)this.getFellow("cash_pay")).getValue());
	    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		lb.setPaymentdate(sqlDate);
	    regdao.PayEMI(lb);
	    Session sess= Sessions.getCurrent();
		sess.setAttribute("emipay",lb);
		if(true)
		{
			Executions.sendRedirect("EmipaySuccess.zul");
		}
	}
}
