package com.pennant.admin;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

public class Feedbackctrl  extends Div{

	protected UserInterface userinterface;
	protected List feedlist;
	public List getFeedlist() {
		return feedlist;
	}
	public void setFeedlist(List feedlist) {
		this.feedlist = feedlist;
	}
	protected void render() {
		Listbox lb=(Listbox)this.getFellow("listfdbk");
		lb.getItems().clear();
		for(Iterator it = feedlist.iterator(); it.hasNext(); ) {
			Feedbackmodel fd=(Feedbackmodel)it.next();
			Listitem lt = new Listitem();
			lt.setValue(fd);
			lt.appendChild(new Listcell(String.valueOf(fd.getCustomerId())));
			lt.appendChild(new Listcell(String.valueOf(fd.getRating())));
			lt.appendChild(new Listcell((fd.getDescription())));
			lb.appendChild(lt);
		}
	}
	
	public void onCreate() throws Exception {
		//	spring bean, ItemDAO
		ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext(
				(ServletContext)getDesktop().getWebApp().getNativeContext());
		  userinterface = (UserInterface)ctx.getBean("taskDAO");
		
		feedlist=userinterface.feedbklist();
		render();
	}
	
}
