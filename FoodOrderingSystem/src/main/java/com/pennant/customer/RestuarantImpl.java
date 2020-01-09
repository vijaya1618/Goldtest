package com.pennant.customer;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.io.FileNotFoundException;
import 	java.math.BigDecimal;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;

import com.pennant.admin.Menumodel;



public class RestuarantImpl implements RestuarantDAO{
	
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
	}protected class TaskRest1 implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Restmodel t = new Restmodel();													
			t.setRestaurantid(rs.getInt("r_id"));
			t.setRestaurantName(rs.getString("rname"));
			t.setLocation(rs.getString("location"));
			t.setPhoneNumber(rs.getLong("phonenumber"));
			t.setImage(rs.getBytes("image"));
			return t;
		}
	}
	public List restImpl() throws Exception {
		String sql="select * from restuarents";
		return jdbcTemplate.query(sql, new TaskRest1());
	}

	

	
}
