package com.pennanttech.Login;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennanttech.Register.RegisterBean;
import com.pennanttech.Register.RegistrationDao;

public class LoginController extends Div {
	
	
	   @Wire
	    Label message;

	private static final long serialVersionUID = 1L;
	protected RegistrationDao regdao;
	
	private Component login;
public void LoginUser() 
{
	
	ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
	regdao=(RegistrationDao)ctx.getBean("taskDAO");	
	

	try {

		Textbox u=(Textbox)this.getFellow("username");
		LoginModel lm=new LoginModel();
		

	lm.setUsername(((Textbox)this.getFellow("username")).getValue());
	lm.setPassword(((Textbox)this.getFellow("password")).getValue());
	RegisterBean rb=regdao.LoginInsert(lm);
	if(rb.getPassword()==null)
	{
		Messagebox.show("please enter valid username/password");
	}
	if(rb.getPassword().equals(lm.getPassword()) && rb.getUsername().equals(lm.getUsername()))
	{
		Session sess = Sessions.getCurrent();
		if(rb.getDepartment().equals("Admin"))
		{	
			sess.setAttribute("userid", rb.getUserid());
			sess.setAttribute("username", rb.getUsername());
			Executions.sendRedirect("admin/admin1.zul");
		}
		else if(rb.getDepartment().equals("Sales"))
		{
			sess.setAttribute("userid", rb.getUserid());
			sess.setAttribute("username", rb.getUsername());
			Executions.sendRedirect("sales/SalesHome.zul");
		}
		else if(rb.getDepartment().equals("Payment"))
			
		{
			sess.setAttribute("userid", rb.getUserid());
			sess.setAttribute("username", rb.getUsername());
			Executions.sendRedirect("Paymentmethods/payment1.zul");
		}
	}
}
catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
}
}

public void forgotpassword()
{
	Window w = (Window) Executions.createComponents("forgotpasswordmod.zul", null, null);
	w.doModal();
}
}
