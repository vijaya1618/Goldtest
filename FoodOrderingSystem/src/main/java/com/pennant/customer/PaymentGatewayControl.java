package com.pennant.customer;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Label;
import org.zkoss.zul.*;

public class PaymentGatewayControl extends Window{
	private Session sess = null;
	public void onOKCard() throws Exception {
		this.setAttribute("OK", Boolean.TRUE);
		this.detach();
	}
	
	public void onOKPhonePe() throws Exception {
		this.setAttribute("OK", Boolean.TRUE);
		this.detach();
	}
	public void onOKGpay() throws Exception {
		this.setAttribute("OK", Boolean.TRUE);
		this.detach();
	}
	

}
