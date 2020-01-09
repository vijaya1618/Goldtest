package com.pennant.admin;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public interface UserInterface {
	public int validuser(model m);
	public void validLogin(loginmodel lm);
	public int Resadd(Restmodel rm);
	public int menAdd(Menumodel mm);
	public int itemAdd(Item im);
	public void delete(Menumodel t);
	public List findAll() throws Exception;
	public List findRest() throws Exception;
	public Restmodel RestUpdate(Restmodel td);
	public void deleter(Restmodel td);
	public List showCust();
	public int totalord();
	public int conford();
	public int pickord();
	public int prepord();
	public List findByRid(int id) throws Exception;
	/* public int totalcancelled(); */
	public int totaldeliver();
	public int totaluse();

	public List feedbklist();
	public void forgetS(model m) throws Exception;
	public void passs(model m);
}
