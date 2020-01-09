package com.pennanttech.CustInfo;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listcell;


import com.pennanttech.Register.RegistrationDao;

public class PrintCustomerDetailsCntrl extends Div {
	protected RegistrationDao regdao;
	public void onCreate()
	{
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		regdao=(RegistrationDao)ctx.getBean("taskDAO");	
		Session sess= Sessions.getCurrent();
		CustInfoBean cb=(CustInfoBean)sess.getAttribute("Customer_Info");
		
		((Listcell)this.getFellow("customer_id")).setLabel(String.valueOf(cb.getCustomer_id()));
		((Listcell)this.getFellow("customer_name")).setLabel(cb.getName());
		((Listcell)this.getFellow("gender")).setLabel(cb.getGender());
		((Listcell)this.getFellow("dob")).setLabel(String.valueOf(cb.getDob()));
		((Listcell)this.getFellow("mobile1")).setLabel(String.valueOf(cb.getMobileno1()));
		((Listcell)this.getFellow("mobile2")).setLabel(String.valueOf(cb.getMobileno2()));
		((Listcell)this.getFellow("profession")).setLabel(cb.getProfession());
		((Listcell)this.getFellow("income")).setLabel(String.valueOf(cb.getNetIncome()));
		((Listcell)this.getFellow("aadhar")).setLabel(String.valueOf(cb.getAadhar()));
		((Listcell)this.getFellow("pan")).setLabel(cb.getPAN());
		((Listcell)this.getFellow("address1")).setLabel(cb.getAddress1());
		((Listcell)this.getFellow("address2")).setLabel(cb.getAddress2());
		((Listcell)this.getFellow("vehicle_id")).setLabel(String.valueOf(cb.getVehicleId()));
		((Listcell)this.getFellow("vehicle_type")).setLabel(cb.getVehicleType());
		((Listcell)this.getFellow("vehicle_amount")).setLabel(String.valueOf(cb.getVehicleAmount()));
		}
	public void cancel()
	{
		Executions.sendRedirect("SalesHome.zul");		
	}

}
