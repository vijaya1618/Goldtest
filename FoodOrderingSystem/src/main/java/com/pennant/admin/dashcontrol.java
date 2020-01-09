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


public class dashcontrol extends Div
{
	

	UserInterface db;
	Listitem lt;
	protected int task;
	int Co;
	int Po,Pico,Cano,Do,Cust;

	
	public int getTask() {
		return task;
	}

	public void setTask(int task) {
		this.task = task;
	}

	protected void render() {
		Listbox lb = (Listbox)this.getFellow("totalOrders");
		lb.getItems().clear();
		
	
			Dashmodel t =new Dashmodel();
			t.setTotalorders(task);
			lt = new Listitem();
			lt.setValue(t);
			lt.appendChild(new Listcell(String.valueOf((t.getTotalorders()))));
			lb.appendChild(lt);																																				
		
		
	    Listbox lb1 = (Listbox)this.getFellow("confirmedorders");
		lb1.getItems().clear();
		
			t.setConfirmedorders(Co);
			lt = new Listitem();
			lt.setValue(t);
			lt.appendChild(new Listcell(String.valueOf((t.getConfirmedorders()))));
			lb1.appendChild(lt);
		
		
		Listbox lb2 = (Listbox)this.getFellow("Pickuporders");
		lb2.getItems().clear();
		
		
		t.setPickuporders(Pico);
			Listitem lt = new Listitem();
			lt.setValue(t);
			lt.appendChild(new Listcell(String.valueOf((t.getPickuporders()))));
			lb2.appendChild(lt);
		
		
		Listbox lb3 = (Listbox)this.getFellow("Preparingorders");
		lb3.getItems().clear();
		
		
			t.setPreparingorders(Po);
			lt = new Listitem();
			lt.setValue(t);
			lt.appendChild(new Listcell(String.valueOf((t.getPreparingorders()))));
			lb3.appendChild(lt);
		
		
		
		
		
		Listbox lb5 = (Listbox)this.getFellow("delivery");
		lb5.getItems().clear();
		
		t.setDeliveredorders(Do);
			
			lt = new Listitem();
			lt.setValue(t);
			lt.appendChild(new Listcell(String.valueOf((t.getDeliveredorders()))));
			lb5.appendChild(lt);
		
		Listbox lb6 = (Listbox)this.getFellow("TotalUsers");
		lb6.getItems().clear();
		t.setTotalusers(Cust);
		
			
			lt = new Listitem();
			lt.setValue(t);
			lt.appendChild(new Listcell(String.valueOf((t.getTotalusers()))));
			lb6.appendChild(lt);
		
		
	}
	
	public void onCreate() throws Exception {
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(UserInterface)ctx.getBean("taskDAO");
		task=db.totalord();
		Co=db.conford();
		Pico=db.pickord();
		Po=db.prepord();
		/* Cano=db.totalcancelled(); */
		Do=db.totaldeliver();
		Cust=db.totaluse();
		render();
		
	}
	
	
}	