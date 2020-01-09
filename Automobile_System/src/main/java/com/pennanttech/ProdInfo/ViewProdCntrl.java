package com.pennanttech.ProdInfo;

import java.util.List;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.zkoss.image.AImage;
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
import org.zkoss.zul.Image;

import com.pennanttech.CustInfo.CustInfoBean;
import com.pennanttech.Register.RegistrationDao;

public class ViewProdCntrl extends Div {

	private static final long serialVersionUID = 1L;
	protected RegistrationDao regdao;

	protected List products;

	protected void info() throws IOException {
	Listbox lb = (Listbox) this.getFellow("vehicles");
		lb.getItems().clear();

		for (Iterator it = (products).iterator(); it.hasNext();) {
			ProdBean pb1 = (ProdBean) it.next();

			Listitem lt = new Listitem();
			lt.setValue(pb1);
			lt.appendChild(new Listcell(String.valueOf(pb1.getVehicleId())));
			lt.appendChild(new Listcell(pb1.getVehicleType()));
			lt.appendChild(new Listcell(pb1.getVehicleName()));
			lt.appendChild(new Listcell(pb1.getVehicleModel()));
			lt.appendChild(new Listcell(String.valueOf(pb1.getVehicleYear())));
			lt.appendChild(new Listcell(String.valueOf(pb1.getVehicleCost())));
			lt.appendChild(new Listcell(pb1.getVehicleColor()));
			lt.appendChild(new Listcell(String.valueOf(pb1.getVehicleCost())));
			lb.appendChild(lt);
		}
		
	}

	public void onCreate() throws Exception {
		// spring bean, taskDAO
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		regdao = (RegistrationDao) ctx.getBean("taskDAO");

		products = (List) regdao.AllProducts();
		info();

	}
}
