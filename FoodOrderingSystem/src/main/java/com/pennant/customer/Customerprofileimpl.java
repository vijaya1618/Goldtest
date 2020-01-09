package com.pennant.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;


public class Customerprofileimpl implements  CustomerprofileDAO{
	private JdbcTemplate jdbcTemplate;
	protected DataFieldMaxValueIncrementer taskIncer;
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setTaskIncer(DataFieldMaxValueIncrementer taskIncer) {
		this.taskIncer = taskIncer;
	}
	protected class TaskCust implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customerprofilemodel cp = new Customerprofilemodel();													
			cp.setFirstname(rs.getString("fname"));
			cp.setLastname(rs.getString("lname"));
			cp.setEmail(rs.getString("email"));
			cp.setPhonenumber(rs.getLong("phoneno"));
			return cp;
		}
	}
	
/*
 * public Customerprofile update(Customerprofile cp) throws Exception { String
 * sql = "UPDATE customers SET  fname,lname,email,phoneno WHERE cust_id = ?";
 * Object[] params = new Object[] {cp.getFirstname(),
 * cp.getLastname(),cp.getEmail(),cp.getPhonenumber()}; int types[] = new int[]
 * { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
 * jdbcTemplate.update(sql, params, types);
 * 
 * return cp; }
 */
	public List Findcustomer() throws Exception {
		
		Session s=Sessions.getCurrent(); 
		  int i=(Integer)s.getAttribute("userid");
		String sql = "SELECT fname,lname,email,phoneno FROM  customers where cust_id=?";
		Object[] params = new Object[] {i};
		int types[] = new int[] { Types.INTEGER};
	      return jdbcTemplate.query(sql,params,types,new TaskCust());
	}
	public void Customerupdate(Customerprofilemodel cp) {
		Session s=Sessions.getCurrent(); 
		  int i=(Integer)s.getAttribute("userid");
		String sql = "UPDATE customers SET  fname=?,lname=?,email=?,phoneno=? WHERE cust_id = ?";
		Object[] params = new Object[] {cp.getFirstname(), cp.getLastname(),cp.getEmail(),cp.getPhonenumber(),i};
		int types[] = new int[] { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.INTEGER};
		jdbcTemplate.update(sql, params, types);
		
	}

	
	
	
}
