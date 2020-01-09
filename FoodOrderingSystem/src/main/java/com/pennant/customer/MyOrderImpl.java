package com.pennant.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;
import 	java.math.BigDecimal;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;


public class MyOrderImpl implements  MyOrderUserInterface {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
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
	
	
	public List order(MyOrderModel om){
		/*
		 * Session s=Sessions.getCurrent(); int i=(Integer)s.getAttribute("userid");
		 */
		String sql = "Select o.order_id,pickup_date,status,p.amount,p.payment_type from orders o,payments p where o.order_id=p.order_id and o.cust_id=101";
		return jdbcTemplate.query(sql, new CustTask());
	}

	protected class CustTask implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			MyOrderModel t = new MyOrderModel();													
			t.setOrderId(rs.getInt("order_id"));
			
			t.setPrice(rs.getInt("amount"));
			t.setDate(rs.getDate("pickup_date"));
			t.setPaymentType(rs.getString("payment_type"));
			t.setStatus(rs.getString("status"));
			
			return t;
		}
		
	}
	protected class OrderTask implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			MyOrderModel t = new MyOrderModel();													
			t.setItemid(rs.getInt("item_id"));
			
			t.setItemName(rs.getString("items_name"));
			t.setQuantity(rs.getInt("quantity"));
			
			t.setTotalprice(rs.getInt("items_total_price"));
			
			return t;
		}
		
	}
	public List popOrder(int oid) {
	
		String sql="select * from orderitems where order_id="+oid;
		return jdbcTemplate.query(sql, new OrderTask());
		
	}


}
