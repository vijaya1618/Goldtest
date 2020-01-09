package com.pennant.admin;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
//import org.zkoss.zhtml.Map;
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


public class restuarantupdel extends Div
{
	UserInterface db;
	protected List tasks;
	

	
		/*popup to set  the model for ok*/

public List getTasks() {
		return tasks;
	}
	public void setTasks(List tasks) {
		this.tasks = tasks;
	}
protected void render() {
	Listbox lb = (Listbox)this.getFellow("restuarantUpdDel");
	lb.getItems().clear();
	
	for (Iterator it = (tasks).iterator(); it.hasNext();) {
		Restmodel t = (Restmodel) it.next();
		
		Listitem lt = new Listitem();
		lt.setValue(t);
		lt.appendChild(new Listcell(String.valueOf((t.getRestaurantid()))));
		lt.appendChild(new Listcell(String.valueOf((t.getRestaurantName()))));
		lt.appendChild(new Listcell(String.valueOf((t.getPhoneNumber()))));
		lt.appendChild(new Listcell(String.valueOf((t.getLocation()))));
		lb.appendChild(lt);
	}	
}
public void onCreate() throws Exception {
	ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext(
				(ServletContext)getDesktop().getWebApp().getNativeContext());
	db=(UserInterface)ctx.getBean("taskDAO");
	tasks = db.findRest();
	render();
	
}
public void onUpdate() throws Exception {

	Listbox lb = (Listbox)this.getFellow("restuarantUpdDel");
	Listitem lt = lb.getSelectedItem();
	if (lt == null)
		return;
	Restmodel t = (Restmodel)lt.getValue();

	Map<String, Restmodel> params = new HashMap<String, Restmodel>();
	params.put("task", t);
	Window win = (Window)Executions.createComponents("resttask.zul", null,params);
	win.doModal();	
	win.setTitle(".");
	win.setClosable(true);
	if (win.getAttribute("OK") != null) {	//stupid
	tasks=db.findRest();
	render();
	}
}

/*Listbox lb = (Listbox)this.getFellow("taskslb");
Listitem lt = lb.getSelectedItem();
if (lt == null)
	return;
Task t = (Task)lt.getValue();

Map<String, Task> params = new HashMap<String, Task>();
params.put("task", t);
Window win = (Window)Executions.createComponents("task.zul", null, params);
win.doModal();
if (win.getAttribute("OK") != null) {	//stupid
	tasks = taskDAO.findAll();
	render();
}*/
public void onDelete() throws Exception {
	Listbox lb = (Listbox)this.getFellow("restuarantUpdDel");
	Listitem lt = lb.getSelectedItem();
	if (lt == null)
		return;
	Restmodel td = (Restmodel)lt.getValue();
	db.deleter(td);
	lt.detach();
}

}
