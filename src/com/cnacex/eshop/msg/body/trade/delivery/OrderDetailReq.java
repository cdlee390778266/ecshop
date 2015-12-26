package com.cnacex.eshop.msg.body.trade.delivery;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OrderDetailReq {
	
	
	@XStreamAlias("strikeno")
	private String strikeNo;
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("mid")
	private String mID;

	public String getStrikeNo() {
		return strikeNo;
	}

	public void setStrikeNo(String strikeNo) {
		this.strikeNo = strikeNo;
	}

	public String getOperID() {
		return operID;
	}

	public void setOperID(String operID) {
		this.operID = operID;
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	@Override
	public String toString() {
		return "OrderDetailReq [strikeNo=" + strikeNo + ", operID=" + operID
				+ ", mID=" + mID + "]";
	}




}
