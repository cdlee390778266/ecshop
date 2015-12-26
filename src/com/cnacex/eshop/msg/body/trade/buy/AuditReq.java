package com.cnacex.eshop.msg.body.trade.buy;

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
	
	@XStreamAlias("delistno")
	private String delistNo;
	
	public String getAuOperID() {
		return auOperID;
	}

	public void setAuOperID(String auOperID) {
		this.auOperID = auOperID;
	}



	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@XStreamAlias("auditret")
	private String	auditRet;
	
	@XStreamAlias("comment")
	private String 	comment;

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getDelistNo() {
		return delistNo;
	}

	public void setDelistNo(String delistNo) {
		this.delistNo = delistNo;
	}

	public String getAuditRet() {
		return auditRet;
	}

	public void setAuditRet(String auditRet) {
		this.auditRet = auditRet;
	}

	@Override
	public String toString() {
		return "AuditReq [mID=" + mID + ", auOperID=" + auOperID
				+ ", delistNo=" + delistNo + ", auditRet=" + auditRet
				+ ", comment=" + comment + "]";
	}



	

	
	

}
