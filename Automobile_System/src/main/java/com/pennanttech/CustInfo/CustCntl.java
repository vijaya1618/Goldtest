package com.pennanttech.CustInfo;

import java.sql.Date;
import java.text.SimpleDateFormat;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Longbox;

import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Intbox;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Div;


import com.pennanttech.Register.RegistrationDao; 
 
public class CustCntl extends Div{
	
private static final long serialVersionUID = 1L;
	
protected RegistrationDao regdao;
protected CustInfoBean cb=new CustInfoBean ();
	
	//private Component custinfo;
	public void onCreate()
	{
		Session sess=Sessions.getCurrent();
		//accessing session variables
		cb.setVehicleId((Long) sess.getAttribute("vehicleId"));
		cb.setVehicleType((String) sess.getAttribute("vehicleType"));
		cb.setVehicleAmount((Long) sess.getAttribute("vehicleCost"));
		//setting  sessions to zul
		((Longbox)this.getFellow("VehicleId")).setValue(cb.getVehicleId());
		((Textbox)this.getFellow("VehicleType")).setValue(cb.getVehicleType());
		((Longbox)this.getFellow("VehicleAmount")).setValue(cb.getVehicleAmount());
		
		
	}
	
	public void submit() {
		
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		regdao=(RegistrationDao)ctx.getBean("taskDAO");	
		
		
		cb.setName(((Textbox)this.getFellow("name")).getValue());
	    cb.setGender(((Combobox)this.getFellow("gender")).getValue());
		cb.setMobileno1(((Longbox)this.getFellow("mobileno1")).getValue());
		cb.setMobileno2(((Longbox)this.getFellow("mobileno2")).getValue());
		cb.setProfession(((Textbox)this.getFellow("profession")).getValue());
	    cb.setNetIncome(((Longbox)this.getFellow("NetIncome")).getValue());
	    cb.setAadhar(((Longbox)this.getFellow("Aadhar")).getValue());
	    cb.setPAN(((Textbox)this.getFellow("PAN")).getValue());
	    cb.setAddress1(((Textbox)this.getFellow("Address1")).getValue());
	    cb.setAddress2(((Textbox)this.getFellow("Address2")).getValue());
	    cb.setVehicleId(((Longbox)this.getFellow("VehicleId")).getValue());
	    cb.setVehicleType(((Textbox)this.getFellow("VehicleType")).getValue());
	    cb.setVehicleAmount(((Longbox)this.getFellow("VehicleAmount")).getValue());
	   
	    
	    CustInfoBean res;		
		try {       
		        java.util.Date utilDate = ((Datebox)this.getFellow("dob")).getValue();
				SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
				final String stringDate= dateFormat.format(utilDate);
				
				Date sqlDate=Date.valueOf(stringDate);
					
				cb.setDob(sqlDate);
				res=regdao.CustReg(cb);
				Session sess= Sessions.getCurrent();
				sess.setAttribute("Customer_Info", res);
				if((res.getStatus()).equals("success"))
				{
					Executions.sendRedirect("Success.zul");
				}
				else
				{
					Messagebox.show(cb.getName() + "  "+res.getStatus()+" !");
				}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
    }
	public void cancel()
	{
		Executions.sendRedirect("SalesHome.zul");
	}

}

	


