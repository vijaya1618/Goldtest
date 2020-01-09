package com.pennant.customer;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Div;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennant.customer.ChangepasswordModel;
import com.pennant.customer.ChangepasswordDAO;

public class ChangepasswordController extends Window{
	ChangepasswordDAO   cpd;
	
	
	
	public void changepassword()
	{
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		cpd=(ChangepasswordDAO)ctx.getBean("CPDAO");
		

		Textbox t=(Textbox)this.getFellow("oldpassword");
		String oldpass=t.getValue();
		Textbox t1=(Textbox)this.getFellow("newpassword");
		String newpass=t1.getValue();
		Textbox t2=(Textbox)this.getFellow("confirmpassword");
		String cnfpass=t2.getValue();
		
		try {
			ChangepasswordModel cpm=new ChangepasswordModel();
		
			cpm.setOldpassword(oldpass);
			cpm.setNewpassword(newpass);
			cpm.setConfirmpassword(cnfpass);
			cpd.changepassword(cpm);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	public void cancel()
	{
		this.detach();
	}
	
}
