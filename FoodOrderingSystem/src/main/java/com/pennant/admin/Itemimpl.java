package com.pennant.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;






public class Itemimpl implements ItemDAO{
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
	protected class TaskItem implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Item t = new Item();													
			t.setItemID(rs.getInt("item_id"));
			t.setItemName(rs.getString("item_name"));
			t.setItemPrice(rs.getInt("item_price"));
			t.setItemAvailability(rs.getString("avaliability"));
			t.setMenuID(rs.getInt("menu_id"));
			return t;
		}
	}
	public void delete(Item t) {
		String sql = "DELETE FROM items WHERE item_id = ?";
		Object[] params = new Object[] { t.getItemID() };
		int types[] = new int[] { Types.INTEGER};
		jdbcTemplate.update(sql, params, types);
	}
	public Item update(Item t) throws Exception {
		String sql = "UPDATE items SET item_name = ?, item_price = ?, avaliability=?,menu_id=? WHERE item_id = ?";
		Object[] params = new Object[] { t.getItemName(), t.getItemPrice(), t.getItemAvailability(),t.getMenuID(),t.getItemID()};
		int types[] = new int[] { Types.VARCHAR, Types.DECIMAL,Types.VARCHAR, Types.INTEGER,Types.INTEGER};
		jdbcTemplate.update(sql, params, types);
		
		return t;
	}
	public List findItem() throws Exception {
		String sql = "SELECT * FROM  items";
		return jdbcTemplate.query(sql, new TaskItem());
	}

	
		


	public Item findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Item insert(Item t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
