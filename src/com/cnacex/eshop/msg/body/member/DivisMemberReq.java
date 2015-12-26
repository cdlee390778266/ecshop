package com.cnacex.eshop.msg.body.member;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class DivisMemberReq {
	
	@XStreamAlias("divlevel")
	private String divLevel;
	
	@XStreamAlias("divcode")
	private String divCode;
	
	@XStreamAlias("markcode")
	private String markCode;
	
	@XStreamAlias("reqstart")
	private String reqStart;
	
	@XStreamAlias("reqnum")
	private String reqNum;
	
	public String getDivLevel() {
		return divLevel;
	}
	public void setDivLevel(String divLevel) {
		this.divLevel = divLevel;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	public String getMarkCode() {
		return markCode;
	}
	public void setMarkCode(String markCode) {
		this.markCode = markCode;
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

}
