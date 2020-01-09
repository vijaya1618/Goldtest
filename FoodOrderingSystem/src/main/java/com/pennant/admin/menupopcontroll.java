package com.pennant.admin;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;




public class menupopcontroll extends Div{
	UserInterface db;
	protected List tasks,rlist;
	
	
	protected Menumodel task;
												/*popup to set  the model for ok*/
	public Menumodel getTask() {
		return task;
	}

	public void setTask( Menumodel task) {
		this.task = task;
	}
	protected void render() {
		Listbox lb = (Listbox)this.getFellow("menu");
		lb.getItems().clear();
		
		for (Iterator it = (tasks).iterator(); it.hasNext();) {
			Menumodel t = (Menumodel) it.next();
			
			Listitem lt = new Listitem();
			lt.setValue(t);
			lt.appendChild(new Listcell(String.valueOf((t.getMenuid()))));
			lt.appendChild(new Listcell(t.getMenuName()));
			
			lt.appendChild(new Listcell(String.valueOf((t.getRestaurantID()))));
			lb.appendChild(lt);
		}
			
	}	
	public void onCreate() throws Exception {
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(UserInterface)ctx.getBean("taskDAO");
		tasks = db.findAll();

		render();
	}
	
	/*public void onUpdate() throws Exception {
		Window win = (Window)Executions.createComponents("task.zul", null, null);
		win.doModal();
		if (win.getAttribute("OK") != null) {	//stupid
			//menu = menu.findAll();
			render();
		
	*/
	
	public void onDelete() throws Exception {
		Listbox lb = (Listbox)this.getFellow("menu");
		Listitem lt = lb.getSelectedItem();
		if (lt == null)
			return;
		Menumodel t = (Menumodel)lt.getValue();
		db.delete(t);
		lt.detach();
		
		
		/*
		 * win.doModal(); if (win.getAttribute("OK") != null) { //stupid //menu =
		 * menu.findAll(); render(); }
		 */
		/*
		 * Listbox lb = (Listbox)this.getFellow("menu"); Listitem lt =
		 * lb.getSelectedItem(); if (lt == null) return; Menumodel t =
		 * (Menumodel)lt.getValue(); db.delete(t); lt.detach();
		 */
	}
	public void onOk() {
		
			//new
			task = new  Menumodel();
			
			Intbox ctrl = (Intbox) this.getFellow("restuarantid"); 
			task.setRestaurantID(ctrl.getValue());
			Textbox ctrlw = (Textbox) this.getFellow("menuname");
			task.setMenuName(ctrlw.getValue());
			Intbox ctrlr = (Intbox) this.getFellow("menuid"); 
			task.setMenuid(ctrlr.getValue());
			try {
					db.findAll();
				}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
		this.setAttribute("OK", Boolean.TRUE);
		this.detach();
}
	public void onCancel()
	{
		
		this.detach();

	}
	public void search() 
	{
		Intbox ctrl = (Intbox) this.getFellow("rid"); 
		int r=ctrl.getValue();
		try {
				rlist=db.findByRid(r);
			Listbox lb = (Listbox)this.getFellow("menu");
			lb.getItems().clear();
			
			for (Iterator it = (rlist).iterator(); it.hasNext();) {
				Menumodel t = (Menumodel) it.next();
				
				Listitem lt = new Listitem();
				lt.setValue(t);
				lt.appendChild(new Listcell(String.valueOf((t.getMenuid()))));
				lt.appendChild(new Listcell(t.getMenuName()));
				
				lt.appendChild(new Listcell(String.valueOf((t.getRestaurantID()))));
				lb.appendChild(lt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
		}
		
		
	}
