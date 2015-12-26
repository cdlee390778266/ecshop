package com.cnacex.eshop.msg.body.query;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */

public class HisQueryReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("reqstart")
	private String reqStart;
	
	@XStreamAlias("reqnum")
	private String reqNum;
	
	@XStreamAlias("commcode")
	private String commCode;
	
	@XStreamAlias("strikedate")
	private String strikeDate;
	
	@XStreamAlias("estrikedate")
	private String estrikeDate;
	
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

	public String getReqStart() {
		return reqStart;
	}

	public void setReqStart(String reqStart) {
		this.reqStart = reqStart;
	}

	public String getReqNum() {
		return reqNum;
	}

	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}

	public String getStrikeDate() {
		return strikeDate;
	}

	public void setStrikeDate(String strikeDate) {
		this.strikeDate = strikeDate;
	}

	public String getCommCode() {
		return commCode;
	}

	public void setCommCode(String commCode) {
		this.commCode = commCode;
	}

	public String getEstrikeDate() {
		return estrikeDate;
	}

	public void setEstrikeDate(String estrikeDate) {
		this.estrikeDate = estrikeDate;
	}



	
	

}
