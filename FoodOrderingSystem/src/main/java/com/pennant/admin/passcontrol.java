package com.pennant.admin;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennant.admin.UserInterface;
import com.pennant.admin.model;
public class passcontrol extends Window
{
	

	public void pass()
		{
	
			UserInterface db;
			ApplicationContext ctx = 
					WebApplicationContextUtils.getRequiredWebApplicationContext(
						(ServletContext)getDesktop().getWebApp().getNativeContext());
			db=(UserInterface)ctx.getBean("taskDAO");
			
			Textbox pass=(Textbox)this.getFellow("newpassword");
			String passw=pass.getValue();
			Textbox c=(Textbox)this.getFellow("confirmpassword");
			String cnfp=c.getValue();
			try
			{
				model m=new model();
				m.setPassword(passw);
				m.setCnfpassword(cnfp);
				db.passs(m);
			
			}
			catch(Exception e)
			{
			
				System.out.println(e);
			
			
			}
			this.setAttribute("OK", Boolean.TRUE);
			this.detach();
			Clients.showNotification("Password Changed Sucessfully", true);
		}
		public void cancel()
		{
			this.detach();
		}
}	