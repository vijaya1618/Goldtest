package com.pennant.admin;

import java.util.List;

	public interface OrdersDAO {

		public Orders update(Orders r) throws Exception;
		public void delete(Orders r) throws Exception;
		public Orders findById(int id) throws Exception;
		public List findItem() throws Exception;
		public List findItem1() throws Exception;
		}