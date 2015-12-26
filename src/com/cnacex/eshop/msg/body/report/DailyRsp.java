package com.cnacex.eshop.msg.body.report;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class DailyRsp {

	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("memname")
	private String memName;
	
	@XStreamAlias("date")
	private String date;

	@XStreamImplicit(itemFieldName = "subacctlist")
	List<SubAcctList> subAcctList = new ArrayList<SubAcctList>();
	
	@XStreamAlias("offsetamt")
	private double offsetAmt;
	
	@XStreamAlias("closeabv")
	private double closeABV;
	
	@XStreamAlias("closefav")
	private double closeFAV;

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<SubAcctList> getSubAcctList() {
		return subAcctList;
	}

	public void setSubAcctList(List<SubAcctList> subAcctList) {
		this.subAcctList = subAcctList;
	}

	public double getOffsetAmt() {
		return offsetAmt;
	}

	public void setOffsetAmt(double offsetAmt) {
		this.offsetAmt = offsetAmt;
	}

	public double getCloseABV() {
		return closeABV;
	}

	public void setCloseABV(double closeABV) {
		this.closeABV = closeABV;
	}

	public double getCloseFAV() {
		return closeFAV;
	}

	public void setCloseFAV(double closeFAV) {
		this.closeFAV = closeFAV;
	}

	
	

}
