package com.pennanttech.ProdInfo;

import java.io.FileNotFoundException;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;



import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Longbox;

import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Intbox;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;

import com.pennanttech.ProdInfo.ProdBean;
import com.pennanttech.Register.RegistrationDao;

public class ProdCntrl extends Div {

	private static final long serialVersionUID = 1L;

	protected RegistrationDao regdao;

	private Component prodinfo;
	private ProdBean pb = new ProdBean();

	public void add() {

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		regdao = (RegistrationDao) ctx.getBean("taskDAO");

	
		pb.setVehicleId(((Intbox) this.getFellow("vehicle_id")).getValue());
		pb.setVehicleType(((Combobox) this.getFellow("Vehicle_Type")).getValue());
		pb.setVehicleName(((Textbox) this.getFellow("vehicle_name")).getValue());
		pb.setVehicleModel(((Textbox) this.getFellow("vehiclemodel")).getValue());
		pb.setVehicleYear(((Longbox) this.getFellow("vehicle_year")).getValue());
		pb.setVehicleCost(((Longbox) this.getFellow("vehicle_cost")).getValue());
		pb.setVehicleColor(((Combobox) this.getFellow("VehicleColor")).getValue());
		pb.setQuantity(((Longbox) this.getFellow("Quantity")).getValue());
		String res;
	       			try {
	       				res = regdao.ProdAdd(pb);
	       				if (res.equals("success")) {
	       					Messagebox.show("Stock added successfully", "Confirm Dialog",Messagebox.OK , Messagebox.INFORMATION,new org.zkoss.zk.ui.event.EventListener() {
	       						public void onEvent(Event evt) throws InterruptedException {
	       							if (evt.getName().equals("onOK")) {
	       								
	       								
	       								Executions.sendRedirect("/admin/admin1.zul");
	       								
	       								
	       							}
	       						
	       						
	       						}
	       						
	    
	       						});
	       					}
	       			
	       				
	       				 else {
	       					Messagebox.show(pb.getVehicleName() + "  " + res + " !");
	       				}

	       			} catch (Exception e) {
	       				e.printStackTrace();
	       			}
	}
	
	public void fileUpload() throws FileNotFoundException
	{
	        EventListener<UploadEvent> el = new EventListener<UploadEvent>() {
	            public void onEvent(UploadEvent ev) throws FileNotFoundException {
	               Media m = ev.getMedia();
	               byte[] b=m.getByteData();
	               pb.setImage(b);
	               pb.setFilename(m.getName());            	 	               
	            }
	        };
	      
	        Fileupload.get(el);
		
	}
	
	public void view() {
		try {

			ApplicationContext ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext((ServletContext) (getDesktop()).getWebApp().getNativeContext());
			regdao = (RegistrationDao) ctx.getBean("taskDAO");

			ProdBean pb = new ProdBean();
			pb.setVehicleId(((Intbox) this.getFellow("vehicle_id")).getValue());
			System.out.println(((Intbox) this.getFellow("vehicle_id")).getValue());

			ProdBean pb1 = regdao.ViewProd1(pb);

			((Textbox) this.getFellow("Vehicle_Type")).setValue(pb1.getVehicleType());
			((Textbox) this.getFellow("vehicle_name")).setValue(pb1.getVehicleName());
			((Textbox) this.getFellow("vehiclemodel")).setValue(pb1.getVehicleModel());
			((Longbox) this.getFellow("vehicle_year")).setValue(pb1.getVehicleYear());
			((Longbox) this.getFellow("vehicle_cost")).setValue(pb1.getVehicleCost());
			((Textbox) this.getFellow("VehicleColor")).setValue(pb1.getVehicleColor());
			((Longbox) this.getFellow("Quantity")).setValue(pb1.getQuantity());

		} catch (Exception e) {
			System.out.println(e);
		                 }
	}

	
	public void update() {

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		regdao = (RegistrationDao) ctx.getBean("taskDAO");

		ProdBean pb = new ProdBean();

		Combobox type = (Combobox) this.getFellow("Vehicle_Type");
		String vehtype = type.getValue();

		Textbox name = (Textbox) this.getFellow("vehicle_name");
		String vehname = name.getValue();

		Textbox model = (Textbox) this.getFellow("vehiclemodel");
		String vehmodel = model.getValue();

		Longbox year = (Longbox) this.getFellow("vehicle_year");
		long vehyear = year.getValue();

		Longbox cost = (Longbox) this.getFellow("vehicle_cost");
		long vehcost = cost.getValue();

		Combobox color = (Combobox) this.getFellow("VehicleColor");
		String vehcolor = color.getValue();

		Longbox quantity = (Longbox) this.getFellow("Quantity");
		long vehquantity = quantity.getValue();

		Intbox id = (Intbox) this.getFellow("vehicle_id");
		int vehid = id.getValue();

		pb.setVehicleType(vehtype);
		pb.setVehicleName(vehname);
		pb.setVehicleModel(vehmodel);
		pb.setVehicleYear(vehyear);
		pb.setVehicleCost(vehcost);
		pb.setVehicleColor(vehcolor);
		pb.setQuantity(vehquantity);
		pb.setVehicleId(vehid);

		String res=regdao.produpdate(pb);
		if(res=="success")
		{
			Messagebox.show("stock updated successfully","Click on ok",	Messagebox.OK , Messagebox.INFORMATION,
			new org.zkoss.zk.ui.event.EventListener() {
				public void onEvent(Event evt) throws InterruptedException {
					if (evt.getName().equals("onOK")) {
   
						Executions.sendRedirect("/admin/admin1.zul");
			
			
			
		}
		
				}
				
			}
		
				);
		}

	}
	public void deleteStock() throws Exception {

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) (getDesktop()).getWebApp().getNativeContext());

		regdao = (RegistrationDao) ctx.getBean("taskDAO");
		ProdBean pb = new ProdBean();
		System.out.println("entered");

		Intbox i = (Intbox) this.getFellow("vehicle_id");
		int i2 = i.getValue();
		System.out.println(i2);
		pb.setVehicleId(i2);

		// System.out.println(
		// vp1.setUserid(((Intbox)this.getFellow("userid")).getValue()));
		String res=regdao.deletestock(pb);
		System.out.println(res);
		
		if (res.equals("success")) {
			
				Messagebox.show("Stock deleted successfully", "Confirm Dialog",Messagebox.OK , Messagebox.INFORMATION,
				new org.zkoss.zk.ui.event.EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onOK")) {
	   
							Executions.sendRedirect("/admin/admin1.zul");
				
					
						}
						
					}
					
				}
			);
			}

		}
}
		
		

		         
		
		
		
		
		
		
		
		
		
		
		


