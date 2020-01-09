package com.pennant.customer;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Messagebox;

import com.pennant.customer.ChangepasswordModel;

public class Changepasswordimpl implements ChangepasswordDAO {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	protected DataFieldMaxValueIncrementer taskIncer;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setTaskIncer(DataFieldMaxValueIncrementer taskIncer) {
		this.taskIncer = taskIncer;
	}
  
	
	public void changepassword(ChangepasswordModel cpm) {

		String op = cpm.getOldpassword();
		Session s=Sessions.getCurrent(); 
		  int i=(Integer)s.getAttribute("userid");
		String sql="select password from customers where cust_id=?";
		Object[] params = new Object[] { i };
		int types[] = new int[] {Types.INTEGER};
		String password=jdbcTemplate.queryForObject(sql, params, types,String.class);
		
		if((op.equals(password))) {
			
			String np=cpm.getNewpassword();
			String cp=cpm.getConfirmpassword();
		    if(np.equals(cp)) {
		    	String sql1="update customers set password=? where cust_id=?";
		      Object[] params1 = new Object[] {np,i};
		      int types1[]=new int[] {Types.VARCHAR,Types.INTEGER}; 
		      jdbcTemplate.update(sql1, params1, types1);
		      } 
		    else
		    {
		    	Messagebox.show("New and Confirm Passwords must match");
		    }
		}
	   else
	   { 
		 Messagebox.show(" Password is wrong");
		 }

   }

}
