package com.pennanttech.Register;

import com.pennanttech.Finance.FullpayBean;
import com.pennanttech.Finance.PaymentEMI;
import com.pennanttech.Login.LoginModel;

import java.util.List;

import com.pennanttech.CustInfo.CustInfoBean;
import com.pennanttech.Empmodifications.ViewPagebean;
import com.pennanttech.Payments.PaymentBean;
import com.pennanttech.ProdInfo.ProdBean;
import com.pennanttech.emp.view.DownPayBean;

public interface RegistrationDao {
	public RegisterBean RegUser(RegisterBean rb);

	public RegisterBean LoginInsert(LoginModel lm);

	public PaymentEMI findById(PaymentEMI lb);

	public ViewPagebean ViewEmployees(ViewPagebean vp);

	public void delete(ViewPagebean vp) throws Exception;

	public FullpayBean fullPay(FullpayBean fpb);

	public FullpayBean downPay(FullpayBean fpb);

	public java.math.BigDecimal emical(FullpayBean fpb);

	public void deletestock(ViewPagebean vp1);

	// add stock
	public CustInfoBean CustReg(CustInfoBean cb);

	public String ProdAdd(ProdBean pb);

	public java.util.List<ProdBean> AllProducts() throws Exception;

	public java.util.List<PaymentBean> AllPayments() throws Exception;

	public java.util.List<DownPayBean> AllSchedules() throws Exception;

	public java.util.List<PaymentBean> change(int id);

	public java.util.List<DownPayBean> change1(int id);

	public FullpayBean VehicleDetails(FullpayBean fb);

	public void PayEMI(PaymentEMI lb);

	public ProdBean ViewProd1(ProdBean pb);

	public String produpdate(ProdBean pb);

	public String deletestock(ProdBean pb);

	public List<ViewPagebean> AllEmployees();

	public String empupdate(ViewPagebean vpb);

	public String deleteemp(ViewPagebean vpb);
	
	public RegisterBean Resetpassword(ResetPasswordBean rpb);

	ResetPasswordBean Updatepassword(ResetPasswordBean rb);

}
