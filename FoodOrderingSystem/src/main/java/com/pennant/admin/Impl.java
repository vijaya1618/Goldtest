package com.pennant.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.math.BigDecimal;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.pennant.admin.Menumodel;
import com.pennant.admin.Impl.TaskMapper;

public class Impl implements UserInterface {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	protected DataFieldMaxValueIncrementer taskIncer;
	private Session sess = null;
	String z;
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setTaskIncer(DataFieldMaxValueIncrementer taskIncer) {
		this.taskIncer = taskIncer;
	}

	/*
	 * protected class LoginMapper implements RowMapper { public Object
	 * mapRow(ResultSet rs, int rowNum) throws SQLException { loginmodel lm = new
	 * loginmodel(); lm.setPassword(rs.getString("password"));
	 * 
	 * return lm; }
	 * 
	 * }
	 */
	public int validuser(model m) {
		// TODO Auto-generated method stub
		String f = m.getFirstname();
		String l = m.getLastname();
		String e = m.getEmail();
		long p = m.getPhone();
		String pa = m.getPassword();
		String cpa = m.getCnfpassword();
		
		String a = m.getAnswers();
		if (pa.equals(cpa)) {
			String sql = "insert into customers values(next value for custseq,?,?,?,?,?,?)";
			Object[] params = new Object[] { f, l, e, pa, p, a };
			int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT,
					Types.VARCHAR };
			jdbcTemplate.update(sql, params, types);
			Executions.sendRedirect("login.zul");
			
		} else {
			// Executions.sendRedirect("passwordmistake.zul");
			Clients.showNotification("password must match");
		}
		return 0;
	}

	public void validLogin(loginmodel lm) {

		String a = lm.getEmail();
		String p = lm.getPassword();
		try {
		String sql = "select password from customers where email=?";
		Object[] params = new Object[] { a };
		int types[] = new int[] { Types.VARCHAR };
		String password = jdbcTemplate.queryForObject(sql, params, types, String.class);
		String sql1 = "select cust_id from customers where email=?";
		Object[] params1 = new Object[] { a };
		int types1[] = new int[] { Types.VARCHAR };
		int cid = jdbcTemplate.queryForObject(sql1, params1, types1, Integer.class);
		
		if (a.equals("sai@gmail.com")) {
			if (password.isEmpty()) {
				sess.setAttribute("stat", "no");
				Executions.sendRedirect("login.zul");
			}
			if (p.equals(password) ) {
				sess = Sessions.getCurrent();
				System.out.println(a);
				sess.setAttribute("userid", cid);

				sess.setAttribute("stat", "yes");
				Executions.sendRedirect("/Admin/Adminpage.zul");
			} else {
				Clients.showNotification("Email or Password is Invalid ", true);
				sess.setAttribute("stat", "no");
				

			}
		} else {
			if (password.isEmpty()) {
				sess.setAttribute("stat", "no");
				Executions.sendRedirect("login.zul");
			}

			if (p.equals(password) ) {
				sess = Sessions.getCurrent();
				System.out.println(a);
				sess.setAttribute("userid", cid);

				sess.setAttribute("stat", "yes");
				Executions.sendRedirect("/customer/customer.zul");
			} else {
				Clients.showNotification("Email or Password is Invalid ", true);
				sess.setAttribute("stat", "no");
				

			}
		}}
		catch(Exception e)
		{
			Clients.showNotification("Email or Password is Invalid ", true);
		}
		// Stream password1=(stream)password.stream();
		// String password2=(stream)password.stream();

		// return 0;
	}
	public void forgetS(model m) throws Exception
	{
/*		String a=m.getEmail();*/
		
		
		String j=m.getAnswers();
		System.out.println(j);
		Session sess=Sessions.getCurrent();
		
		z=(String)sess.getAttribute("email");
		
		String sql="select securityanswer from customers where email=?";
		Object[] params = new Object[] { z };
		int types[] = new int[] { Types.VARCHAR};
		String ans=jdbcTemplate.queryForObject(sql, params, types,String.class);
		if(ans.equals(j))
		{
			Window win = (Window)Executions.createComponents("password.zul", null, null);
			win.doModal();
	
			
		}
		else
		{
			Executions.sendRedirect("login.zul");
		}
	}
	public void passs(model m)
	{
		String t=m.getPassword(); 
		System.out.println(t);
		String l=m.getCnfpassword();
		Session sess=Sessions.getCurrent();
		String j=(String)sess.getAttribute("email");
		if(t.equals(l))
		{	
			String sql="update customers set password=? where email=?";
			Object[] params = new Object[] { t,j};
			int types[] = new int[] { Types.VARCHAR,Types.VARCHAR};
			jdbcTemplate.update(sql, params, types);
			Executions.sendRedirect("login.zul");
		
		}
		else
		{
			/*Executions.sendRedirect("ForgetPassword.zul");*/
			Clients.showNotification("password must match");
		}
		
	}
	public int Resadd(Restmodel rm) {
		String n = rm.getRestaurantName();
		long p = rm.getPhoneNumber();
		String l = rm.getLocation();
		byte[] r = rm.getImage();
		int i=0;
		try {
		String sql = "insert into restuarents values(next value for resseq,?,?,?,?)";
		Object[] params = new Object[] { n, p, l, r };
		int types[] = new int[] { Types.VARCHAR, Types.BIGINT, Types.VARCHAR, Types.LONGVARBINARY };
		i=jdbcTemplate.update(sql, params, types);
		
		return i;
		}
		catch(Exception e)
		{
			return 0;
		}
		

	}

	public int menAdd(Menumodel mm) {

		long r = mm.getRestaurantID();
		String mn = mm.getMenuName();
		int j=0;
		String sql = "insert into menu values(?,next value for menuseq,?)";
		Object[] params = new Object[] { r, mn };
		int types[] = new int[] { Types.SMALLINT, Types.VARCHAR };
		j=jdbcTemplate.update(sql, params, types);
		return j;
	}

	public int itemAdd(Item im) {

		int mid = im.getMenuID();
		String itname = im.getItemName();
		int ip = im.getItemPrice();
		String itav = im.getItemAvailability();
		int k=0;
		String sql = "insert into items values(next value for itemseq,?,?,?,?)";
		Object[] params = new Object[] { itname, ip, itav, mid };
		int types[] = new int[] { Types.VARCHAR, Types.DECIMAL, Types.VARCHAR, Types.SMALLINT };
		k=jdbcTemplate.update(sql, params, types);
		return k;
	}

	protected class TaskMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Menumodel t = new Menumodel();
			t.setRestaurantID(rs.getInt("r_id"));
			t.setMenuName(rs.getString("menu_name"));
			t.setMenuid(rs.getInt("menu_id"));

			return t;
		}
	}

	protected class TaskRest implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Restmodel t = new Restmodel();
			t.setRestaurantid(rs.getInt("r_id"));
			t.setRestaurantName(rs.getString("rname"));
			t.setLocation(rs.getString("location"));
			t.setPhoneNumber(rs.getLong("phonenumber"));
			return t;
		}
	}

	protected class TaskupRest implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Restmodel td = new Restmodel();
			td.setRestaurantid(rs.getInt("r_id"));
			td.setRestaurantName(rs.getString("rname"));
			td.setPhoneNumber(rs.getLong("phonenumber"));
			td.setLocation(rs.getString("location"));
			return td;
		}
	}

	protected class TaskCust implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Custmodel cm = new Custmodel();
			cm.setCust_id(rs.getInt("cust_id"));
			cm.setFirstname(rs.getString("fname"));
			cm.setLastname(rs.getString("lname"));
			cm.setEmail(rs.getString("email"));
			cm.setPhone(rs.getLong("phoneno"));
			return cm;
		}
	}

	public void delete(Menumodel t) {
		String sql = "DELETE FROM menu WHERE r_id = ?";
		Object[] params = new Object[] { t.getRestaurantID() };
		int types[] = new int[] { Types.INTEGER };
		jdbcTemplate.update(sql, params, types);
	}

	public List findAll() throws Exception {
		String sql = "SELECT * FROM  menu";
		return jdbcTemplate.query(sql, new TaskMapper());
	}
	public List findByRid(int rid) throws Exception
	{
		String sql="select * from menu where r_id=?";
		Object[] params = new Object[] { rid };
		int types[] = new int[] { Types.INTEGER };
		return jdbcTemplate.query(sql, params, types,new TaskMapper());
		
	}
	

	public List findRest() throws Exception {
		String sql = "SELECT * FROM  restuarents";
		return jdbcTemplate.query(sql, new TaskRest());
	}

	public Restmodel RestUpdate(Restmodel td) {

		String sql = "update restuarents set rname = ?, phonenumber= ?,location=? where r_id=?";
		Object[] params = new Object[] { td.getRestaurantName(), td.getPhoneNumber(), td.getLocation(),
				td.getRestaurantid() };
		int types[] = new int[] { Types.VARCHAR, Types.BIGINT, Types.VARCHAR, Types.INTEGER };
		jdbcTemplate.update(sql, params, types);

		return td;

	}

	public void deleter(Restmodel td) {
		String sql = "DELETE FROM restuarents WHERE r_id = ?";
		Object[] params = new Object[] { td.getRestaurantid() };
		int types[] = new int[] { Types.INTEGER };
		jdbcTemplate.update(sql, params, types);
	}

	public List showCust() {
		String sql = "SELECT cust_id,fname,lname,email,phoneno FROM  customers";
		return jdbcTemplate.query(sql, new TaskCust());
	}

	public List feedbklist() {
		String sql = "SELECT * FROM feedback";
		return jdbcTemplate.query(sql, new TaskFeed());
	}

	protected class TaskFeed implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Feedbackmodel f = new Feedbackmodel();
			f.setCustomerId(rs.getInt("cust_id"));
			f.setRating(rs.getInt("rating"));
			f.setDescription(rs.getString("description"));
			return f;
		}
	}

	/* ####################dashboard implementation ############################ */
	public int totalord() {
		String sql = "select count(order_id) from orders";
		// return jdbcTemplate.query(sql,new taskOrd());
		int To = jdbcTemplate.queryForObject(sql, Integer.class);
		return To;
	}

	public int conford() {
		String sql = "select count(order_id) from orders where status='confirmed'";
		int Co = jdbcTemplate.queryForObject(sql, Integer.class);
		return Co;
	}

	public int pickord() {
		String sql = "select count(order_id) from orders where status='Pickuped'";
		int Pico = jdbcTemplate.queryForObject(sql, Integer.class);
		return Pico;
	}

	public int prepord() {
		String sql = "select count(order_id) from orders where status='Food Preparing'";
		int Po = jdbcTemplate.queryForObject(sql, Integer.class);
		return Po;
	}

	/*
	 * public int totalcancelled() { String
	 * sql="select count(order_id) from orders where status='fail'"; cancelled
	 * orders int Tu=jdbcTemplate.queryForObject(sql,Integer.class); return Tu; }
	 */
	public int totaldeliver() {
		String sql = "select count(order_id) from orders where status='Delivered'";
		int Td = jdbcTemplate.queryForObject(sql, Integer.class);
		return Td;
	}

	public int totaluse() {
		String sql = "select count(cust_id) from customers";
		int Cust = jdbcTemplate.queryForObject(sql, Integer.class);
		return Cust;
	}

	/*
	 * #####################dashboard implementation
	 * mappers###############################
	 */
	protected class taskOrd implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dashmodel t = new Dashmodel();
			t.setTotalorders(rs.getInt("order_id"));
			return t;
		}
	}

	protected class taskconf implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dashmodel t = new Dashmodel();
			t.setConfirmedorders(rs.getInt("order_id"));
			return t;
		}
	}

	protected class taskpick implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dashmodel t = new Dashmodel();
			t.setPickuporders(rs.getInt("order_id"));
			return t;
		}
	}

	protected class taskprep implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dashmodel t = new Dashmodel();
			t.setPreparingorders(rs.getInt("order_id"));
			return t;
		}
	}

	protected class taskundeliver implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dashmodel t = new Dashmodel();
			t.setCancelledorders(rs.getInt("order_id")); /* cancelled orders */
			return t;
		}
	}

	protected class taskdeliver implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dashmodel t = new Dashmodel();
			t.setDeliveredorders(rs.getInt("payment_id"));
			return t;
		}
	}

	protected class taskuse implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Dashmodel t = new Dashmodel();
			t.setTotalusers(rs.getInt("cust_id"));
			return t;
		}
	}

}
