package com.pennant.authentication;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Initiator;

public class Authentication implements Initiator{
	Session s=Sessions.getCurrent();
	public void doInit(Page page,Map<String,Object> args) throws Exception
	{
		String status=(String)s.getAttribute("stat");
		System.out.print(status);
		if(status==null || status.equals("no"))
		{
			Executions.sendRedirect("/Admin/login.zul");
			return;
		}
		
	}

}
