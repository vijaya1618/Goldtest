package com.pennant.admin;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

public class viewcustomercontrol extends Div{

	
	UserInterface db;
	
	private Component cust;
	protected List tasks;
	private Custmodel cm;
	
	public Custmodel getCm() {
		return cm;
	}

	public void setCm(Custmodel cm) {
		this.cm = cm;
	}
	public void render()
	{
		Listbox lb = (Listbox)this.getFellow("customers");
		lb.getItems().clear();
		
		for (Iterator it = (tasks).iterator(); it.hasNext();) {
			Custmodel t = (Custmodel) it.next();
			
			Listitem lt = new Listitem();
			lt.setValue(t);
			lt.appendChild(new Listcell(String.valueOf((t.getCust_id()))));
			lt.appendChild(new Listcell(t.getFirstname()));
			lt.appendChild(new Listcell(String.valueOf((t.getLastname()))));
			lt.appendChild(new Listcell(String.valueOf((t.getEmail()))));
			lt.appendChild(new Listcell(String.valueOf((t.getPhone()))));
			lb.appendChild(lt);
		}
	}
	public void onCreate()
	{
		/*cm=new Custmodel();
		Intbox ctrlwq= (Intbox) this.getFellow("cust_id");
		cm.setCust_id(ctrlwq.getValue());
		Textbox ctrl = (Textbox) this.getFellow("Firstname"); 
		cm.setFirstname(ctrl.getValue());
		Textbox ctrlw = (Textbox) this.getFellow("Lastname");
		cm.setLastname(ctrlw.getValue());
		Textbox ctrlr = (Textbox) this.getFellow("email"); 
		cm.setEmail(ctrlr.getValue());
		Intbox ctr= (Intbox) this.getFellow("phone");
		cm.setPhone(ctr.getValue());*/
	
		ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext(
				(ServletContext)getDesktop().getWebApp().getNativeContext());
	db=(UserInterface)ctx.getBean("taskDAO");
	tasks = db.showCust();
	render();
	}
}
