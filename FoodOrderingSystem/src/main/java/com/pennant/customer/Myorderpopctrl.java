package com.pennant.customer;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennant.admin.Orders;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
public class Myorderpopctrl extends Window {
	MyOrderUserInterface mop;
	private MyOrderModel order;
	  private List orderpop;

	public MyOrderModel getOrder() {
		return order;
	}


	public void setOrder(MyOrderModel order) {
		this.order = order;
	}
  
	public void render()
	{
		Listbox lb = (Listbox)this.getFellow("myorder");
		lb.getItems().clear();
		
		for (Iterator it = (orderpop).iterator(); it.hasNext();) {
			MyOrderModel m = (MyOrderModel) it.next();
			
			Listitem lt = new Listitem();
			lt.setValue(m);
			lt.appendChild(new Listcell(String.valueOf((m.getItemid()))));
			lt.appendChild(new Listcell(String.valueOf((m.getItemName()))));
			
			lt.appendChild(new Listcell(String.valueOf((m.getQuantity()))));
			lt.appendChild(new Listcell(String.valueOf((m.getTotalprice()))));
		
			lb.appendChild(lt);
		}
	}
	
	
	public void onCreate() throws Exception{
		
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
			mop = (MyOrderUserInterface)ctx.getBean("OrdersDAO");

			
            orderpop=mop.popOrder(order.getOrderId());
		render();
}		
}
