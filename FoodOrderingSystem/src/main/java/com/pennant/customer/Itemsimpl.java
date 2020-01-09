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
import org.zkoss.zk.ui.util.Clients;







public class Itemsimpl implements ItemsDAO{
	
	private JdbcTemplate jdbcTemplate;
	protected DataFieldMaxValueIncrementer taskIncer;
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	protected class TaskRest implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Menuitemmodel mi=new Menuitemmodel();
			
			mi.setRestname(rs.getString("rname"));
			
			mi.setLocation(rs.getString("location"));
			return mi;
		}
	}
	protected class TaskMenu implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Menuitemmodel mi=new Menuitemmodel();
			
			mi.setMenuName(rs.getString("menu_name"));
			mi.setMenuid(rs.getInt("menu_id"));
			return mi;
		}
	}
	protected class TaskItems implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Menuitemmodel mi=new Menuitemmodel();
			mi.setItemID(rs.getInt("item_id"));
			mi.setItemName(rs.getString("item_name"));
			mi.setItemPrice(rs.getInt("item_price"));
			return mi;
		}
	}
	protected class TaskItems2 implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cartmodel cm=new Cartmodel();
			cm.setItemid(rs.getInt("item_id"));
			cm.setItemname(rs.getString("item_name"));
			cm.setPrice(rs.getInt("item_price"));
			cm.setTotalprice(rs.getInt("item_price"));
			return cm;
		}
	}
	

	public List findRest(int i) throws Exception {
		Menuitemmodel mi=new Menuitemmodel();
		int rid=i;
		String sql = "SELECT * FROM  restuarents where r_id="+rid;
		return jdbcTemplate.query(sql, new TaskRest());
	}
	public List findMenus(int i) throws Exception {
		Menuitemmodel mi=new Menuitemmodel();
		int rid=i;
		String sql = "SELECT * FROM  menu where r_id="+rid;
		return jdbcTemplate.query(sql, new TaskMenu());
	}
	public List findItems(int i) throws Exception {
		Menuitemmodel mi=new Menuitemmodel();
		int rid=i;
		String sql = "select * from items where menu_id in(select menu_id from menu where r_id="+rid+")";
		return jdbcTemplate.query(sql, new TaskItems());
	}
	public List addItem(int i)throws Exception{
		String sql="select * from items where item_id="+i;
		return jdbcTemplate.query(sql, new TaskItems2());
	}
	public int cartitem(Cartmodel m)throws Exception{
		Session s=Sessions.getCurrent();
		int j=0;
		int i=(Integer)s.getAttribute("userid");
		int a=Integer.valueOf(i);
		try {
		String sql="insert into cart values(?,?,?,?,?,?)";
		Object[] params = new Object[] {i ,m.getItemid(),m.getTotalprice(),m.getQty(),m.getItemname(),m.getPrice() };
		int types[] = new int[] {Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.INTEGER};
		j=jdbcTemplate.update(sql,params ,types);
		}
		catch(Exception e)
		{
			Clients.showNotification("Item Already Added");
		}
		return j;
	}
	}

	

