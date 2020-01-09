package com.pennant.admin;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


public class forgetcontrol extends Window{
		
	UserInterface db;

//	private Window forget;
	
	
	
	
		
		public void forgetPass() {
			ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
			db=(UserInterface)ctx.getBean("taskDAO");
			Textbox ans=(Textbox)this.getFellow("answer");
			String answ=ans.getValue();
			try{
				model m=new model();
			
				m.setAnswers(answ);
				db.forgetS(m);
				
			}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
			this.setAttribute("OK", Boolean.TRUE);
			this.detach();
			
	}
	public void cancel()
	{
		this.detach();
	}
	
	


}
