package com.slyynewwebservice.models;

import java.util.List;

public class QueryAvaildateUpload {

	private String username;
    private String password;
	private List<String> thekey;
	
	
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
	public List<String> getThekey() {
		return thekey;
	}
	public void setThekey(List<String> thekey) {
		this.thekey = thekey;
	}
	
}
