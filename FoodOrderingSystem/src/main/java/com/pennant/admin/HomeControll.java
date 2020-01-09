package com.pennant.admin;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Div;

public class HomeControll extends Div{
	
	public void goReg() {
		Executions.sendRedirect("/Admin/registerpage.zul");
		
	}

	public void goLog() {
		Executions.sendRedirect("/Admin/login.zul");
	
	}
	
	
	
	
	
}