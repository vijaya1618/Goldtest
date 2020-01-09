package com.pennant.customer;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Space;
import org.zkoss.zul.Vbox;

public class Cartcontroller extends Div {
	private static final long serialVersionUID = 1L;
	protected CartDAO cart;
	private Session sess=null;
	int i;
	int price,totalp;
	protected List findp;
	public int gtotal;
	Hbox vb;
	
	public List getFindp() {
		return findp;
	}

	public void setFindp(List findp) {
		this.findp = findp;
	}

	protected void render() {
try {
		Vbox v = (Vbox) this.getFellow("items");
		

		for (Iterator it = findp.iterator(); it.hasNext();) {
		
			 Vbox v1=new Vbox();
			 
			Hbox h = new Hbox();
			
			Cartmodel cm = (Cartmodel) it.next();
			
			i=cm.getItemid();
			Label name = new Label(cm.getItemname());
			name.setSclass("name");
			h.setId("h"+i);
			v1.setId("v1"+i);
			final Button b=new Button(); 
			b.setWidth("3px"); 
			b.setHeight("3px");
			b.setLabel("-");
			b.setId(""+i);
			Space q=new Space();
			q.setWidth("10px");
			Label qty = new Label(String.valueOf(cm.getQty())); 
			qty.setId("q"+i);
			Space q1=new Space();
			q1.setWidth("10px");
			final Button b1=new Button(); 
			b1.setWidth("3px"); 
			b1.setHeight("3px");
			b1.setLabel("+"); 
			b1.setId("p"+i);
			Button remove=new Button();
			remove.setIconSclass("z-icon-trash-o");
			remove.setId("r"+i);
			Cell c=new Cell();
			
			c.setId("c"+i);
			
			Cell c1=new Cell();
			
			c1.setId("c1"+i);
			c.setWidth("180px");
			Space q2=new Space();
			q2.setWidth("50px");
			
			c.appendChild(name);
			
			c1.appendChild(b);
			c1.appendChild(q); 
			c1.appendChild(qty);
			c1.appendChild(q1);
			c1.appendChild(b1);
			c1.appendChild(q2);
			c1.appendChild(remove);
			
			h.appendChild(c);
			h.appendChild(c1);
			Hbox h1 = new Hbox();
			
			h.setId("h1"+i);
			Label price = new Label(String.valueOf(cm.getPrice())); 
			price.setId("Price"+i);
			Label totalprice= new Label(String.valueOf(cm.getTotalprice())); 
			totalprice.setId("TotalPrice"+i);
			totalprice.setSclass("align");
			Cell c2=new Cell();
			c2.setId("c2"+i);
			Space s1=new Space();
			s1.setWidth("150px");
			Space s2=new Space();
			s2.setWidth("130px");
			
			c2.appendChild(price);
			c2.appendChild(s1);
			c2.appendChild(totalprice);
			c2.appendChild(s2);
			
			h1.appendChild(c2);
			Space vbox=new Space();
			vbox.setWidth("100px");
			v1.appendChild(h);
			
			v1.appendChild(h1);
			v1.appendChild(vbox);
			v.appendChild(v1);
			Label Delivery=(Label)this.getFellow("Deliverycharges");
			Delivery.setValue("₹30");
			b.addEventListener(Events.ON_CLICK,new EventListener<Event>()
			{
				
	          
			public void onEvent(Event arg0) throws Exception {
			String a=(String)b.getId();
			int i=Integer.parseInt(a);
			minus(i);
			
		
			}
			});
			b1.addEventListener(Events.ON_CLICK,new EventListener<Event>()
			{
				
	          
			public void onEvent(Event arg0) throws Exception {
			String a=(String)b1.getId();
			String b=a.substring(1,4);
			int i=Integer.parseInt(b);
			plus(i);
			
		
			}
			});
			remove.addEventListener(Events.ON_CLICK,new EventListener<Event>()
			{
				
	          
			public void onEvent(Event arg0) throws Exception {
			String a=(String)b.getId();
			int i=Integer.parseInt(a);
			
			delete(i);
			cart.idelete(i);
			
		
			}
			});
			Label itp=(Label)this.getFellow("Item_Total");
			itp.setValue("₹"+String.valueOf(totalp));
		
			gtotal=totalp+30;
			Label gtp=(Label)this.getFellow("Grand_Total");
			gtp.setValue("₹"+String.valueOf(gtotal));
			
		}
}catch(Exception e) {
	
}
		
		

	}

	public void onCreate() throws Exception {
		// spring bean, ItemDAO
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		cart = (CartDAO) ctx.getBean("cartDAO");
		
		findp=cart.cartItemsd();
		totalp=cart.findtp();
		if(findp.isEmpty())
		{
			Vbox d=(Vbox)this.getFellow("cartitem");
			
			d.setVisible(false);
			Div div=(Div)this.getFellow("cart");
			Div div2=new Div();
			Hbox h=new Hbox();
			Vbox e=new Vbox();
			Label a=new Label("Your cart is empty");
			a.setSclass("cartlabel");
			Button b=new Button("Home");
			b.setSclass("cartbutton");
			e.appendChild(a);
			e.appendChild(b);
			e.setSclass("carte");
			h.appendChild(e);
			div2.appendChild(h);
			div.appendChild(div2);
			
			b.addEventListener(Events.ON_CLICK,new EventListener<Event>()
			{
				
	          
			public void onEvent(Event arg0) throws Exception {
			Executions.sendRedirect("/customer/customer.zul");
			
		
			}
			});
			
		}
		render();
		
		
		

	}
public void minus(int i) throws Exception
{
	Label qty=(Label)this.getFellow("q"+i);
	int min=Integer.parseInt((String)qty.getValue());
	
	if(min==1)
		min=1;
	else
	min--;
	String c=String.valueOf(min);
	qty.setValue(c);
	for (Iterator it = findp.iterator(); it.hasNext(); ) {
		
		Cartmodel cm=(Cartmodel)it.next();
		Label ctrlp = (Label) this.getFellow("Price"+i); 
	 price=Integer.parseInt((String)ctrlp.getValue());
 }
	int tp=min*price;
	Label ctrlp = (Label) this.getFellow("TotalPrice"+i); 
	ctrlp.setValue(String.valueOf(tp));
	Cartmodel item=new Cartmodel();
	item.setItemid(i);
	item.setQty(min);
	item.setTotalprice(tp);
	cart.update(item);
	totalp=cart.findtp();
	Label itp=(Label)this.getFellow("Item_Total");
	itp.setValue("₹"+String.valueOf(totalp));
	gtotal=totalp+30;
	Label gtp=(Label)this.getFellow("Grand_Total");
	gtp.setValue("₹"+String.valueOf(gtotal));
}
public void plus(int i) throws Exception
{
	Label qty=(Label)this.getFellow("q"+i);
	int min=Integer.parseInt((String)qty.getValue());
	min++;

	String c=String.valueOf(min);
	qty.setValue(c);
	for (Iterator it = findp.iterator(); it.hasNext(); ) {
		
		Cartmodel cm=(Cartmodel)it.next();
		Label ctrlp = (Label) this.getFellow("Price"+i); 
	 price=Integer.parseInt((String)ctrlp.getValue());
 }
	int tp=min*price;
	Label ctrlp = (Label) this.getFellow("TotalPrice"+i); 
	ctrlp.setValue(String.valueOf(tp));
	Cartmodel item=new Cartmodel();
	item.setItemid(i);
	item.setQty(min);
	item.setTotalprice(tp);
	cart.update(item);
	totalp=cart.findtp();
	Label itp=(Label)this.getFellow("Item_Total");
	itp.setValue("₹"+String.valueOf(totalp));
	gtotal=totalp+30;
	Label gtp=(Label)this.getFellow("Grand_Total");
	gtp.setValue("₹"+String.valueOf(gtotal));
}
public void payment()
{
	sess=Sessions.getCurrent();
	 sess.setAttribute("grandtotal",gtotal);
	 System.out.print(gtotal+"ghj");
	 System.out.print(sess.getAttribute("grandtotal"));
		Executions.sendRedirect("/customer/payment.zul");
}
public void items()
{
	
	Executions.sendRedirect("/customer/Restaurents.zul");
}
public void delete(int i)
{
	
	Vbox v = (Vbox) this.getFellow("items");
	Vbox v1 = (Vbox) this.getFellow("v1"+i);
	v.removeChild(v1);
	Executions.sendRedirect("/customer/cart.zul");
	

}
}
