package com.pennanttech.Empmodifications;

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

import com.pennanttech.Empmodifications.ViewPagebean;
import com.pennanttech.Register.RegistrationDao;

public class ViewEmpCntl  extends Div {

	private static final long serialVersionUID = 1L;
	protected RegistrationDao regdao;

	protected List employee;

	protected void empinfo() {
		Listbox lb = (Listbox) this.getFellow("employees");
		lb.getItems().clear();

		for (Iterator it = (employee).iterator(); it.hasNext();) {
			ViewPagebean vpb = (ViewPagebean) it.next();

			Listitem lt = new Listitem();
			lt.setValue(vpb);
			lt.appendChild(new Listcell(String.valueOf(vpb.getUserid())));
			lt.appendChild(new Listcell(vpb.getUsername() ));
			lt.appendChild(new Listcell(vpb.getFullname()));
			lt.appendChild(new Listcell(String.valueOf(vpb.getMobileno())));
			lt.appendChild(new Listcell(vpb.getGender()));
			lt.appendChild(new Listcell(vpb.getEmail()));
			lt.appendChild(new Listcell(vpb.getDepartment()));
			lt.appendChild(new Listcell(String.valueOf(vpb.getDob())));
			lb.appendChild(lt);
		}
	}

	public void onCreate() throws Exception {
		// spring bean, taskDAO
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		regdao = (RegistrationDao) ctx.getBean("taskDAO");

		employee = (List) regdao.AllEmployees();
		empinfo();

	}
}
