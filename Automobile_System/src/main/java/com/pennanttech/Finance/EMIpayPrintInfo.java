package com.pennanttech.Finance;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listcell;

import com.pennanttech.Register.RegistrationDao;

public class EMIpayPrintInfo extends Div {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected RegistrationDao regdao;
	public void onCreate()
	{
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		regdao=(RegistrationDao)ctx.getBean("taskDAO");	
		Session sess= Sessions.getCurrent();
		PaymentEMI cb=(PaymentEMI)sess.getAttribute("emipay");
		
		((Listcell)this.getFellow("payment_id")).setLabel(String.valueOf(cb.getPayment_id()));
		((Listcell)this.getFellow("customer_id")).setLabel(String.valueOf(cb.getCustomerid()));
		((Listcell)this.getFellow("vehicle_id")).setLabel(String.valueOf(cb.getVehicleid()));
		((Listcell)this.getFellow("emi")).setLabel(String.valueOf(cb.getEmi()));
		((Listcell)this.getFellow("pay_date")).setLabel(String.valueOf(cb.getPaymentdate()));		
		((Listcell)this.getFellow("paid_cash")).setLabel(String.valueOf(cb.getCashpay()));
		}
	public void cancel()
	{
		Executions.sendRedirect("payment1.zul");		
	}


}
