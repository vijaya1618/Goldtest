package com.pennant.admin;

public class Orders {
		
			private int orderID;
			private int customerID;
			private String status;
			private String Rname;
			public String getRname() {
				return Rname;
			}
			public void setRname(String rname) {
				Rname = rname;
			}
			public int getOrderID() {
				return orderID;
			}
			public void setOrderID(int orderID) {
				this.orderID = orderID;
			}
			public int getCustomerID() {
				return customerID;
			}
			public void setCustomerID(int customerID) {
				this.customerID = customerID;
			}
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}		
}
