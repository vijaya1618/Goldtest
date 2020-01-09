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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Div;

import com.pennanttech.CustInfo.CustInfoBean;
import com.pennanttech.Register.RegistrationDao;
import com.pennanttech.emp.view.DownPayBean;

public class ViewScheduleCntrl extends Div {

	private static final long serialVersionUID = 1L;
	protected RegistrationDao regdao;

	protected List schedule1;

	protected void info() {
		Listbox lb = (Listbox) this.getFellow("schedule");
		lb.getItems().clear();

		for (Iterator it = (schedule1).iterator(); it.hasNext();) {
			DownPayBean db = (DownPayBean) it.next();

			Listitem lt = new Listitem();
			lt.setValue(db);
			lt.appendChild(new Listcell(String.valueOf(db.getPayment_Id())));
			lt.appendChild(new Listcell(String.valueOf(db.getEMIindex())));
			lt.appendChild(new Listcell(String.valueOf(db.getPayment_st_date())));
			lt.appendChild(new Listcell(String.valueOf(db.getPayment_pay_date())));
			lt.appendChild(new Listcell(String.valueOf(db.getBegining_balnce())));
			lt.appendChild(new Listcell(String.valueOf(db.getEMI())));
			lt.appendChild(new Listcell(String.valueOf(db.getPrinciple())));
			lt.appendChild(new Listcell(String.valueOf(db.getInterest())));
			lt.appendChild(new Listcell(String.valueOf(db.getRem_Balance())));
			lt.appendChild(new Listcell(db.getStatus()));

			lb.appendChild(lt);
		}

	}

	public void onCreate() throws Exception {
		// spring bean, taskDAO
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		regdao = (RegistrationDao) ctx.getBean("taskDAO");

		schedule1 = (List) regdao.AllSchedules();
		info();

	}
	public void change1() {
		int id = 0;

		Listbox lb = (Listbox) this.getFellow("schedule");
		lb.getItems().clear();
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		try {
			regdao = (RegistrationDao) ctx.getBean("taskDAO");
			Intbox i = (Intbox) this.getFellow("paymentid");
			id = i.getValue();
		} catch (NullPointerException ne) {

		}

		List<DownPayBean> result = regdao.change1(id);
		Iterator<DownPayBean> iterator = result.iterator();
		while (iterator.hasNext()) {
			DownPayBean dpb = iterator.next();
			Listitem lt = new Listitem();
			lt.appendChild(new Listcell(String.valueOf(dpb.getPayment_Id())));
			lt.appendChild(new Listcell(String.valueOf(dpb.getEMIindex())));
			lt.appendChild(new Listcell(String.valueOf(dpb.getPayment_st_date())));
			lt.appendChild(new Listcell(String.valueOf(dpb.getPayment_pay_date())));
			lt.appendChild(new Listcell(String.valueOf(dpb.getBegining_balnce())));
			lt.appendChild(new Listcell(String.valueOf(dpb.getEMI())));
			lt.appendChild(new Listcell(String.valueOf(dpb.getPrinciple())));
			lt.appendChild(new Listcell(String.valueOf(dpb.getInterest())));
			lt.appendChild(new Listcell(String.valueOf(dpb.getRem_Balance())));
			lt.appendChild(new Listcell(dpb.getStatus()));
		
			lb.appendChild(lt);

		}
	}
}
