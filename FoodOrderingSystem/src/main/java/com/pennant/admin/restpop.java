package com.pennant.admin;
import java.util.HashMap;
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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class restpop extends Window
{
	protected UserInterface db;
	
	protected Restmodel task;
	public Restmodel getTask() {
		return task;
		}

		public void setTask(Restmodel task) {
		this.task = task;
		}

	
	
		public void onCreate() {
			
			if(task!=null) 
			{
				
				Intbox ctrlwq= (Intbox) this.getFellow("Restaurant_Id");
				ctrlwq.setValue(task.getRestaurantid());
				Textbox ctrl = (Textbox) this.getFellow("Restaurant_Name"); 
				ctrl.setValue(task.getRestaurantName());
				Longbox ctrlw = (Longbox) this.getFellow("PhoneNumber");
				ctrlw.setValue(task.getPhoneNumber());
				Textbox ctrlr = (Textbox) this.getFellow("Location"); 
				ctrlr.setValue(task.getLocation());
			
	
		}
			ApplicationContext ctx = 
					WebApplicationContextUtils.getRequiredWebApplicationContext(
						(ServletContext)getDesktop().getWebApp().getNativeContext());
			db=(UserInterface)ctx.getBean("taskDAO");
		}
		public void onOK() {
			
			//new
			
		
				Intbox ctrlwq= (Intbox) this.getFellow("Restaurant_Id");
				task.setRestaurantid(ctrlwq.getValue());
				Textbox ctrl = (Textbox) this.getFellow("Restaurant_Name"); 
				task.setRestaurantName(ctrl.getValue());
				Longbox cotrl = (Longbox) this.getFellow("PhoneNumber");
				task.setPhoneNumber(cotrl.getValue());
				Textbox ctrlt = (Textbox) this.getFellow("Location"); 
				task.setLocation(ctrlt.getValue());
				db.RestUpdate(task);
				Executions.sendRedirect("/Admin/RestaurentUpdateDel.zul");
			
			this.setAttribute("OK", Boolean.TRUE);
			this.detach();
		
			
		}
		public void  onCancel()
		{
			this.detach();
		}
			
}








