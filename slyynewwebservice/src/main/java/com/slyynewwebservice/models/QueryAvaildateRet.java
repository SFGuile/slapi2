package com.slyynewwebservice.models;

import java.util.List;

public class QueryAvaildateRet {
	
private int returncode;
private List<QueryAvaildateRetDetail> queryretlist;
private String expmsg;


public int getReturncode() {
	return returncode;
}

public void setReturncode(int returncode) {
	this.returncode = returncode;
}

public List<QueryAvaildateRetDetail> getQueryretlist() {
	return queryretlist;
}

public void setQueryretlist(List<QueryAvaildateRetDetail> queryretlist) {
	this.queryretlist = queryretlist;
}

public String getExpmsg() {
	return expmsg;
}

public void setExpmsg(String expmsg) {
	this.expmsg = expmsg;
}

	
}
