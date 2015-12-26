package com.cnacex.eshop.msg.body.report;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ReportCommReq {
	
	@XStreamAlias("rptdate")
	private String rptDate;
	
	@XStreamAlias("mid")
	private String mID;

	public String getRptDate() {
		return rptDate;
	}

	public void setRptDate(String rptDate) {
		this.rptDate = rptDate;
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}
	
	
	


}
