package com.pennanttech.Payments;

import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Longbox;

import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Div;
import com.pennanttech.Register.RegistrationDao;
import com.pennanttech.Payments.PaymentBean;
import com.pennanttech.ProdInfo.ProdBean;

public class ViewPaymentsCntrl extends Div {

	private static final long serialVersionUID = 1L;
	protected RegistrationDao regdao;

	protected List payment;

	protected void pay() {
		Listbox lb = (Listbox) this.getFellow("payments");
		lb.getItems().clear();

		for (Iterator it = (payment).iterator(); it.hasNext();) {
			PaymentBean pb2 = (PaymentBean) it.next();

			Listitem lt = new Listitem();
			lt.setValue(pb2);
			lt.appendChild(new Listcell(String.valueOf(pb2.getPayment_Id())));
			lt.appendChild(new Listcell(pb2.getPayment_Type()));
			lt.appendChild(new Listcell(String.valueOf(pb2.getCustomer_Id())));
			lt.appendChild(new Listcell(String.valueOf(pb2.getVehicle_Id())));
			lt.appendChild(new Listcell(String.valueOf(pb2.getVehicle_Amount())));
			lt.appendChild(new Listcell(String.valueOf(pb2.getLoan_Tenure())));
			lt.appendChild(new Listcell(String.valueOf(pb2.getRateOfInterest())));
			lt.appendChild(new Listcell(String.valueOf(pb2.getPaid_Amount())));
			lt.appendChild(new Listcell(String.valueOf(pb2.getPrinciple_Amount())));
			lt.appendChild(new Listcell(String.valueOf(pb2.getPaymentDate())));
			lt.appendChild(new Listcell(String.valueOf(pb2.getEMI_Money())));

			lb.appendChild(lt);
		}
	}

	public void onCreate() throws Exception {
		// spring bean, taskDAO
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		regdao = (RegistrationDao) ctx.getBean("taskDAO");

		payment = (List) regdao.AllPayments();
		pay();
	}

	public void change() {
		int id = 0;

		Listbox lb = (Listbox) this.getFellow("payments");
		lb.getItems().clear();
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		try {
			regdao = (RegistrationDao) ctx.getBean("taskDAO");
			Intbox i = (Intbox) this.getFellow("customerid");
			id = i.getValue();
		} catch (NullPointerException ne) {

		}

		List<PaymentBean> result = regdao.change(id);
		Iterator<PaymentBean> iterator = result.iterator();
		while (iterator.hasNext()) {
			PaymentBean ptb = iterator.next();
			Listitem lt = new Listitem();
			lt.appendChild(new Listcell(String.valueOf(ptb.getPayment_Id())));
			lt.appendChild(new Listcell(ptb.getPayment_Type()));
			lt.appendChild(new Listcell(String.valueOf(ptb.getCustomer_Id())));
			lt.appendChild(new Listcell(String.valueOf(ptb.getVehicle_Id())));
			lt.appendChild(new Listcell(String.valueOf(ptb.getVehicle_Amount())));
			lt.appendChild(new Listcell(String.valueOf(ptb.getLoan_Tenure())));
			lt.appendChild(new Listcell(String.valueOf(ptb.getRateOfInterest())));
			lt.appendChild(new Listcell(String.valueOf(ptb.getPaid_Amount())));
			lt.appendChild(new Listcell(String.valueOf(ptb.getPrinciple_Amount())));
			lt.appendChild(new Listcell(String.valueOf(ptb.getPaymentDate())));
			lt.appendChild(new Listcell(String.valueOf(ptb.getEMI_Money())));
		
			lb.appendChild(lt);

		}
	}
	
	
}
