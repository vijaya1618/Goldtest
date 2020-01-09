package com.pennant.customer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


public class Customerprofilewnd extends Div{
	private static final long serialVersionUID = 1L;
	protected CustomerprofileDAO cpd;
	protected List findc;
	//private Customerprofile c;

	public List getFindc() {
		return findc;
	}

	public void setFindc(List findc) {
		this.findc = findc;
	}

	protected void render() {
		
		Label lb = (Label)this.getFellow("firstname");
	    lb.getValue();
	    Label lb1 = (Label)this.getFellow("lastname");
	    lb1.getValue();
	    Label lb2 = (Label)this.getFellow("email");
	    lb2.getValue();
	    Label lb3 = (Label)this.getFellow("phone");
	    lb3.getValue();
	    
	    for (Iterator it = findc.iterator(); it.hasNext(); ) {
		Customerprofilemodel cp=(Customerprofilemodel)it.next();
		
		lb.setValue(cp.getFirstname());
		lb1.setValue(cp.getLastname());
		lb2.setValue(cp.getEmail());	
		lb3.setValue(String.valueOf(cp.getPhonenumber()));
	}
	
	}

	public void onCreate() throws Exception {
		//	spring bean, ItemDAO
		ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext(
				(ServletContext)getDesktop().getWebApp().getNativeContext());
		  cpd = (CustomerprofileDAO)ctx.getBean("taskDAO1");
	
		 findc=cpd.Findcustomer();
		 
		render();

	}
	
	
	
	public void onUpdate() throws Exception {
	
		Window win = (Window)Executions.createComponents("Customerprofileedit.zul", null, null);
		win.doModal();
		win.setTitle("Edit");
		win.setClosable(true);
		}
	}
