package com.pennanttech.Empmodifications;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.*;

import com.pennanttech.Register.RegistrationDao;

import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class ViewPageCntl extends Window {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected RegistrationDao regdao;

	public void view() {
		try {

			ApplicationContext ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext((ServletContext) (getDesktop()).getWebApp().getNativeContext());
			regdao = (RegistrationDao) ctx.getBean("taskDAO");

			ViewPagebean vp = new ViewPagebean();
			vp.setUserid(((Intbox) this.getFellow("userid")).getValue());
			System.out.println(((Intbox) this.getFellow("userid")).getValue());

			ViewPagebean vb = regdao.ViewEmployees(vp);

			((Textbox) this.getFellow("username")).setValue(vb.getUsername());
			((Textbox) this.getFellow("fullname")).setValue(vb.getFullname());
			((Longbox) this.getFellow("mobileno")).setValue(vb.getMobileno());
			((Textbox) this.getFellow("gender")).setValue(vb.getGender());
			((Textbox) this.getFellow("email")).setValue(vb.getEmail());
			((Textbox) this.getFellow("department")).setValue(vb.getDepartment());
			((Datebox) this.getFellow("DateOfBirth")).setValue(vb.getDob());

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void delete() throws Exception {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) (getDesktop()).getWebApp().getNativeContext());

		regdao = (RegistrationDao) ctx.getBean("taskDAO");
		ViewPagebean vp = new ViewPagebean();
		vp.setUserid(((Intbox) this.getFellow("deleteemployees")).getValue());
		regdao.delete(vp);

	}

	

	public String empupdate1() {

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		regdao = (RegistrationDao) ctx.getBean("taskDAO");

		ViewPagebean vpb = new ViewPagebean();

		Textbox uname = (Textbox) this.getFellow("username");
		String user = uname.getValue();

		Textbox fname = (Textbox) this.getFellow("fullname");
		String fullname = fname.getValue();

		Longbox number = (Longbox) this.getFellow("mobileno");
		long mobile = number.getValue();

		Textbox gen = (Textbox) this.getFellow("gender");
		String gender = gen.getValue();

		Textbox email = (Textbox) this.getFellow("email");
		String em = email.getValue();
		System.out.println(em);

		Textbox dep = (Textbox) this.getFellow("department");
		String dept = dep.getValue();

		
		 Datebox date = (Datebox) this.getFellow("DateOfBirth");
		 Date dob=(Date) date.getValue();
	
		Intbox id = (Intbox) this.getFellow("userid");
		int userid = id.getValue();

		vpb.setUsername(user);
		vpb.setFullname(fullname);
		vpb.setMobileno(mobile);
		vpb.setGender(gender);
		vpb.setEmail(em);
		vpb.setDepartment(dept);
		 vpb.setDob(dob);
		vpb.setUserid(userid);

		String res=regdao.empupdate(vpb);
		
		if(res=="success")
		{
			
			
			
			Messagebox.show("Employee is successfully Updated","Click on ok",	Messagebox.OK , Messagebox.INFORMATION,
					new org.zkoss.zk.ui.event.EventListener() {
						public void onEvent(Event evt) throws InterruptedException {
							if (evt.getName().equals("onOK")) {
		   
								Executions.sendRedirect("/admin/admin1.zul");
			
			
							}
			
		}
			});
		}
		
		

		
		return res;
	}
	
	public String deleteemp() {

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) (getDesktop()).getWebApp().getNativeContext());

		regdao = (RegistrationDao) ctx.getBean("taskDAO");
		ViewPagebean vpb = new ViewPagebean();
		System.out.println("entered");

		Intbox i = (Intbox) this.getFellow("userid");
		int i2 = i.getValue();
		System.out.println(i2);
		vpb.setUserid(i2);
		String res=regdao.deleteemp(vpb);
		if(res=="success")
		
		{
			Messagebox.show("Employee is successfully deleted","Click on ok",	Messagebox.OK , Messagebox.INFORMATION,
					new org.zkoss.zk.ui.event.EventListener() {
						public void onEvent(Event evt) throws InterruptedException {
							if (evt.getName().equals("onOK")) {
		   
								Executions.sendRedirect("/admin/admin1.zul");
					
					
					
				}
				
						}
						
					}
				
						);
			
			
			
			
			
			
			
			
			
			
			
		}
		return res;
		
		

	}
}

