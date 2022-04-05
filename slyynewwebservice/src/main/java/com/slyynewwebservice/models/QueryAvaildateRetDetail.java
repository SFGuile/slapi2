package com.slyynewwebservice.models;

public class QueryAvaildateRetDetail {

private String prodbatchkey;
private String prodname;
private String availdate;
private int  ifexists;
private int prodbatchrecordcount;



public String getProdbatchkey() {
	return prodbatchkey;
}

public void setProdbatchkey(String prodbatchkey) {
	this.prodbatchkey = prodbatchkey;
}

public String getProdname() {
	return prodname;
}

public void setProdname(String prodname) {
	this.prodname = prodname;
}

public String getAvaildate() {
	return availdate;
}

public void setAvaildate(String availdate) {
	this.availdate = availdate;
}

public int getIfexists() {
	return ifexists;
}

public void setIfexists(int ifexists) {
	this.ifexists = ifexists;
}

public int getProdbatchrecordcount() {
	return prodbatchrecordcount;
}

public void setProdbatchrecordcount(int prodbatchrecordcount) {
	this.prodbatchrecordcount = prodbatchrecordcount;
}

}
