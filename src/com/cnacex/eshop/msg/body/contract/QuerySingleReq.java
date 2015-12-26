package com.cnacex.eshop.msg.body.contract;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class QuerySingleReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("contno")
	private String contNo;

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getOperID() {
		return operID;
	}

	public void setOperID(String operID) {
		this.operID = operID;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}


}
