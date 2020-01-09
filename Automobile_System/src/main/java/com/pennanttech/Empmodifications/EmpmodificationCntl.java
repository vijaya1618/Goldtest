package com.pennanttech.Empmodifications;
import org.zkoss.zul.Include;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.*;



public class EmpmodificationCntl extends SelectorComposer<Component> {
	
	@Wire
	private Include modifications;
	
	@Listen("onClick=#home")
	public void home()
	{
		modifications.setSrc("/stock/ViewStocks.zul");
	}
	
	
	@Listen("onClick=#addstock")
	public void submit()
	{
		modifications.setSrc("/stock/addstock.zul");
	}
	
	@Listen("onClick=#updatestock")
	public void subm()
	{
		modifications.setSrc("/stock/updatestock.zul");
	}
	@Listen("onClick=#viewstock")
	
	public void submit1()
	{
		modifications.setSrc("/stock/ViewStocks.zul");
	}
	
	@Listen("onClick=#deletestock")
	public void submi()
	{
		modifications.setSrc("/stock/deletestock.zul");
	}
	@Listen("onClick=#viewemployees")
	public void addemployees()
	{
		modifications.setSrc("/Employees/viewemployees.zul");
	}
	@Listen("onClick=#updateemployees")
	public void updateemployees()
	{
		modifications.setSrc("/Employees/updateemployees.zul");
	}
	@Listen("onClick=#deleteemployees")
	public void deleteemployees()
	{
		modifications.setSrc("/Employees/deleteemployees.zul");
	}
	
	@Listen("onClick=#logout")
	public void logout()
	{
		Session sess = Sessions.getCurrent();
        sess.removeAttribute("userid");
        sess.removeAttribute("username");
        sess.removeAttribute("vehicleId");
        sess.removeAttribute("vehicleType");
        sess.removeAttribute("vehicleCost");
        if(true)
        {
        	Executions.sendRedirect("../Login.zul");
        }
	}
 

}
