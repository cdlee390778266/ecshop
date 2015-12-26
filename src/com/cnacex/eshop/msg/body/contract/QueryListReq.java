package com.cnacex.eshop.msg.body.contract;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */

public class QueryListReq {
	
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
	
	@XStreamAlias("bstype")
	private String bsType;
	
	@XStreamAlias("contno")
	private String contNo;
	
	@XStreamAlias("strikeno")
	private String strikeNo;
	
	@XStreamAlias("status")
	private String status;
	
	
	@XStreamAlias("conttime")
	private String contTime;
	
	
	@XStreamAlias("econttime")
	private String econtTime;

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

	public String getCommCode() {
		return commCode;
	}

	public void setCommCode(String commCode) {
		this.commCode = commCode;
	}



	public String getBsType() {
		return bsType;
	}

	public void setBsType(String bsType) {
		this.bsType = bsType;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getStrikeNo() {
		return strikeNo;
	}

	public void setStrikeNo(String strikeNo) {
		this.strikeNo = strikeNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContTime() {
		return contTime;
	}

	public void setContTime(String contTime) {
		this.contTime = contTime;
	}

	public String getEcontTime() {
		return econtTime;
	}

	public void setEcontTime(String econtTime) {
		this.econtTime = econtTime;
	}




	



}
