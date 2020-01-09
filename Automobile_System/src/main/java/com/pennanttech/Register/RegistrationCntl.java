package com.pennanttech.Register;

import java.sql.Date;
import java.text.SimpleDateFormat;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
//import org.zkoss.zk.ui.event.Event;
//import org.zkoss.zk.ui.select.SelectorComposer;
//import org.zkoss.zk.ui.select.annotation.Listen;
//import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
//import java.util.*;
import org.zkoss.zul.Window;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Div;
//import org.zkoss.zk.ui.Executions;

import com.pennanttech.Register.RegistrationDao;

public class RegistrationCntl extends Div {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	protected RegistrationDao regdao;

	private Component register;
	protected RegisterBean rb = new RegisterBean();

	ResetPasswordBean rpb = new ResetPasswordBean();
public void validate()
{
    String password=((Textbox)this.getFellow("password")).getValue();	    
    int cap=0,num=0,spec=0;	    
    for(int i=0;i<password.length();i++)
    {
    	//System.out.print(password.charAt(i));
    	if((int)password.charAt(i)>=65 && (int)password.charAt(i)<=90)
    		cap++;
    	if((int)password.charAt(i)>=48 &&(int)password.charAt(i)<=57)
    		num++;
    	if((int)password.charAt(i)==35 ||(int)password.charAt(i)==36||(int)password.charAt(i)==64)
    		spec++;
    }
    
    if(cap>=1 && num>=1 && spec>=1 && password.length()>=8 &&password.length()<=16)
    {
  	
    }
    else
    	Messagebox.show("Please Use Atleast One Uppercase,One Special Character And One Numeric");
	
}	
	
	
	
	
	
	

	@SuppressWarnings("rawtypes")
	public void submit() { // register a listener to a component called retrieve

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		regdao = (RegistrationDao) ctx.getBean("taskDAO");

		RegisterBean rb = new RegisterBean();
		rb.setUsername(((Textbox) this.getFellow("usr")).getValue());
		rb.setFullname(((Textbox) this.getFellow("fname")).getValue());
		rb.setPassword(((Textbox) this.getFellow("password")).getValue());
		rb.setEmail(((Textbox) this.getFellow("email")).getValue());
		rb.setPhone(((Longbox) this.getFellow("phn")).getValue());
		rb.setGender(((Combobox) this.getFellow("gender")).getValue());
		rb.setDepartment(((Combobox) this.getFellow("dept")).getValue());
		rb.setSecurityquestion(((Combobox) this.getFellow("question")).getValue());
		rb.setAnswer(((Textbox) this.getFellow("answer")).getValue());
		

		RegisterBean res;
		try {
			java.util.Date utilDate = ((Datebox) this.getFellow("dob")).getValue();
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
			final String stringDate = dateFormat.format(utilDate);
			Date sqlDate = Date.valueOf(stringDate);
			rb.setDob(sqlDate);
			res = regdao.RegUser(rb);
			
			String y="success";
			if(y.equals(rb.getStatus()))
			{
			
				Messagebox.show("Registration Success!!","Confirm Dialog",Messagebox.OK,Messagebox.INFORMATION,new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onOK")) {
		Executions.sendRedirect("Login.zul");
						}
				}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void ResetPassword() {

		try {
			System.out.println("started");

//		System.out.println(((Textbox)this.getFellow("email")).getValue());
			ApplicationContext ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
			regdao = (RegistrationDao) ctx.getBean("taskDAO");

			rpb.setEmail(((Textbox) this.getFellow("email")).getValue());
			rpb.setAnswer(((Textbox) this.getFellow("answer")).getValue());

			rb = regdao.Resetpassword(rpb);

			if (rb.getAnswer().equals(rpb.getAnswer()) && rb.getEmail().equals(rpb.getEmail())) 
			{
				Window w = (Window) Executions.createComponents("forgotpsd.zul", null, null);
				w.doModal();
			}
			else
			{
				Messagebox.show("Invalid Email and answer");
			}
		}
		catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void updatepassword() {
		try {
			ApplicationContext ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
			regdao = (RegistrationDao) ctx.getBean("taskDAO");
			rpb.setPassword(((Textbox) this.getFellow("password")).getValue());
			rpb.setRepassword(((Textbox) this.getFellow("repassword")).getValue());
			ResetPasswordBean r = regdao.Updatepassword(rpb);
			String x = "success";
			if (x.equals(r.getStatus())) {
				
				Messagebox.show("Your Password has been successfully Changed!!","Confirm Dialog",Messagebox.OK,Messagebox.INFORMATION,new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onOK")) {
		Executions.sendRedirect("Login.zul");
						}
				}
				});
			}
	
		}

		catch (Exception e) {

			e.printStackTrace();
		}

	}
	public void Cancel()
	{
		Executions.sendRedirect("Login.zul");
	}

}
