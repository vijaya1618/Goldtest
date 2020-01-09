package com.pennant.customer;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zhtml.Div;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class paymentcontrol extends Div {

	PaymentDao oi;
	private Component payment;
	// private String z;
	private Session sess = null;

	public void onCreate() {

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		oi = (PaymentDao) ctx.getBean("payDAO");
		
		Label p = (Label) this.getFellow("totprice");
		sess = Sessions.getCurrent();
		int totalprice = (Integer) sess.getAttribute("grandtotal");
		p.setValue(String.valueOf(totalprice));
		
	}

	public void same() {
		Textbox f = (Textbox) this.getFellow("deladdress");
		String dela = f.getValue();

		Textbox b = (Textbox) this.getFellow("billaddress");
		b.setValue(dela);
	}
	public void check() {
		Textbox f = (Textbox) this.getFellow("deladdress");
		String dela = f.getValue();

		Textbox b = (Textbox) this.getFellow("billaddress");
		String bill = b.getValue();
		if(dela.equals(bill))
		{
			Radio r=(Radio)this.getFellow("ra4");
			r.setVisible(true);
		}
		else
		{
			Radio r=(Radio)this.getFellow("ra4");
			r.setVisible(false);
		}
	}

	public void on() {

		Textbox b = (Textbox) this.getFellow("billaddress");
		b.setValue("");
		Radio r=(Radio)this.getFellow("ra4");
		r.setVisible(false);

	}

	public void payAdd() {
		Textbox f = (Textbox) this.getFellow("deladdress");
		String dela = f.getValue();
		Textbox b = (Textbox) this.getFellow("billaddress");
		String t = b.getValue();
		Label ch = (Label) this.getFellow("choice");
		String choice = ch.getValue();
		/* int totalprice = (Integer) sess.getAttribute("grandtotal"); */
		Label p = (Label) this.getFellow("totprice");
		String m=p.getValue();
		
		  paymentmodel pm=new paymentmodel();
		  try {
			  	pm.setDeladdress(dela);
			  	pm.setBilladdress(t); 
			  	pm.setPaytype(choice);
			  	pm.setTotprice(Integer.parseInt(m));
			  	
			  	oi.orderInsert();
			  	oi.cartdisplay();
			  	oi.paymentAdd(pm);
			  	oi.deletecart();
			  	confirm();
			  }
		  catch(Exception ex) 
		  { 
			  System.out.println(ex); 
		  }
		 
	}
	
	private void confirm() {
		// TODO Auto-generated method stub
		Executions.sendRedirect("/customer/Confirmation.zul");
		
	}

	public void paymentOnCard() {
		Window win = (Window)Executions.createComponents("Card.zul", null, null);
		win.doModal();
		win.setTitle("payment");
		win.setClosable(true);
	}
	public void paymentOnGpay() {
		Window win = (Window)Executions.createComponents("Gpay.zul", null, null);
		win.doModal();
		win.setTitle("payment");
		win.setClosable(true);
	}
	public void paymentOnPhonePe() {
		Window win = (Window)Executions.createComponents("PhonePe.zul", null, null);
		win.doModal();
		win.setTitle("payment");
		win.setClosable(true);
	}
	
	
}
