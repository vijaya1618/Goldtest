package com.pennant.admin;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class blobretrivalcontrol extends Window  {
	CustomerprofileDAO EDAO;
	
	public void onCreate() {
		ApplicationContext ctx = WebApplicationContextUtils
    			.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
    	EDAO = (CustomerprofileDAO) ctx.getBean("taskDAO1");
    	try {
    			
    			ImageBean ib=EDAO.retrieve();
 
    			AImage aImage = new AImage("demo.jpg",ib.getB());	
    			Image img = (Image)this.getFellow("image");
    			img.setContent(aImage);
    			
    			
    		}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    	
	}
}
