package com.cnacex.eshop.msg.body.trade.delivery;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ConfirmReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("strikeno")
	private String strikeNo;

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

	public String getStrikeNo() {
		return strikeNo;
	}

	public void setStrikeNo(String strikeNo) {
		this.strikeNo = strikeNo;
	}

	@Override
	public String toString() {
		return "ConfirmReq [mID=" + mID + ", operID=" + operID + ", strikeNo="
				+ strikeNo + "]";
	}
	
	


}
