package com.slyynewwebservice.models;

import java.util.List;

public class Sbuploads {
	
	private String username;
	private String password;
	private List<Sbuploadetails> mydetails;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Sbuploadetails> getMydetails() {
		return mydetails;
	}
	public void setMydetails(List<Sbuploadetails> mydetails) {
		this.mydetails = mydetails;
	}


}
