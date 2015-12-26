package com.cnacex.eshop.msg.body.trade.delivery;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class AppealBillRsp {
	
	@XStreamAlias("compno")
	private String compNo;
	
	@XStreamAlias("compmid")
	private String compMID;
	
	@XStreamAlias("strikeno")
	private String strikeNo;
	
	@XStreamAlias("trdstatus")
	private int trdStatus;
	
	private String trdStatusDesc;
	
	public String getTrdStatusDesc() {
		return trdStatusDesc;
	}
	public void setTrdStatusDesc(String trdStatusDesc) {
		this.trdStatusDesc = trdStatusDesc;
	}
	@XStreamAlias("comptime")
	private String compTime;
	
	@XStreamAlias("comprlt")
	private String compRlt;
	
	@XStreamAlias("accepter")
	private String accepter;
	
	@XStreamAlias("acceptime")
	private String accepTime;
	
	@XStreamAlias("comment")
	private String comment;
	
	@XStreamAlias("acceptret")
	private String acceptRet;
	
	
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getCompMID() {
		return compMID;
	}
	public void setCompMID(String compMID) {
		this.compMID = compMID;
	}
	public String getStrikeNo() {
		return strikeNo;
	}
	public void setStrikeNo(String strikeNo) {
		this.strikeNo = strikeNo;
	}
	public int getTrdStatus() {
		return trdStatus;
	}
	public void setTrdStatus(int trdStatus) {
		this.trdStatus = trdStatus;
	}
	public String getCompTime() {
		return compTime;
	}
	public void setCompTime(String compTime) {
		this.compTime = compTime;
	}
	public String getCompRlt() {
		return compRlt;
	}
	public void setCompRlt(String compRlt) {
		this.compRlt = compRlt;
	}
	public String getAccepter() {
		return accepter;
	}
	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}
	public String getAccepTime() {
		return accepTime;
	}
	public void setAccepTime(String accepTime) {
		this.accepTime = accepTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAcceptRet() {
		return acceptRet;
	}
	public void setAcceptRet(String acceptRet) {
		this.acceptRet = acceptRet;
	}

	

}
