package com.pennant.customer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.pennant.admin.Menumodel;


public class paymentimpl implements PaymentDao {
	
	public int n;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	protected DataFieldMaxValueIncrementer taskIncer;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setTaskIncer(DataFieldMaxValueIncrementer taskIncer) {
		this.taskIncer = taskIncer;
	}
	
	
	public void paymentAdd(paymentmodel pm) throws Exception {
		Session s=Sessions.getCurrent(); 
		  int cid=(Integer)s.getAttribute("userid");
		
		String t=pm.getBilladdress();
		String d=pm.getDeladdress();
		String p=pm.getPaytype();
		int h=pm.getTotprice();                              
		String status="success";
		//LocalDate y=java.time.LocalDate.now();
		   java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		    Date y=(Date)sqlDate;
		String sql="insert into payments values(next value for payseq,?,?,?,?,?,?,?,?)";
		Object[] params=new Object[] {cid,n,p,y,h,t,d,status};
		int types[]=new int[]{Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.DATE,Types.DECIMAL,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		jdbcTemplate.update(sql,params ,types);
	}

	public List order(MyOrderModel om) {
		// TODO Auto-generated method stub
		
	
		return null;
	}

	public void orderInsert() throws Exception {
		// TODO Auto-generated method stub
		Session s1=Sessions.getCurrent(); 
		  int cid=(Integer)s1.getAttribute("userid");
	    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
	    Date d=(Date)sqlDate;
		String status="confirmed";
		
		String b=(String)s1.getAttribute("rid");
		int r_id=Integer.parseInt(b);
		String sql="select rname from restuarents where r_id=?";
		Object[] params1=new Object[] {r_id};
		int types1[]=new int[]{Types.INTEGER};
		String s=jdbcTemplate.queryForObject(sql,params1 ,types1,String.class); 
		/*
		 * String sql="insert into orders values(?,?,?,?)"; Object[] params=new Object[]
		 * {cid,d,status,s}; int types[]=new
		 * int[]{Types.INTEGER,Types.DATE,Types.VARCHAR,Types.VARCHAR};
		 * jdbcTemplate.update(sql,params ,types);
		 */
		SimpleJdbcCall jdbc=new SimpleJdbcCall(dataSource).withProcedureName("orderpro");
		SqlParameterSource in =new MapSqlParameterSource().addValue("cust_id",cid)
					.addValue("pickup_date",d)
					.addValue("status",status)
					.addValue("name", s);
				Map<String,Object>  out=jdbc.execute(in);
				
			 n=((Integer)out.get("id")).intValue();
				
	
	
	}
	public void cartdisplay() 
	{
		Session s=Sessions.getCurrent(); 
		  int cid=(Integer)s.getAttribute("userid");
		String sql="select * from cart where cust_id=?";
		 Object[] params=new Object[] {cid};
		 int types[]=new int[]{Types.INTEGER};
		 jdbcTemplate.query(sql,params,types,new OrderMapper());
	}
	protected class OrderMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				String sql="insert into orderitems values(?,?,?,?,?)";
				Object[] params=new Object[]{n,rs.getInt("item_id"),rs.getString("item_name"),rs.getInt("items_count"),rs.getInt("total_price")}; 
				 int types[]=new int[]{Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
				 jdbcTemplate.update(sql,params ,types); 

			
		return null;
		}
	}
	public void deletecart() throws Exception
	{
		Session s=Sessions.getCurrent(); 
		  int cid=(Integer)s.getAttribute("userid");
		String sql="delete from cart where cust_id=?";
		 Object[] params=new Object[] {cid};
		 int types[]=new int[]{Types.INTEGER};
		 jdbcTemplate.update(sql,params ,types); 
	}

	
 
}
