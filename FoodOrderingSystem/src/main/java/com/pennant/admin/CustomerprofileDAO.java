package com.pennant.admin;

import java.io.FileNotFoundException;
import java.util.List;

public interface CustomerprofileDAO {

	public void Customerupdate(Customerprofilemodel cp);
	public List Findcustomer() throws Exception;
	public void file(byte[] R) throws FileNotFoundException;
	public ImageBean retrieve() throws FileNotFoundException;
}