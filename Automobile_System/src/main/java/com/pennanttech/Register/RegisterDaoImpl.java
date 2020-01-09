package com.pennanttech.Register;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.pennanttech.Finance.FullpayBean;
import com.pennanttech.Finance.PaymentEMI;
import com.pennanttech.Login.LoginModel;
import com.pennanttech.Payments.PaymentBean;
import com.pennanttech.ProdInfo.ProdBean;
import com.pennanttech.emp.view.DownPayBean;
import com.pennanttech.CustInfo.CustInfoBean;
import com.pennanttech.Empmodifications.*;

public class RegisterDaoImpl implements RegistrationDao {

	private JdbcTemplate jdbcTemplate;
	protected DataFieldMaxValueIncrementer taskIncer;
	Session sess = Sessions.getCurrent();

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setTaskIncer(DataFieldMaxValueIncrementer taskIncer) {
		this.taskIncer = taskIncer;
	}

	public RegisterBean RegUser(RegisterBean rb) {
		// TODO Auto-generated method stub

		int st = 0;
		try {
			String qry = "insert into Auto_Users(userid,username,fullname,password,email,Mobileno,dob,gender,dept,securityquestion,answer) values(next value for SEQ_USERID,?,?,?,?,?,?,?,?,?,?)";

			Object[] params = new Object[] { rb.getUsername(), rb.getFullname(), rb.getPassword(), rb.getEmail(),
					rb.getPhone(), rb.getDob(), rb.getGender(), rb.getDepartment(),rb.getSecurityquestion(),rb.getAnswer() };
			int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT,
					Types.DATE, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
			st = jdbcTemplate.update(qry, params, types);
			if (st == 1)
			{
				rb.setStatus("success");
			}
			else
			{
				rb.setStatus("fail");
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return rb;
	}

	public RegisterBean LoginInsert(LoginModel lm) {
		// TODO Auto-generated method stub
		RegisterBean users = null;
		try {
			String qry = "select userid,username,password,dept from Auto_Users where username=? and password=?";

			Object[] params = new Object[] { lm.getUsername(), lm.getPassword() };
			int types[] = new int[] { Types.VARCHAR, Types.VARCHAR };

			users = jdbcTemplate.query(qry, params, new ResultSetExtractor<RegisterBean>() {

				public RegisterBean extractData(ResultSet rs) throws SQLException, DataAccessException {

					RegisterBean usr = new RegisterBean();
					if (rs.next()) {
						usr.setUserid(rs.getInt("userid"));
						usr.setUsername(rs.getString("username"));
						usr.setPassword(rs.getString("password"));
						usr.setDepartment(rs.getString("dept"));
					}
					return usr;
				}
			});

		} catch (Exception e) {

			e.printStackTrace();
		}
		return users;
	}

	public PaymentEMI findById(PaymentEMI lb) {
		// TODO Auto-generated method stub
		String sql = "SELECT Payment_Id,Cust_id,Prod_id,EMI FROM Auto_Payment WHERE Cust_id = ?";
		Object[] params = new Object[] { lb.getCustomerid() };
		int types[] = new int[] { Types.INTEGER };
		List<PaymentEMI> ts = jdbcTemplate.query(sql, params, types, new TaskMapper());
		if (ts.isEmpty())
			return null;
		return (PaymentEMI) ts.get(0);
	}

	protected class TaskMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			PaymentEMI pe = new PaymentEMI();
			pe.setCustomerid(rs.getInt("Cust_id"));
			pe.setVehicleid(rs.getInt("Prod_id"));
			pe.setEmi(rs.getDouble("EMI"));
			pe.setPayment_id(rs.getInt("Payment_Id"));
			return pe;
		}
	}

	public ViewPagebean ViewEmployees(ViewPagebean vp) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Auto_Users WHERE userid = ?";
		System.out.println(vp.getUserid());
		Object[] params = new Object[] { vp.getUserid() };
		int types[] = new int[] { Types.INTEGER };
		List<ViewPagebean> ts = jdbcTemplate.query(sql, params, types, new EmpMapper());
		if (ts.isEmpty())
			return null;
		return (ViewPagebean) ts.get(0);
	}

	protected class EmpMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ViewPagebean pe = new ViewPagebean();
			pe.setUserid(rs.getInt("userid"));
			pe.setUsername(rs.getString("username"));
			pe.setFullname(rs.getString("fullname"));
			pe.setMobileno(rs.getLong("mobileno"));
			pe.setGender(rs.getString("gender"));
			pe.setEmail(rs.getString("email"));
			pe.setDepartment(rs.getString("dept"));
			pe.setDob(rs.getDate("DOB"));
			return pe;
		}
	}

	public void delete(ViewPagebean vp) throws Exception {
		String sql = "DELETE FROM Auto_users WHERE userid = ?";
		Object[] params = new Object[] { vp.getUserid() };
		int types[] = new int[] { Types.INTEGER };
		jdbcTemplate.update(sql, params, types);
	}

	public FullpayBean fullPay(FullpayBean fb) {
		int qr = 0;
		try {
			String qry = "insert into Auto_Payment(Payment_Id,Payment_Type,Cust_id,Prod_id,Remaining_Amt,Prod_cost,Loan_Tenure,RateOfInterest,payment_date,EMI,paid_amount) values(next value for SEQ_PAYMENTID,?,?,?,?,?,?,?,?,?,?)";
			Object[] params = new Object[] { fb.getPayment_type(), fb.getCustomerid(), fb.getVehicleid(),
					fb.getPrinciple_amount(), fb.getVehiclecost(), fb.getTenure(), fb.getRoi(), fb.getPaymentdate(),
					fb.getEMI(), fb.getPaidamount() };
			int types[] = new int[] { Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.DECIMAL, Types.DECIMAL,
					Types.INTEGER, Types.INTEGER, Types.DATE, Types.DECIMAL, Types.DECIMAL };
			qr = jdbcTemplate.update(qry, params, types);

			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("updatestock");
			SqlParameterSource in = new MapSqlParameterSource().addValue("Product_id", fb.getVehicleid());
			jdbcCall.execute(in);
			String sql="select Payment_id from Auto_Payment where Cust_id=? and Prod_id=?";
			Object[] param = new Object[] { fb.getCustomerid(),fb.getVehicleid()};
			int type[] = new int[] { Types.BIGINT, Types.BIGINT};
			long payment_id = jdbcTemplate.queryForObject(sql, param, type,Long.class);
			System.out.println(payment_id);
			fb.setPayment_id(payment_id);
			
		
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			
		}
		return fb;
	}

	public FullpayBean downPay(FullpayBean fb) {

		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("downpayment");
			SqlParameterSource in = new MapSqlParameterSource().addValue("Payment_type", fb.getPayment_type())
					.addValue("Cust_id", fb.getCustomerid()).addValue("Prod_id", fb.getVehicleid())
					.addValue("remaining_amt", fb.getPrinciple_amount()).addValue("Prod_cost", fb.getVehiclecost())
					.addValue("Loan_Tenure", fb.getTenure()).addValue("RateOfInterest", fb.getRoi())
					.addValue("payment_date", fb.getPaymentdate()).addValue("EMI", fb.getEMI())
					.addValue("paid_amount", fb.getPaidamount());

			jdbcCall.execute(in);
			String sql="select Payment_id from Auto_Payment where Cust_id=? and Prod_id=?";
			Object[] param = new Object[] { fb.getCustomerid(),fb.getVehicleid()};
			int type[] = new int[] { Types.BIGINT, Types.BIGINT};
			long payment_id = jdbcTemplate.queryForObject(sql, param, type,Long.class);
			fb.setPayment_id(payment_id);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			
		}
		return fb;
	}

	public BigDecimal emical(FullpayBean fpb) {
		// TODO Auto-generated method stub
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName("emical");
		SqlParameterSource in = new MapSqlParameterSource().addValue("p", fpb.getPrinciple_amount())
				.addValue("ri", fpb.getRoi()).addValue("nmi", fpb.getTenure());
		BigDecimal emi = jdbcCall.executeFunction(BigDecimal.class, in);
		return emi;
	}

	public void deletestock(ViewPagebean vp1) {
		String sql = "DELETE FROM Auto_Users WHERE userid = ?";
		Object[] params = new Object[] { vp1.getUserid() };
		int types[] = new int[] { Types.INTEGER };
		jdbcTemplate.update(sql, params, types);

	}

	public CustInfoBean CustReg(CustInfoBean cb) {

		int st = 0;
		try {
			Session sess = Sessions.getCurrent();
			int userid = (Integer)sess.getAttribute("userid");
			System.out.println("userid : "+userid);
			String qry = "insert into Auto_Customers (userid,Cust_id,Cust_name,Cust_Gender,Cust_Mobileno1,Cust_Mobileno2 ,Cust_dob ,Cust_Profession ,Cust_NetIncome ,Cust_Aadhar ,Cust_PAN ,Cust_Address1 ,Cust_Address2,Cust_Vehicleid,Cust_Vehicle_Type ,Cust_VehicleAmt ) values(?,next value for SEQ_CUSTID,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			Object[] params = new Object[] { userid, cb.getName(), cb.getGender(), cb.getMobileno1(), cb.getMobileno2(),
					cb.getDob(), cb.getProfession(), cb.getNetIncome(), cb.getAadhar(), cb.getPAN(), cb.getAddress1(),
					cb.getAddress2(), cb.getVehicleId(), cb.getVehicleType(), cb.getVehicleAmount() };
			int types[] = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.BIGINT,
					Types.DATE, Types.VARCHAR, Types.DECIMAL, Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
					Types.BIGINT, Types.VARCHAR, Types.DECIMAL };
			st = jdbcTemplate.update(qry, params, types);
			
			String sql="select Cust_id from Auto_Customers where userid=? and cust_name=?";
			Object[] param = new Object[] { userid, cb.getName()};
			int type[] = new int[] { Types.BIGINT, Types.VARCHAR};
			long cust_id = jdbcTemplate.queryForObject(sql, param, type,Long.class);
			
			
			cb.setCustomer_id(cust_id);
			if (st == 1)
				cb.setStatus("success");
			else
				cb.setStatus("fail");
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return cb;
	}

	public String ProdAdd(ProdBean pb) {
		int st = 0;

		try {
			String qry = "insert into Auto_Product(prod_id,prod_type,Prod_name ,prod_model ,prod_year,prod_cost,prod_color,prod_Quantity,prod_img,prod_img_filename)values(?,?,?,?,?,?,?,?,?,?)";

			Object[] params = new Object[] { pb.getVehicleId(), pb.getVehicleType(), pb.getVehicleName(),
					pb.getVehicleModel(), pb.getVehicleYear(), pb.getVehicleCost(), pb.getVehicleColor(),
					pb.getQuantity(), pb.getImage(), pb.getFilename() };
			int types[] = new int[] { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT,
					Types.DECIMAL, Types.VARCHAR, Types.BIGINT, Types.LONGVARBINARY, Types.VARCHAR };
			st = jdbcTemplate.update(qry, params, types);
			// System.out.println(st);
			if (st == 1)
				return "success";
			else
				return "fail";
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return e.getMessage();
		}
	}

	protected class AutoMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProdBean pb1 = new ProdBean();
			pb1.setVehicleId(rs.getInt("prod_id"));
			pb1.setVehicleType(rs.getString("prod_type"));
			pb1.setVehicleName(rs.getString("Prod_name"));
			pb1.setVehicleModel(rs.getString("prod_model"));
			pb1.setVehicleYear(rs.getLong("prod_year"));
			pb1.setVehicleCost(rs.getLong("prod_cost"));
			pb1.setVehicleColor(rs.getString("prod_color"));
			pb1.setQuantity(rs.getLong("prod_Quantity"));
			pb1.setImage(rs.getBytes("prod_img"));
			pb1.setFilename(rs.getString("prod_img_filename"));
			return pb1;
		}
	}

	public List AllProducts() throws Exception {
		String sql = "select * from  Auto_Product";
		List query = jdbcTemplate.query(sql, new AutoMapper());
		return (List) query;
	}

	protected class AutoPayMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			PaymentBean pb2 = new PaymentBean();
			pb2.setPayment_Id(rs.getInt("Payment_Id"));
			pb2.setPayment_Type(rs.getString("Payment_Type"));
			pb2.setCustomer_Id(rs.getInt("Cust_id"));
			pb2.setVehicle_Id(rs.getInt("Prod_id"));
			pb2.setVehicle_Amount(rs.getLong("Prod_cost"));
			pb2.setLoan_Tenure(rs.getInt("Loan_Tenure"));
			pb2.setRateOfInterest(rs.getInt("RateOfInterest"));
			pb2.setPrinciple_Amount(rs.getLong("paid_amount"));
			pb2.setPrinciple_Amount(rs.getLong("Remaining_Amt"));
			pb2.setPaymentDate(rs.getDate("payment_date"));
			pb2.setEMI_Money(rs.getLong("EMI"));
			return pb2;
		}
	}

	public List AllPayments() throws Exception {
		String sql = "select * from  Auto_Payment";
		return (List) jdbcTemplate.query(sql, new AutoPayMapper());
	}

	protected class AutoScheduleMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DownPayBean dpb = new DownPayBean();
			dpb.setPayment_Id(rs.getInt("Payment_Id"));
			dpb.setEMIindex(rs.getInt("EMIindex"));
			dpb.setPayment_st_date(rs.getDate("Payment_st_date"));
			dpb.setPayment_pay_date(rs.getDate("Payment_pay_date"));
			dpb.setBegining_balnce(rs.getDouble("Begining_balnce"));
			dpb.setEMI(rs.getBigDecimal("EMI"));
			dpb.setPrinciple(rs.getDouble("Principal"));
			dpb.setInterest(rs.getInt("intrest"));
			dpb.setRem_Balance(rs.getDouble("Rem_Balance"));
			dpb.setStatus(rs.getString("Status"));

			return dpb;
		}
	}

	public List<DownPayBean> AllSchedules() throws Exception {
		String sql = "select * from  Auto_Schedule";
		return (List) jdbcTemplate.query(sql, new AutoScheduleMapper());
	}

	public List<PaymentBean> change(int id) {

		List<PaymentBean> li = new ArrayList<PaymentBean>();
		StringBuilder sql = new StringBuilder("select * from Auto_Payment ");
		if (id != 0) {
			sql.append(" where Cust_id=" + id);

		}

		List<PaymentBean> li1 = (List<PaymentBean>) jdbcTemplate.query(sql.toString(), new AutoPayMapper());
		return li1;

	}

	public List<DownPayBean> change1(int id) {
		List<DownPayBean> li = new ArrayList<DownPayBean>();
		StringBuilder sql = new StringBuilder("select * from Auto_Schedule");
		if (id != 0) {
			sql.append(" where Payment_Id=" + id);

		}

		List<DownPayBean> li2 = (List<DownPayBean>) jdbcTemplate.query(sql.toString(), new AutoScheduleMapper());
		return li2;

	}

	public FullpayBean VehicleDetails(FullpayBean fb) {
		// TODO Auto-generated method stub
		String sql = "select * from Auto_Customers where Cust_id=?";
		Object[] params = new Object[] { fb.getCustomerid() };
		int types[] = new int[] { Types.INTEGER };
		List<FullpayBean> fb1 = jdbcTemplate.query(sql, params, types, new VehicleDetailsMapper());
		return (FullpayBean) fb1.get(0);
	}

	protected class VehicleDetailsMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			FullpayBean fb = new FullpayBean();
			fb.setVehicleid(rs.getInt("Cust_Vehicleid"));
			fb.setVehicleType(rs.getString("Cust_Vehicle_Type"));
			fb.setVehiclecost(rs.getDouble("Cust_VehicleAmt"));
			return fb;
		}
	}

	public void PayEMI(PaymentEMI lb) {
		// TODO Auto-generated method stub
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("EMI_PAY");
		SqlParameterSource in = new MapSqlParameterSource().addValue("customerid", lb.getCustomerid())
				.addValue("payment_id", lb.getPayment_id()).addValue("emi", lb.getEmi())
				.addValue("paymentdate", lb.getPaymentdate());
		jdbcCall.execute(in);
	}

	public ProdBean ViewProd1(ProdBean pb) {
		String sql = "SELECT * FROM Auto_Product WHERE prod_id = ?";
		System.out.println(pb.getVehicleId());
		Object[] params = new Object[] { pb.getVehicleId() };
		int types[] = new int[] { Types.INTEGER };
		List<ProdBean> ts = jdbcTemplate.query(sql, params, types, new AutoMapper());
		if (ts.isEmpty())
			return null;
		return (ProdBean) ts.get(0);
	}

	public String produpdate(ProdBean pb) {
		String sql = "UPDATE Auto_Product SET prod_type =?,Prod_name =?,prod_model =?,prod_year =?,prod_cost =?,prod_color =?,prod_Quantity =? WHERE prod_id =?";
		Object[] params = new Object[] { pb.getVehicleType(), pb.getVehicleName(), pb.getVehicleModel(),
				pb.getVehicleYear(), pb.getVehicleCost(), pb.getVehicleColor(), pb.getQuantity(), pb.getVehicleId() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.DECIMAL,
				Types.VARCHAR, Types.BIGINT, Types.INTEGER };
		int i=jdbcTemplate.update(sql, params, types);
		if(i==1)
			return "success";
		else
			return "fail";

	}

	public String deletestock(ProdBean pb) {
		String sql = "DELETE FROM Auto_Product WHERE prod_id= ?";
		Object[] params = new Object[] { pb.getVehicleId() };
		int types[] = new int[] { Types.INTEGER };
		int res=jdbcTemplate.update(sql, params, types);
		if(res==1)
			return "success";
		else
			return "fail";
		

	}

	public List<ViewPagebean> AllEmployees() {
		String sql = "select * from  Auto_Users";
		return (List) jdbcTemplate.query(sql, new EmpMapper());
	}

	public String empupdate(ViewPagebean vpb) {
		String sql = "UPDATE Auto_Users SET username = ?,fullname = ?,Mobileno = ?,Gender =?,Email = ?,Dept = ?,DOB=? WHERE userid =?";
		Object[] params = new Object[] { vpb.getUsername(), vpb.getFullname(), vpb.getMobileno(), vpb.getGender(),
				vpb.getEmail(), vpb.getDepartment(),vpb.getDob(), vpb.getUserid()};
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR,Types.DATE,Types.INTEGER };
		int i=jdbcTemplate.update(sql, params, types);
		if(i==1)
			return "success";
		else
			return "fail";

	}

	public String deleteemp(ViewPagebean vpb) {
		String sql = "DELETE FROM Auto_Users WHERE userid= ?";
		Object[] params = new Object[] { vpb.getUserid() };
		int types[] = new int[] { Types.INTEGER };
		int res=jdbcTemplate.update(sql, params, types);
		if(res==1)
			return "success";
		else
		return "fail"; 
	}
	
	public RegisterBean Resetpassword(ResetPasswordBean rpb)
	{
		         RegisterBean users=null;
		      try {
			
		
		String qry = "select email,answer from Auto_Users where email=? and answer=?";
		
		
		Object[] params = new Object[] {rpb.getEmail(),rpb.getAnswer()};
		int types[] = new int[] { Types.VARCHAR,Types.VARCHAR};  
		
		
		 users = jdbcTemplate.query(qry,params,new ResultSetExtractor<RegisterBean>(){ 
	           public RegisterBean extractData(ResultSet rs) throws SQLException, DataAccessException {
	        	   
		           RegisterBean usr = new RegisterBean();
	
		             if(rs.next()){  
	                      
	                        usr.setEmail(rs.getString("email"));
	                       
	                        usr.setAnswer(rs.getString("answer"));
	                        Session sess = Sessions.getCurrent();
	                        sess.setAttribute("mail", rs.getString("email"));
	                       
	                         }  
		 
		             		return usr;
		                                  }
		 
		                       });
		                           }
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		
		}
		
		return users;
	
	}
	       public ResetPasswordBean Updatepassword(ResetPasswordBean rb)
	       {
	    	      Session sess = Sessions.getCurrent();
	    	       String s=(String)sess.getAttribute("mail");
	    	       System.out.println(sess.getAttribute("mail"));
	    	   String sql = "UPDATE Auto_Users SET password = ? WHERE email =?";
	    	   Object[] params = new Object[] { rb.getPassword(),s};
	   		   int types[] = new int[] { Types.VARCHAR,Types.VARCHAR};
	   		   int i=jdbcTemplate.update(sql, params, types);
	   		   if(i==1)
	   		   {
	   			   rb.setStatus("success");
	   		   }else
	   		   {
	   			rb.setStatus("fail");
	   		   }
	           return rb;
	       
	       }

}
