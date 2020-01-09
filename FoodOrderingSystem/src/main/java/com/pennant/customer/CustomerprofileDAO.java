package com.pennant.customer;

import java.util.List;

public interface CustomerprofileDAO {

	public void Customerupdate(Customerprofilemodel cp);
	public List Findcustomer() throws Exception;
}
