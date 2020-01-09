package com.pennant.admin;

import java.util.List;

public interface ItemDAO {

	
	public Item insert(Item t) throws Exception;
	public Item update(Item t) throws Exception;
	public void delete(Item t) throws Exception;
	public Item findById(int id) throws Exception;
	public List findItem() throws Exception;
	}

