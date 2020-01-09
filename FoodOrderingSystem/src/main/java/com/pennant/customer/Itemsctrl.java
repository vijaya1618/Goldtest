package com.pennant.customer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import com.pennant.admin.Feedbackmodel;
import com.pennant.admin.Restmodel;

public class Itemsctrl extends Div {

	private Menuitemmodel mi;
	private List Res;
	private List Menus;
	private List Items;
	private ItemsDAO id;
	private Session sess=null;
	Menuitemmodel im;
	int i;
	public void render() {
		
		Vbox v = (Vbox) this.getFellow("Rname");
		for (Iterator it = Res.iterator(); it.hasNext();) {
	im = (Menuitemmodel) it.next();

			/*
			 * while(it.hasNext()) {
			 */
			Vbox vb = new Vbox();
			vb.setSclass("subdiv");

			Label lb = new Label(im.getRestname());
			lb.setSclass("subdiv");
			vb.appendChild(lb);
			Vbox vb1 = new Vbox();
			vb1.setSclass("subdiv");
			Label lb1 = new Label(im.getLocation());
			lb1.setSclass("subdiv");
			vb1.appendChild(lb1);
			v.appendChild(vb);
			v.appendChild(vb1);
		}
		
		  Tabs t=(Tabs)this.getFellow("tab");
		  
		  for(Iterator it = Menus.iterator();it.hasNext();) {
		  
		im=(Menuitemmodel)it.next();
		  
		  
		  
		  Tab a=new Tab(im.getMenuName()); 
	
		  //a.getAction(im.setMenuid(menuid););
		  t.appendChild(a);
		 

		 } 
		  Listbox lb=(Listbox)this.getFellow("Menu");
			lb.getItems().clear();
			ListModelList li;
			try {
				li = new ListModelList(id.findItems(i));
			
				lb.setModel(li);
				
			} 
			
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

	}

	public void onCreate() throws Exception {
		// spring bean, ItemDAO
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		id = (ItemsDAO) ctx.getBean("MenuitemDAO");
		sess=Sessions.getCurrent();
		
		String s=(String) sess.getAttribute("rid");
		
		i=Integer.parseInt(s);
		
		
		Res = id.findRest(i);
		Menus=id.findMenus(i);
	
		render();
	}
	public void add(String s) {
		String a=s;
		System.out.print(a);
		sess.setAttribute("itemid", a);
		Window win = (Window)Executions.createComponents("Addcart.zul", null,null);
		win.doModal();
		win.setTitle(".");
		win.setClosable(true);
		}
	 public void gownd()
	    {
	    	Window win = (Window)Executions.createComponents("Changepassword.zul", null, null);
			win.doModal();
	    }
	
}
