package com.pennant.customer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;



public class Resturents extends Div{

		
		private Restmodel rm;
		private List rest;
		private RestuarantDAO rd;
		private Session sess=null;
		String a;
		public List getRest() {
			return rest;
		}

		public void setRest(List rest) {
			this.rest = rest;
		}	
		Session s1=Sessions.getCurrent();
			public void render() throws IOException {
			
			Vbox v=(Vbox)this.getFellow("sample");
			  
		     for(Iterator it = rest.iterator();it.hasNext();) 
		     {
		    	 Hbox hb = new Hbox();
		    	for(int j = 0;j<3 && it.hasNext();j++) {
		    		
		    		
		    			Restmodel rm= (Restmodel)it.next();
		    		
		    			/*
		    			 * while(it.hasNext()) {
		    			 */
		    			Vbox vb = new Vbox();
		    			v.setSclass("subdiv");
		    			final Image ig = new Image();
		    			
		    			AImage aImage = new AImage("demo.jpg",rm.getImage());	
		    			ig.setSclass("divalign");
		    			ig.setContent(aImage);
		    			ig.setId(String.valueOf(rm.getRestaurantid()));
		    			
		    			Label lb = new Label(rm.getRestaurantName());
		    			a=lb.getValue();
		    			Label lb1 = new Label(rm.getLocation()); 
		    			Label lb2 = new Label(String.valueOf(rm.getPhoneNumber()));
		    			vb.appendChild(ig);
		    			vb.appendChild(lb);
		    			vb.appendChild(lb1);
		    			vb.appendChild(lb2);
		    			Separator s=new Separator();
		    			s.setWidth("400px");
		    			vb.appendChild(s);
		    			hb.appendChild(vb);
		    			
		    			ig.addEventListener(Events.ON_CLICK,new EventListener<Event>()
    					{
		    				
    			          
    				public void onEvent(Event arg0) throws Exception {
    					sess=Sessions.getCurrent();
    					
    					sess.setAttribute("rid",ig.getId());
    					System.out.print(sess.getAttribute("rid"));
    					Executions.sendRedirect("/customer/Restaurents.zul");
						/* Executions.getCurrent().setAttribute("rname", a); */
    					}
    					});
		    		}
		    				v.appendChild(hb);
		    	
		    		}
		}
		    public void onCreate() throws Exception {
				//	spring bean, ItemDAO
			ApplicationContext ctx = 
					WebApplicationContextUtils.getRequiredWebApplicationContext(
						(ServletContext)getDesktop().getWebApp().getNativeContext());
				  rd = (RestuarantDAO)ctx.getBean("restDAO");
				  rest=rd.restImpl();
				 
				  
				  
				  render();
				  
				  
		    }  
		    
		    public void gownd()
		    {
		    	Window win = (Window)Executions.createComponents("Changepassword.zul", null, null);
				win.doModal();
		    }
		    public void about()
		    {
		    	Window win = (Window)Executions.createComponents("AboutUs.zul", null, null);
				win.doModal();
				win.setTitle("About Us");
				win.setClosable(true);
		    }
		    public void feedback()
		    {
		    	Window win = (Window)Executions.createComponents("Customerfeedback.zul", null, null);
				win.doModal();
				win.setTitle("Feedback");
				win.setClosable(true);
		    }
		}
		
	



