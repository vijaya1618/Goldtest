package com.pennant.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Rating;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class FeedbackController extends Window{ 
	protected FeedbackDAO fd;
	
	public FeedbackDAO getFd() {
		return fd;
	}

	public void setFd(FeedbackDAO fd) {
		this.fd = fd;
	}

	public void feedControl() throws Exception {
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext(
					(ServletContext)getDesktop().getWebApp().getNativeContext());
			  fd= (FeedbackDAO)ctx.getBean("taskDAO2");
			  
			   Rating r=(Rating)this.getFellow("rate"); 
			   int t=r.getRating();
			   Textbox g=(Textbox)this.getFellow("description");
			   String h=g.getValue();
			   try {
				   Feedbackmodel fm=new Feedbackmodel();
				   fm.setRate(t);
				   fm.setDescription(h);
				   fd.feed(fm);
				   this.setAttribute("OK", Boolean.TRUE);
				   Clients.showNotification("Thanks For Your Valuable Feedback");
					this.detach();
			   }
			   catch(Exception e)
			   {
				   System.out.println(e);
			   }
			   
	}
	public void onCancel() {
		this.detach();
	}
	
}
