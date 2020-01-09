package com.pennant.admin;

	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Types;
	import java.util.List;

	import javax.sql.DataSource;

	import org.springframework.jdbc.core.JdbcTemplate;
	import org.springframework.jdbc.core.RowMapper;
	import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;


	public class Ordersimpl implements OrdersDAO{
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
		protected class TaskOrder implements RowMapper {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Orders r = new Orders();													
				r.setOrderID(rs.getInt("order_id"));
				r.setCustomerID(rs.getInt("cust_id"));
				r.setStatus(rs.getString("status"));
				r.setRname(rs.getString("rname"));
				return r;
			}
		}
		public void delete(Orders r) {
			String sql = "DELETE FROM orders WHERE order_id = ?";
			Object[] params = new Object[] { r.getOrderID() };
			int types[] = new int[] { Types.INTEGER};
			jdbcTemplate.update(sql, params, types);
		}
		public Orders update(Orders r) throws Exception {
			String sql = "UPDATE orders SET  status=? WHERE order_id = ?";
			Object[] params = new Object[] { r.getStatus(),r.getOrderID()};
			int types[] = new int[] { Types.VARCHAR,Types.INTEGER};
			jdbcTemplate.update(sql, params, types);
			
			return r;
		}
		public List findItem() throws Exception {
			String sql = "SELECT * FROM  orders where status!='Delivered'";
			return jdbcTemplate.query(sql, new TaskOrder());
		}
		
		
		public List findItem1() throws Exception {
			String sql = "SELECT * FROM orders where status='Delivered'";
			return jdbcTemplate.query(sql, new TaskOrder());
		}

		
			


		public Orders findById(int id) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		public Orders insert(Orders r) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

