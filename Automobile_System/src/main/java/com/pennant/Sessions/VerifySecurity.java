package com.pennant.Sessions;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Initiator;

public class VerifySecurity implements Initiator {

	public void doInit(Page page, Map<String, Object> args) throws Exception {
		
		Session sess=Sessions.getCurrent();
		String s=(String) sess.getAttribute("username");
		if(s==null)

		{
		Executions.sendRedirect("Login.zul");
		
	}
	
	}	
	

}
