package com.pennant.admin;

import java.util.Iterator;
import java.util.List;

    import javax.servlet.ServletContext;

	import org.springframework.context.ApplicationContext;
	import org.springframework.web.context.support.WebApplicationContextUtils;
	import org.zkoss.zul.Combobox;
    import org.zkoss.zul.Div;
    import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Longbox;
    import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
        public class Customerprofileeditwnd extends Window{
			protected CustomerprofileDAO cpd;
			protected Customerprofilemodel cp;

			
			
			private List values;
			
			
	/*
	 * public List getValues() { return values; } public void setValues(List values)
	 * { this.values = values; }
	 */
			
	/*
	 * protected List Findcustomer;
	 * 
	 * public List getFindcustomer() { return Findcustomer; } public void
	 * setFindcustomer(List findcustomer) { Findcustomer = findcustomer; }
	 */
			protected void render() {
				
				
			    
			    for (Iterator it = values.iterator(); it.hasNext(); ) {
				Customerprofilemodel cp=(Customerprofilemodel)it.next();
				Textbox ctrl = (Textbox) this.getFellow("fname"); 
				ctrl.setValue(cp.getFirstname());
				
				Textbox ctrlp = (Textbox) this.getFellow("lname"); 
				ctrlp.setValue(cp.getLastname());
				
				Textbox ctrls = (Textbox) this.getFellow("emailid"); 
				ctrls.setValue(cp.getEmail());
				
				Longbox ctrlt = (Longbox) this.getFellow("phoneno"); 
				ctrlt.setValue(cp.getPhonenumber());
				
			}
			
			}
			
			public void onCreate() throws Exception{
				
				ApplicationContext ctx = 
						WebApplicationContextUtils.getRequiredWebApplicationContext(
							(ServletContext)getDesktop().getWebApp().getNativeContext());
					cpd = (CustomerprofileDAO)ctx.getBean("taskDAO1");
					
					values=cpd.Findcustomer();
					render();
	}		
			public void onOK() throws Exception {
				cp=new Customerprofilemodel();
				//update
				Textbox ctrl = (Textbox) this.getFellow("fname"); 
					cp.setFirstname(ctrl.getValue());
				
				Textbox ctrlts = (Textbox) this.getFellow("lname"); 
				cp.setLastname(ctrlts.getValue());
				
				Textbox ctrle = (Textbox) this.getFellow("emailid"); 
				cp.setEmail(ctrle.getValue());
				
				Longbox ctrlp = (Longbox) this.getFellow("phoneno"); 
				cp.setPhonenumber(ctrlp.getValue());
					
				cpd.Customerupdate(cp);
				
				
				this.setAttribute("OK", Boolean.TRUE);
				this.detach();
			}
			public void onCancel() throws Exception{
				this.detach();
			}
		}