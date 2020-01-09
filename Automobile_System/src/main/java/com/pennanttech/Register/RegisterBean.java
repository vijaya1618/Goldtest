package com.pennanttech.Register;

import java.sql.Date;

public class RegisterBean {
	private int Userid;
	private String Username;
	private String Fullname;
	private String Password;
	private String repassword;
	private String email;
	private long phone;
	private Date dob;
	private String gender;
	private String department;
	private String Securityquestion;
	private String Answer;
private String status;

	public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

	public int getUserid() {
		return Userid;
	}

	public void setUserid(int userid) {
		Userid = userid;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getFullname() {
		return Fullname;
	}

	public void setFullname(String fullname) {
		Fullname = fullname;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSecurityquestion() {
		return Securityquestion;
	}

	public void setSecurityquestion(String securityquestion) {
		Securityquestion = securityquestion;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}
	

}
