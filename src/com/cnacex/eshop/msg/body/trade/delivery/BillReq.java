package com.cnacex.eshop.msg.body.trade.delivery;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class BillReq {
	
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("status")
	private String status;
	
	@XStreamAlias("reqstart")
	private String reqStart;
	
	@XStreamAlias("reqnum")
	private String reqNum;
	
	@XStreamAlias("strikeno")
	private String strikeNo;
	
	@XStreamAlias("commcode")
	private String commCode;
	
	@XStreamAlias("lastpd")
	private String lastPD;
	
	@XStreamAlias("elastpd")
	private String elastPD;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReqStart() {
		return reqStart;
	}

	public void setReqStart(String reqStart) {
		this.reqStart = reqStart;
	}

	@Override
	public String toString() {
		return "BillReq [mID=" + mID + ", operID=" + operID + ", status="
				+ status + ", reqStart=" + reqStart + ", reqNum=" + reqNum
				+ ", strikeNo=" + strikeNo + ", commCode=" + commCode
				+ ", lastPD=" + lastPD + "]";
	}

	public String getReqNum() {
		return reqNum;
	}

	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}

	public String getStrikeNo() {
		return strikeNo;
	}

	public void setStrikeNo(String strikeNo) {
		this.strikeNo = strikeNo;
	}

	public String getCommCode() {
		return commCode;
	}

	public void setCommCode(String commCode) {
		this.commCode = commCode;
	}

	public String getLastPD() {
		return lastPD;
	}

	public void setLastPD(String lastPD) {
		this.lastPD = lastPD;
	}

	public String getElastPD() {
		return elastPD;
	}

	public void setElastPD(String elastPD) {
		this.elastPD = elastPD;
	}
	


	
	
	
	
}
