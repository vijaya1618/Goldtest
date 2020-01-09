package com.pennant.admin;


	import java.util.Map;
	import javax.servlet.ServletContext;



	import org.springframework.context.ApplicationContext;
	import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
	import org.zkoss.zul.Textbox;
	import org.zkoss.zul.Window;


	public class ItemWnd extends Window {
		protected ItemDAO itemDAO;
		protected Item item;

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}

		public void onCreate() {
			if (item != null) {
				//update
				Intbox ctrl = (Intbox) this.getFellow("Item_Id"); 
				ctrl.setValue(item.getItemID());
				Textbox ctrlt = (Textbox) this.getFellow("Item_Name"); 
				ctrlt.setValue(item.getItemName());
				Intbox ctrlp = (Intbox) this.getFellow("Item_Price"); 
				ctrlp.setValue(item.getItemPrice());
				Textbox ctrla = (Textbox) this.getFellow("Item_Availability"); 
				ctrla.setValue(item.getItemAvailability());
				Intbox ctrlm = (Intbox) this.getFellow("Menu_Id"); 
				ctrlm.setValue(item.getMenuID());
			}
			
			//spring bean, taskDAO
			ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
			itemDAO = (ItemDAO)ctx.getBean("itemDAO");
		}
		
		public void onOK() throws Exception {
			
			//update
			Intbox ctrl = (Intbox) this.getFellow("Item_Id"); 
			item.setItemID(ctrl.getValue());
			Textbox ctrlt = (Textbox) this.getFellow("Item_Name"); 
			item.setItemName(ctrlt.getValue());
			ctrl = (Intbox) this.getFellow("Item_Price"); 
			item.setItemPrice(ctrl.getValue());
			ctrlt = (Textbox) this.getFellow("Item_Availability"); 
			item.setItemAvailability(ctrlt.getValue());
			ctrl = (Intbox) this.getFellow("Menu_Id"); 
			item.setMenuID(ctrl.getValue());
				
				itemDAO.update(item);
			Executions.sendRedirect("/Admin/ItemList.zul");
			
			this.setAttribute("OK", Boolean.TRUE);
			this.detach();
		}
		
		
	}

