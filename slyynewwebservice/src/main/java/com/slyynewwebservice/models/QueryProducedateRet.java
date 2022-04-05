package com.slyynewwebservice.models;

import java.util.List;

public class QueryProducedateRet {

	private int retcode;
	private String retmsg;
	private String prodno;
	private String batchno;
	private String prodadd;
	private int recordscount;
	private List<QueryProducedateDetails> producedetails;
	
	public int getRecordscount() {
		return recordscount;
	}
	public void setRecordscount(int recordscount) {
		this.recordscount = recordscount;
	}
	
	
	public int getRetcode() {
		return retcode;
	}
	public void setRetcode(int retcode) {
		this.retcode = retcode;
	}
	public String getRetmsg() {
		return retmsg;
	}
	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}
	public String getProdno() {
		return prodno;
	}
	public void setProdno(String prodno) {
		this.prodno = prodno;
	}
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public String getProdadd() {
		return prodadd;
	}
	public void setProdadd(String prodadd) {
		this.prodadd = prodadd;
	}
	public List<QueryProducedateDetails> getProducedetails() {
		return producedetails;
	}
	public void setProducedetails(List<QueryProducedateDetails> producedetails) {
		this.producedetails = producedetails;
	}
	
}
