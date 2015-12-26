package com.cnacex.eshop.msg.body.report;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class FVList {
	
	@XStreamAlias("acctitle")
	private String accTitle;
	
	@XStreamAlias("iae")
	private String iae;
	
	@XStreamAlias("occamt")
	private double occAmt;

	public String getAccTitle() {
		return accTitle;
	}

	public void setAccTitle(String accTitle) {
		this.accTitle = accTitle;
	}

	public String getIae() {
		return iae;
	}

	public void setIae(String iae) {
		this.iae = iae;
	}

	public double getOccAmt() {
		return occAmt;
	}

	public void setOccAmt(double occAmt) {
		this.occAmt = occAmt;
	}

	
	

}
