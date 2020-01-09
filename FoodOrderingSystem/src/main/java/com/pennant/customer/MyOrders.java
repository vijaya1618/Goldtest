package com.pennant.customer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zhtml.Div;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import com.pennant.admin.*;
public class MyOrders extends Div {

UserInterface db;
	
	
	protected List my;
	
	public List getMy() {
		return my;
	}
	public void setMy(List my) {
		this.my = my;
	}
	private MyOrderUserInterface i;
	private MyOrderModel om;
	
	public void render()
	{
		Listbox lb = (Listbox)this.getFellow("myorders");
		lb.getItems().clear();
		
		for (Iterator it = (my).iterator(); it.hasNext();) {
			MyOrderModel m = (MyOrderModel) it.next();
			
			Listitem lt = new Listitem();
			lt.setValue(m);
			lt.appendChild(new Listcell(String.valueOf((m.getOrderId()))));
			
			lt.appendChild(new Listcell(String.valueOf((m.getPrice()))));
			lt.appendChild(new Listcell(String.valueOf((m.getDate()))));
			lt.appendChild(new Listcell(String.valueOf((m.getPaymentType()))));
			lt.appendChild(new Listcell(String.valueOf((m.getStatus()))));
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
	i=(MyOrderUserInterface)ctx.getBean("OrdersDAO");
	my = i.order(om);
	render();
	}
	
	public void onView() throws Exception
	{
		Listbox lb = (Listbox)this.getFellow("myorders");
		Listitem lt = lb.getSelectedItem();
		if (lt == null)
			return;
		MyOrderModel 	 r = (MyOrderModel)lt.getValue();

		Map<String, MyOrderModel> params = new HashMap<String, MyOrderModel>();
		params.put("order", r);
		Window win = (Window)Executions.createComponents("Customerorders.zul", null, params);
		win.doModal();
		win.setTitle(".");
		win.setClosable(true);
	}
	
}
