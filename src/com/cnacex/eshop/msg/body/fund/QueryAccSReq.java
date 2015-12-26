package com.cnacex.eshop.msg.body.fund;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */

public class QueryAccSReq {
	
	@Override
	public String toString() {
		return "QueryAccSReq [mID=" + mID + ", fundPwd=" + fundPwd
				+ ", qryType=" + qryType + ", qryAcctType=" + qryAcctType
				+ ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", origAcNo=" + origAcNo + ", iae=" + iae + ", costCode="
				+ costCode + "]";
	}

	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("fundpwd")
	private String fundPwd;
	
	@XStreamAlias("qrytype")
	private String qryType;
	
	@XStreamAlias("qryaccttype")
	private String qryAcctType;
	
	@XStreamAlias("begindate")
	private String beginDate;
	
	@XStreamAlias("enddate")
	private String endDate;
	
	@XStreamAlias("origacno")
	private String origAcNo;
	
	@XStreamAlias("iae")
	private String iae;
	
	@XStreamAlias("costcode")
	private String costCode;
	
	@XStreamAlias("extno")
	private String extNo;

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getFundPwd() {
		return fundPwd;
	}

	public void setFundPwd(String fundPwd) {
		this.fundPwd = fundPwd;
	}

	public String getQryType() {
		return qryType;
	}

	public void setQryType(String qryType) {
		this.qryType = qryType;
	}

	public String getQryAcctType() {
		return qryAcctType;
	}

	public void setQryAcctType(String qryAcctType) {
		this.qryAcctType = qryAcctType;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOrigAcNo() {
		return origAcNo;
	}

	public void setOrigAcNo(String origAcNo) {
		this.origAcNo = origAcNo;
	}

	public String getIae() {
		return iae;
	}

	public void setIae(String iae) {
		this.iae = iae;
	}

	public String getCostCode() {
		return costCode;
	}

	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	public String getExtNo() {
		return extNo;
	}

	public void setExtNo(String extNo) {
		this.extNo = extNo;
	}



}
