package com.pennant.admin;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


	public class OrdersWnd extends Window {
		protected OrdersDAO OrdersDAO;
		protected Orders Orders;

		public Orders getOrders() {
			return Orders;
		}

		public void setOrders(Orders orders) {
			Orders = orders;
		}

		public void onCreate() {
			if (Orders != null) {
				//update
				Intbox ctrl = (Intbox) this.getFellow("Order_Id"); 
				ctrl.setValue(Orders.getOrderID());
				
				Combobox ctrlp = (Combobox) this.getFellow("Status"); 
				ctrlp.setValue(Orders.getStatus());
			
			//spring bean, taskDAO
			ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
			OrdersDAO = (OrdersDAO)ctx.getBean("orderDAO");
		}
		}		
		public void onOK() throws Exception {
			
			//update
			Intbox ctrl = (Intbox) this.getFellow("Order_Id"); 
			Orders.setOrderID(ctrl.getValue());
			
			Combobox ctrlts = (Combobox) this.getFellow("Status"); 
			Orders.setStatus(ctrlts.getValue());
				
				OrdersDAO.update(Orders);
				Executions.sendRedirect("/Admin/Orderslist.zul");
			
			this.setAttribute("OK", Boolean.TRUE);
			this.detach();
		}
		
		public void onCancel() {
			this.detach();
		}
	}
