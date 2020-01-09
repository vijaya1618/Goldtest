package com.pennant.customer;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennant.admin.Item;
import com.pennant.admin.ItemDAO;

public class AddCtrl extends Window{
	private Session sess=null;
	int i;
	private ItemsDAO id;
	public List item;
	int price;
	String s;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void inc()
		{
		int price=0;
			Label a=(Label)this.getFellow("count"); 
			String b=(String)a.getValue();
			int i=Integer.parseInt(b);
			
			i++;
			
			String c=String.valueOf(i);
			a.setValue(c);
			 for (Iterator it = item.iterator(); it.hasNext(); ) {
					
					Cartmodel cm=(Cartmodel)it.next();
				 price=cm.getTotalprice();
			 }
			 int tp=i*price;
			 Label ctrlp = (Label) this.getFellow("TotalPrice"); 
				ctrlp.setValue(String.valueOf(tp));
			
		}
	public void dec()
	{
		int price=0;
		Label a=(Label)this.getFellow("count"); 
		String b=(String)a.getValue();
		int i=Integer.parseInt(b);
		if(i==1)
			i=1;
		else
		i--;
		String c=String.valueOf(i);
		a.setValue(c);
		for (Iterator it = item.iterator(); it.hasNext(); ) {
			
			Cartmodel cm=(Cartmodel)it.next();
		 price=cm.getTotalprice();
	 }
		 int tp=i*price;
		 Label ctrlp = (Label) this.getFellow("TotalPrice"); 
			ctrlp.setValue(String.valueOf(tp));
	}
	
	  public void onCreate() throws Exception { // spring bean, ItemDAO
	  ApplicationContext ctx =
	  WebApplicationContextUtils.getRequiredWebApplicationContext(
	  (ServletContext)getDesktop().getWebApp().getNativeContext()); 
	  id = (ItemsDAO) ctx.getBean("MenuitemDAO");
	  sess=Sessions.getCurrent();
	  
	  s=(String) sess.getAttribute("itemid");
		
		i=Integer.parseInt(s);
		
	  item= id.addItem(i); 
	  render(); 
	  }
	 public void render()
	 {
		 for (Iterator it = item.iterator(); it.hasNext(); ) {
			
		Cartmodel cm=(Cartmodel)it.next();
		
			Label ctrlt = (Label) this.getFellow("Itemname"); 
			ctrlt.setValue(cm.getItemname());
			Label ctrlp = (Label) this.getFellow("TotalPrice"); 
			ctrlp.setValue(String.valueOf(cm.getTotalprice()));
			price=cm.getPrice();
		 }
			
		 
	 }
	 public void Add() throws Exception
	 {
		 Label iname = (Label) this.getFellow("Itemname"); 
			
			String name=iname.getValue();
		 Label ctrlp = (Label) this.getFellow("TotalPrice"); 
		 Label a=(Label)this.getFellow("count"); 
			String b=(String)a.getValue();
			int j=Integer.parseInt(b);
		 Cartmodel cm=new Cartmodel();
		 cm.setItemid(i);
		 cm.setQty(j);
		 cm.setTotalprice(Integer.parseInt(ctrlp.getValue()));
		 cm.setItemname(name);
		 cm.setPrice(price);
		 int c=id.cartitem(cm);
		
		if(c==1) {
		 Clients.showNotification("Item Added Sucessfully");
		}
	 }
	 public void cart()
	 {
		 Executions.sendRedirect("/customer/cart.zul");
	 }
	 
}
