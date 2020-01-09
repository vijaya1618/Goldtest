package com.pennant.customer;

import java.util.List;

public interface ItemsDAO {
	public List findRest(int i) throws Exception;
	public List findMenus(int i) throws Exception;
	public List findItems(int i) throws Exception;
	public List addItem(int i)throws Exception;
	public int cartitem(Cartmodel m)throws Exception;
}
