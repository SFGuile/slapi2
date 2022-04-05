package com.slyynewwebservice.models;

import java.util.List;

public class Gjret {

	 private int returncode;
	 private int iflackofproduct;
	 private String expmsg;
	 private List<Details> mylackdetails ;
	 
	 
	 public int getReturncode() {
		return returncode;
	}
	public void setReturncode(int returncode) {
		this.returncode = returncode;
	}
	public int getIflackofproduct() {
		return iflackofproduct;
	}
	public void setIflackofproduct(int iflackofproduct) {
		this.iflackofproduct = iflackofproduct;
	}
	public String getExpmsg() {
		return expmsg;
	}
	public void setExpmsg(String expmsg) {
		this.expmsg = expmsg;
	}
	public List<Details> getMylackdetails() {
		return mylackdetails;
	}
	public void setMylackdetails(List<Details> mylackdetails) {
		this.mylackdetails = mylackdetails;
	}

}
