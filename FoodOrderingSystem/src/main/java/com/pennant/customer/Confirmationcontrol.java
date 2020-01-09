package com.pennant.customer;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

public class Confirmationcontrol extends Div{
	
	public void goHome()
	{
		Executions.sendRedirect("/customer/customer.zul");
	}
	public void goFeedBack()
	{
		Window win = (Window)Executions.createComponents("Customerfeedback.zul", null, null);
		win.doModal();
		win.setTitle(".");
		win.setClosable(true);
	}
}
