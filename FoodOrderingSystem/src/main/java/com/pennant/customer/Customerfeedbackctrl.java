package com.pennant.customer;


import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

public class Customerfeedbackctrl extends Div{
	private static final long serialVersionUID = 1L;
	public void onFeedback() throws Exception {
		
		Window win = (Window)Executions.createComponents("Customerfeedback.zul", null, null);
		win.doModal();
		}
	}
