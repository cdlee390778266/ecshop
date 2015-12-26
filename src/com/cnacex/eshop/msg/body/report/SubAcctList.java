package com.cnacex.eshop.msg.body.report;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class SubAcctList {
	
	@XStreamAlias("subtype")
	private String subType;
	
	@XStreamAlias("openbal")
	private double openBal;
	
	@XStreamImplicit(itemFieldName = "ftlist")
	List<FTList> ftlist = new ArrayList<FTList>();

	
	@XStreamImplicit(itemFieldName = "fvlist")
	List<FVList> fvlist = new ArrayList<FVList>();

	
	@XStreamAlias("closebal")
	private double closeBal;


	public String getSubType() {
		return subType;
	}


	public void setSubType(String subType) {
		this.subType = subType;
	}


	public double getOpenBal() {
		return openBal;
	}


	public void setOpenBal(double openBal) {
		this.openBal = openBal;
	}


	public List<FTList> getFtlist() {
		return ftlist;
	}


	public void setFtlist(List<FTList> ftlist) {
		this.ftlist = ftlist;
	}


	public List<FVList> getFvlist() {
		return fvlist;
	}


	public void setFvlist(List<FVList> fvlist) {
		this.fvlist = fvlist;
	}


	public double getCloseBal() {
		return closeBal;
	}


	public void setCloseBal(double closeBal) {
		this.closeBal = closeBal;
	}
	
	
	


}
