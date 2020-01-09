package com.pennant.customer;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class Feedbackimpl implements FeedbackDAO{
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
	public void feed(Feedbackmodel fm) throws Exception
	{
		Session s=Sessions.getCurrent();
		int i=(Integer)s.getAttribute("userid");
		int cid=Integer.valueOf(i);
		int r=fm.getRate();
		String v=fm.getDescription();
		String sql="insert into feedback values(?,?,?)";
		Object[] paramas=new Object[] {cid,r,v};
		int types[] = new int[] { Types.INTEGER,Types.INTEGER, Types.VARCHAR};
		jdbcTemplate.update(sql,paramas ,types);
	}

}
