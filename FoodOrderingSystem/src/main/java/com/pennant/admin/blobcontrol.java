package com.pennant.admin;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
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


public class blobcontrol extends Window {
	CustomerprofileDAO EDAO;
	Media media;
	


	  public void onUploadPDF() {
		  
	        EventListener<UploadEvent> el = new EventListener<UploadEvent>() {
	        	
	        	public void onEvent(UploadEvent event) throws Exception {
	            	ApplicationContext ctx = WebApplicationContextUtils
	            			.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
	            	EDAO = (CustomerprofileDAO) ctx.getBean("taskDAO1");
	            	Media m = event.getMedia();
	            	System.out.println(1);
	            	byte[] b  = m.getByteData();
	            	System.out.println("going");
	                EDAO.file(b);
	                System.out.println("done");
	                
	                //;Filedownload.save(EDAO.retrieve(), EDAO.retrieve().toString(), "Resume.pdf")
	            }
	        };
	      
	        Fileupload.get(el);
	        
	        System.out.println("m.getName()");
	    }
   }

	
	

