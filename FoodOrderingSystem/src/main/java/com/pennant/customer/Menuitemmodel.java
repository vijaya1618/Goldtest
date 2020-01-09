package com.pennant.customer;

public class Menuitemmodel {
	private int itemID;
	private String menuName;
	private String itemName;
	private int itemPrice;
	private String itemAvailability;
	private String Restname;
	private String Location;
	private int restID;
	private int menuid;
	public int getMenuid() {
		return menuid;
	}
	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}
	public int getRestID() {
		return restID;
	}
	public void setRestID(int restID) {
		this.restID = restID;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getRestname() {
		return Restname;
	}
	public void setRestname(String restname) {
		Restname = restname;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemAvailability() {
		return itemAvailability;
	}
	public void setItemAvailability(String itemAvailability) {
		this.itemAvailability = itemAvailability;
	}
		
}
