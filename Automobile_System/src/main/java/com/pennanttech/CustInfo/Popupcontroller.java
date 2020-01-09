package com.pennanttech.CustInfo;

import org.zkoss.zk.ui.*;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

public class Popupcontroller extends SelectorComposer<Component>{
	
@Wire
private Window wincheck;
@Wire
private Checkbox checkboxes;


@Listen("onClick=#subpopup")	
public void submit()
{
   if(checkboxes.isChecked()) {
   Window w=(Window)Executions.createComponents("/sales/CustInfo.zul", null,null);
   w.doModal();
   }
   else
   {
	   Messagebox.show("Please Accept the Terms and Conditions");
   }
   
}

@Listen("onClick=#subpopup1")	
public void cancel()
{
	wincheck.detach();
}
	
}
	

 