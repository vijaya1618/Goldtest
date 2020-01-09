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


	public class OrderslistWnd extends Div{
		private static final long serialVersionUID = 1L;
		protected OrdersDAO OrdersDAO;
		protected List orders,orders1;
		
		

		public List getOrders() {
			return orders;
		}


		public void setOrders(List orders) {
			this.orders = orders;
		}


		protected void render() {
			Listbox lb = (Listbox)this.getFellow("orders");
			lb.getItems().clear();
			
			for (Iterator it = orders.iterator(); it.hasNext(); ) {
				Orders r = (Orders) it.next();
				
				Listitem lt = new Listitem();
				lt.setValue(r);
				lt.appendChild(new Listcell(String.valueOf(r. getOrderID())));
				lt.appendChild(new Listcell(String.valueOf(r.getCustomerID())));
				lt.appendChild(new Listcell((r.getRname())));
				lt.appendChild(new Listcell((r.getStatus())));
				lb.appendChild(lt);
			}
			
			lb = (Listbox)this.getFellow("orders1");
			lb.getItems().clear();
			
			for (Iterator it = orders1.iterator(); it.hasNext(); ) {
				Orders r = (Orders) it.next();
				
				Listitem lt = new Listitem();
				lt.setValue(r);
				lt.appendChild(new Listcell(String.valueOf(r. getOrderID())));
				lt.appendChild(new Listcell(String.valueOf(r.getCustomerID())));
				lt.appendChild(new Listcell((r.getRname())));
				lt.appendChild(new Listcell((r.getStatus())));
				lb.appendChild(lt);
			}
		}
		
		public void onCreate() throws Exception {
			//	spring bean, ItemDAO
			ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
			  OrdersDAO = (OrdersDAO)ctx.getBean("orderDAO");
			
			orders = OrdersDAO.findItem();
			orders1 = OrdersDAO.findItem1();
			render();
		}
		
		
		
		public void onUpdate() throws Exception {
			Listbox lb = (Listbox)this.getFellow("orders");
			Listitem lt = lb.getSelectedItem();
			if (lt == null)
				return;
			Orders r = (Orders)lt.getValue();

			Map<String, Orders> params = new HashMap<String, Orders>();
			params.put("Orders", r);
			Window win = (Window)Executions.createComponents("orders.zul", null, params);
			win.doModal();
			win.setTitle(".");
			win.setClosable(true);
			if (win.getAttribute("OK") != null) {	//stupid
				orders = OrdersDAO.findItem();
				render();
			}
		}
		
		
	}

