package com.pennant.admin;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
public class restcontrol extends Div {

	UserInterface db;
	Restmodel rm=new Restmodel();	
	
	private Component red;

public void restAdd()
{
	

	ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(UserInterface)ctx.getBean("taskDAO");
		try {
		Textbox f=(Textbox)this.getFellow("restaurantName");
		String rname=f.getValue();
		Longbox p=(Longbox)this.getFellow("phoneNumber");
		long pnumber=p.getValue();
		Textbox e=(Textbox)this.getFellow("location");
		String loc=e.getValue();
		
		
		
			rm.setRestaurantName(rname);
			rm.setPhoneNumber(pnumber);
			rm.setLocation(loc);
			
			 int i=db.Resadd(rm);
			 if(i==1)
			 {
			
				Textbox f1=(Textbox)this.getFellow("restaurantName");
				f1.setValue(null);
				Longbox p1=(Longbox)this.getFellow("phoneNumber");
				p1.setValue((long)0);
				Textbox e1=(Textbox)this.getFellow("location");
				e1.setValue(null);
				 Clients.showNotification("Restaurant Added Successfully", true);
			 }
			 else
			 {
				 Clients.showNotification("Restaurant Not Added ", true);
			 }
		}
		catch(Exception ex)
		{
			Clients.showNotification("Please Fill Details", true);
		}

	}

	public void menuAdd() {
		
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(UserInterface)ctx.getBean("taskDAO");
		try {
		Intbox f=(Intbox)this.getFellow("restaurantID");
		int id=f.getValue();
		Textbox n=(Textbox)this.getFellow("menuName");
		String mn=n.getValue();
		
		
			Menumodel mm=new Menumodel();
			mm.setRestaurantID(id);
			mm.setMenuName(mn);
			int j= db.menAdd(mm);
			if(j==1)
			{
				Clients.showNotification("Menu Added Successfully", true);
				Intbox f1=(Intbox)this.getFellow("restaurantID");
				f1.setValue(null);
				Textbox n1=(Textbox)this.getFellow("menuName");
				n1.setValue(null);
			}
			else
			{
				Clients.showNotification("Menu Not Added", true);

			}
		}
		catch(Exception ex)
		{
			Clients.showNotification("please fill the valid data", true);

		}
	}

	public void onUploadPDF() {
		 EventListener<UploadEvent> el = new EventListener<UploadEvent>() {
			 public void onEvent(UploadEvent event) throws Exception {
	            	ApplicationContext ctx = WebApplicationContextUtils
	            			.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
	            	db = (UserInterface) ctx.getBean("taskDAO");
	            	Media m = event.getMedia();
	            	System.out.println(1);
	            	byte[] b  = m.getByteData();
	            	byte[] c=b;
	            	System.out.println("going");
	    			
	    			rm.setImage(c);
	    			
	                System.out.println("done");
	                
	                //;Filedownload.save(EDAO.retrieve(), EDAO.retrieve().toString(), "Resume.pdf")
	            }
		  
};
Fileupload.get(el);

System.out.println("m.getName()");


	}
public void itemadd() {
		
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
		db=(UserInterface)ctx.getBean("taskDAO");
		try {
		Intbox id=(Intbox)this.getFellow("menuID");
		int mid=id.getValue();
		
		Textbox n=(Textbox)this.getFellow("itemName");
		String itname=n.getValue();
		
		Intbox p=(Intbox)this.getFellow("itemPrice");
		int ip=p.getValue();
		

	
		
	
			Item im=new Item();
			im.setMenuID(mid);
			im.setItemName(itname);
			im.setItemPrice(ip);
			im.setItemAvailability("Available");
			int k= db.itemAdd(im);
			 if(k==1)
			 {
				 Clients.showNotification("Item Added Successfully", true);
				 Intbox id1=(Intbox)this.getFellow("menuID");
				 id1.setValue(null);
				 Textbox n1=(Textbox)this.getFellow("itemName");
				 n1.setValue(null);
				 Intbox p1=(Intbox)this.getFellow("itemPrice");
				 p1.setValue(null);
		
			 }
			 else
			 {
				 Clients.showNotification("Item Not Added", true);
			 }
			 
		}
		catch(Exception ex)
		{
			Clients.showNotification("please fill the valid data", true);
		}
		
	}

	}