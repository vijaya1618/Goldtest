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


public class ItemListWnd extends Div{
	private static final long serialVersionUID = 1L;
	protected ItemDAO itemDAO;
	protected List items;
	
	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	protected void render() {
		Listbox lb = (Listbox)this.getFellow("itemslb");
		lb.getItems().clear();
		
		for (Iterator it = items.iterator(); it.hasNext(); ) {
			Item t = (Item) it.next();
			
			Listitem lt = new Listitem();
			lt.setValue(t);
			lt.appendChild(new Listcell(String.valueOf(t.getItemID())));
			lt.appendChild(new Listcell(String.valueOf(t.getMenuID())));
			lt.appendChild(new Listcell(t.getItemName()));
			lt.appendChild(new Listcell(String.valueOf(t.getItemPrice())));
			lt.appendChild(new Listcell(t.getItemAvailability()));
			
			lb.appendChild(lt);
		}
	}
	
	public void onCreate() throws Exception {
		//	spring bean, ItemDAO
		ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext(
				(ServletContext)getDesktop().getWebApp().getNativeContext());
		itemDAO = (ItemDAO)ctx.getBean("itemDAO");
		
		items = itemDAO.findItem();
		render();
	}
	
	
	
	public void onUpdate() throws Exception {
		Listbox lb = (Listbox)this.getFellow("itemslb");
		Listitem lt = lb.getSelectedItem();
		if (lt == null)
			return;
		Item t = (Item)lt.getValue();

		Map<String, Item> params = new HashMap<String, Item>();
		params.put("item", t);
		Window win = (Window)Executions.createComponents("item.zul", null, params);
		win.doModal();
		win.setTitle(".");
		win.setClosable(true);
		if (win.getAttribute("OK") != null) {	//stupid
			items = itemDAO.findItem();
			render();
		}
	}
	
	public void onDelete() throws Exception {
		Listbox lb = (Listbox)this.getFellow("itemslb");
		Listitem lt = lb.getSelectedItem();
		if (lt == null)
			return;
		Item t = (Item)lt.getValue();
		itemDAO.delete(t);
		lt.detach();
	}
}
