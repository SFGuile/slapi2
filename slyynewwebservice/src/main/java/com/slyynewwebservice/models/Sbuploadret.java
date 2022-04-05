package com.slyynewwebservice.models;

import java.util.List;

public class Sbuploadret {
	
 private int returncode;
 private List<Sbretdetails> details;
 
 
 public int getReturncode() {
	return returncode;
}
public void setReturncode(int returncode) {
	this.returncode = returncode;
}
public List<Sbretdetails> getDetails() {
	return details;
}
public void setDetails(List<Sbretdetails> details) {
	this.details = details;
}

 
	

}
