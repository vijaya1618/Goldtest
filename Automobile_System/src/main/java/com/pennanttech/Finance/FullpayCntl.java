package com.pennanttech.Finance;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import com.pennanttech.Register.RegistrationDao;

import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Decimalbox;

public class FullpayCntl extends Div{
private static final long serialVersionUID = 1L;
	
	protected RegistrationDao regdao;
	
	
	
	private Component fullpay;
	public void fullpayment()
	{
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		regdao=(RegistrationDao)ctx.getBean("taskDAO");	
		try{
		FullpayBean fpb=new FullpayBean();
		fpb.setPayment_type("Full Payment");		
			fpb.setCustomerid(((Intbox)this.getFellow("CustomerID")).getValue());
			fpb.setVehicleid(((Intbox)this.getFellow("VehicleID")).getValue());
		fpb.setPrinciple_amount(0);
			fpb.setVehiclecost(((Doublebox)this.getFellow("VehicleCost")).getValue());
		fpb.setTenure(0);
		fpb.setRoi(0);
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		fpb.setPaymentdate(sqlDate);
		fpb.setEMI(BigDecimal.valueOf(0));
			fpb.setPaidamount(((Doublebox)this.getFellow("PaidAmount")).getValue());
		FullpayBean res=regdao.fullPay(fpb);
		Session sess=Sessions.getCurrent();
		sess.setAttribute("fullpay", res);
		if(true) {
			Executions.sendRedirect("FullpaySuccess.zul");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void downpayment()
	{
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		regdao=(RegistrationDao)ctx.getBean("taskDAO");	
		try{
		FullpayBean fpb=new FullpayBean();
			fpb.setPayment_type("Down Payment");		
			fpb.setCustomerid(((Intbox)this.getFellow("CustomerID")).getValue());
			fpb.setVehicleid(((Intbox)this.getFellow("VehicleID")).getValue());
			fpb.setVehiclecost(((Doublebox)this.getFellow("VehicleCost")).getValue());
			fpb.setTenure(((Intbox)this.getFellow("tenure")).getValue());
			fpb.setRoi(((Intbox)this.getFellow("Rate_of_interest")).getValue());
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			fpb.setPaymentdate(sqlDate);
			fpb.setEMI(((Decimalbox)this.getFellow("EMI")).getValue());
			fpb.setPaidamount(((Doublebox)this.getFellow("payingamount")).getValue());
			fpb.setPrinciple_amount(((Doublebox)this.getFellow("remainingamount")).getValue());
			FullpayBean res=regdao.downPay(fpb);
			Session sess=Sessions.getCurrent();
			sess.setAttribute("downpay", res);
			if(true)
			{
				Executions.sendRedirect("DownpaySuccess.zul");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void rem_amt()
	{
		double vc=((Doublebox)this.getFellow("VehicleCost")).getValue();
		double pa=((Doublebox)this.getFellow("payingamount")).getValue();
		double rm=vc-pa;
		((Doublebox)this.getFellow("remainingamount")).setValue(rm);
	}
	public void emicalc()
	{
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		regdao=(RegistrationDao)ctx.getBean("taskDAO");	
		FullpayBean fpb=new FullpayBean();
		fpb.setPrinciple_amount(((Doublebox)this.getFellow("remainingamount")).getValue());
		fpb.setTenure(((Intbox)this.getFellow("tenure")).getValue());
		fpb.setRoi(((Intbox)this.getFellow("Rate_of_interest")).getValue());
		
		BigDecimal emi=regdao.emical(fpb);
		((Decimalbox)this.getFellow("EMI")).setValue(emi);
	}

	public void vehicleDetails()
	{
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		regdao=(RegistrationDao)ctx.getBean("taskDAO");
		FullpayBean fb=new FullpayBean();
		fb.setCustomerid(((Intbox)this.getFellow("CustomerID")).getValue());
		FullpayBean fb1=regdao.VehicleDetails(fb);
		((Intbox)this.getFellow("VehicleID")).setValue(fb1.getVehicleid());
		//((Textbox)this.getFellow("vehicletype")).setValue(fb1.getVehicleType());
		((Doublebox)this.getFellow("VehicleCost")).setValue(fb1.getVehiclecost());
	}
}
