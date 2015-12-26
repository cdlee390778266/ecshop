package com.cnacex.eshop.msg.body.trade.sell;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
public class AuditReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("auoperid")
	private String auOperID;
	
	@XStreamAlias("listedno")
	private String listedNo;
	
	@XStreamAlias("auditret")
	private char	auditRet;
	
	@XStreamAlias("comment")
	private String comment;


	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getAuOperID() {
		return auOperID;
	}

	public void setAuOperID(String auOperID) {
		this.auOperID = auOperID;
	}

	public String getListedNo() {
		return listedNo;
	}

	public void setListedNo(String listedNo) {
		this.listedNo = listedNo;
	}

	public char getAuditRet() {
		return auditRet;
	}

	public void setAuditRet(char auditRet) {
		this.auditRet = auditRet;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "AuditReq [mID=" + mID + ", auOperID=" + auOperID
				+ ", listedNo=" + listedNo + ", auditRet=" + auditRet
				+ ", comment=" + comment + "]";
	}
	
	


}
