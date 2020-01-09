package com.pennant.customer;

import java.util.List;

public interface CartDAO {
	
	public List cartItemsd() throws Exception;
	public void update(Cartmodel ci) throws Exception;
	public void idelete(int ci) throws Exception;
	public int findtp() throws Exception ;
}
