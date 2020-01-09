package com.pennant.customer;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;



public class Cartimpl implements CartDAO {

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

	/*
	 * protected class TaskCart implements RowMapper { public Object
	 * mapRow(ResultSet rs, int rowNum) throws SQLException { Cartmodel c= new
	 * Cartmodel(); c. setItemname(rs.getString("item_name"));
	 * c.setPrice(rs.getInt("item_price"));
	 * 
	 * return c; } }
	 * 
	 * 
	 * public List cartitems() throws Exception{ int s=101; String
	 * sql="select item_name,item_price  from items where item_id in(select item_id from cart where cust_id="
	 * +s+")"; return jdbcTemplate.query(sql, new TaskCart()); }
	 */
	public List cartItemsd() throws Exception {
		Session s=Sessions.getCurrent(); 
		  int i=(Integer)s.getAttribute("userid");
		String sql = "select * from cart where cust_id="+i;
		return jdbcTemplate.query(sql, new TaskCart1());
	}

	protected class TaskCart1 implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cartmodel c = new Cartmodel();
			c.setItemid(rs.getInt("item_id"));
			c.setItemname(rs.getString("item_name"));
			c.setPrice(rs.getInt("item_price"));
			c.setQty(rs.getInt("items_count"));
			c.setTotalprice(rs.getInt("total_price"));
			return c;
		}
	}

	public void update(Cartmodel ci) throws Exception {
		Session s=Sessions.getCurrent(); 
		  int i=(Integer)s.getAttribute("userid");
		String sql = "update cart set items_count=?,total_price=? where cust_id=? and item_id=?";
		Object[] params = new Object[] { ci.getQty(), ci.getTotalprice(), i, ci.getItemid() };

		int types[] = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER };
		jdbcTemplate.update(sql, params, types);

	}
	public void idelete(int ci) throws Exception {
		String sql = "delete from cart where item_id=?";
		Object[] params = new Object[] {ci };

		int types[] = new int[] { Types.INTEGER };
		jdbcTemplate.update(sql, params, types);

	}
	public int findtp() throws Exception {
		int a=0;
		try {
		Session s=Sessions.getCurrent(); 
		  int i=(Integer)s.getAttribute("userid");
		String sql = "select sum(total_price) from cart where cust_id=?";
		Object[] params = new Object[] {i};

		int types[] = new int[] { Types.INTEGER };
		a=jdbcTemplate.queryForObject(sql, params, types,Integer.class);
		System.out.println(a);
		

	}
catch(Exception e)
{
}
		return a;
}
}
