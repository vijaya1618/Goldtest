package com.pennanttech.CustInfo;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import com.pennanttech.ProdInfo.ProdBean;
import com.pennanttech.Register.RegistrationDao;

public class ProductDisplayCntrl extends Div {

	private static final long serialVersionUID = 1L;
	private RegistrationDao regdao;
	private ProdBean pb;
	private List products;
	private Window wincheck;
	
	public void onCreate() throws Exception 
	{
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 regdao = (RegistrationDao)ctx.getBean("taskDAO");	
		 products=regdao.AllProducts();
		 render();
	}
	public void render() throws IOException {
		try {
		Vbox v=(Vbox)this.getFellow("sample");

	     for(Iterator it = products.iterator();it.hasNext();) 
	     {
	    	 Hbox hb = new Hbox();
	    	for(int j = 0;j<5 && it.hasNext();j++) {
	    		
	    		
	    			final ProdBean rm= (ProdBean)it.next();
	    		
	    			
	    			Vbox vb = new Vbox();
	    			vb.setSclass("subdiv");
	    			Image ig = new Image();
	    			AImage aImage = new AImage(rm.getFilename(),rm.getImage());	
	    			ig.setSclass("align");
	    			ig.setContent(aImage);
	    			
	    			Label lb = new Label("Vehicle Name :"+rm.getVehicleName());
	    			lb.setSclass("prodname");	    			
	    			
	    			Label l=new Label("Vehicle Cost : "+String.valueOf(rm.getVehicleCost())+"₹");
	    			l.setSclass("prodname");
	    			
	    			Label m=new Label("Vehicle Year: "+String.valueOf(rm.getVehicleYear()));
	    			m.setSclass("prodname");
	    		
	    			Button b=new Button();
					b.setLabel("BUY");
					b.setSclass("buybutton");
					/* b.setAction("onClick");*/	    	
	    			b.addEventListener("onClick", new EventListener() {
	    				public void onEvent(Event e) throws Exception {
	    					Session sess=Sessions.getCurrent();
	    					sess.setAttribute("vehicleId", rm.getVehicleId());
							sess.setAttribute("vehicleType", rm.getVehicleType());
							sess.setAttribute("vehicleCost", rm.getVehicleCost());
							Window w = (Window) Executions.createComponents("/sales/Popupfile.zul", null, null);
							w.doModal();	    					
	    				}
	    			});	
	    			
	    			vb.appendChild(ig);
	    			vb.appendChild(lb);
	    			vb.appendChild(l);
	    			vb.appendChild(m);
	    			vb.appendChild(b);
	    			//Separator s=new Separator();
	    			//s.setWidth("300px");
	    			//s.setHeight("200px");
	    			
	    			hb.appendChild(vb);
	    		}
	    				v.appendChild(hb);
	    	
	    		}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
