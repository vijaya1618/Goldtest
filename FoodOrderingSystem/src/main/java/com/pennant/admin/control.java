package com.pennant.admin;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class control extends Div{
	
	UserInterface db;
	
	private Component reg;
	public void verifyRegister() {
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(UserInterface)ctx.getBean("taskDAO");
		
		System.out.println("enter");
		Textbox f=(Textbox)this.getFellow("firstname");
		String fname=f.getValue();
		Textbox l=(Textbox)this.getFellow("lastname");
		String lname=l.getValue();
		Textbox e=(Textbox)this.getFellow("email");
		String email=e.getValue();
		Longbox p=(Longbox)this.getFellow("phone");
		long ph=p.getValue();
		Textbox pass=(Textbox)this.getFellow("password");
		String passw=pass.getValue();
		Textbox c=(Textbox)this.getFellow("cnfpswd");
		String cnfp=c.getValue();
		Textbox s=(Textbox)this.getFellow("security");
		String sec=s.getValue();
		Textbox ans=(Textbox)this.getFellow("answer");
		String answ=ans.getValue();
		try
		{
			model m=new model();
			m.setFirstname(fname);
			m.setLastname(lname);
			m.setEmail(email);
			m.setPhone(ph);
			m.setPassword(passw);
			m.setCnfpassword(cnfp);
			m.setSecurity(sec);
			m.setAnswers(answ);
		int val=db.validuser(m);
		}	
		catch(Exception et)
		{
			System.out.println(et);
		}
	}
	public void verifyLogin()
	{
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(UserInterface)ctx.getBean("taskDAO");
		Textbox e=(Textbox)this.getFellow("email");
		String emai=e.getValue();
		Textbox pass=(Textbox)this.getFellow("password");
		String pas=pass.getValue();
		try {
			loginmodel lm=new loginmodel();	
			lm.setEmail(emai);
			lm.setPassword(pas);
			 db.validLogin(lm);
			 
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		
	}
	public void pressButton() throws Exception
	{
		model m=new model();
		Textbox e=(Textbox)this.getFellow("email");
		Session sess=Sessions.getCurrent();
		
		sess.setAttribute("email", e.getValue());
			Window win = (Window)Executions.createComponents("Forgotpassword.zul", null, null);
			win.doModal();
			win.setTitle(".");
			win.setClosable(true);
		
	}
	
		
}

