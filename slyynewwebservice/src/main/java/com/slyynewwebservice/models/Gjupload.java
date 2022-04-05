package com.slyynewwebservice.models;

import java.util.List;

public class Gjupload {

	private String username;
	private String password;
	private int belongto;
	private String listno;
	private String gjbno;
	private String localtime;
	private List<Details> mydetails;
	
	
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
	public int getBelongto() {
		return belongto;
	}
	public void setBelongto(int belongto) {
		this.belongto = belongto;
	}
	public String getListno() {
		return listno;
	}
	public void setListno(String listno) {
		this.listno = listno;
	}
	public String getGjbno() {
		return gjbno;
	}
	public void setGjbno(String gjbno) {
		this.gjbno = gjbno;
	}
	public String getLocaltime() {
		return localtime;
	}
	public void setLocaltime(String localtime) {
		this.localtime = localtime;
	}
	public List<Details> getMydetails() {
		return mydetails;
	}
	public void setMydetails(List<Details> mydetails) {
		this.mydetails = mydetails;
	}
	
}
