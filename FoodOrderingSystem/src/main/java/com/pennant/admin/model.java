package com.pennant.admin;

import org.zkoss.zul.Textbox;

public class model {
private int cust_id;
private String Firstname;
private String Lastname;
private String email;
private String password;
private long phone;
private String cnfpassword ;
private String security;
private String answers;
public int getCust_id() {
	return cust_id;
}
public void setCust_id(int cust_id) {
	this.cust_id = cust_id;
}
public String getFirstname() {
	return Firstname;
}
public void setFirstname(String firstname) {
	Firstname = firstname;
}
public String getLastname() {
	return Lastname;
}
public void setLastname(String lastname) {
	Lastname = lastname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public long getPhone() {
	return phone;
}
public void setPhone(long phone) {
	this.phone = phone;
}
public String getCnfpassword() {
	return cnfpassword;
}
public void setCnfpassword(String cnfpassword) {
	this.cnfpassword = cnfpassword;
}
public String getSecurity() {
	return security;
}
public void setSecurity(String security) {
	this.security = security;
}
public String getAnswers() {
	return answers;
}
public void setAnswers(String answers) {
	this.answers = answers;
}

}



