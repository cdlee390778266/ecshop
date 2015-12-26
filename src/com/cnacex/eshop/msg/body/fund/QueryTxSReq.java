package com.cnacex.eshop.msg.body.fund;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */

public class QueryTxSReq {
	
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
	
	@XStreamAlias("origtxsn")
	private String origTxSN;
	
	@XStreamAlias("costcode")
	private String costCode;

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

	public String getOrigTxSN() {
		return origTxSN;
	}

	public void setOrigTxSN(String origTxSN) {
		this.origTxSN = origTxSN;
	}

	public String getCostCode() {
		return costCode;
	}

	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}


	
	
}
