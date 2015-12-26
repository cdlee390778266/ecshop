package com.cnacex.eshop.msg.body.report;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Fund {
	
	
	@XStreamAlias("date")
	private String date;
	
	
	@XStreamAlias("acno")
	private String acNo;
	
	@XStreamAlias("iae")
	private String iae;
	
	
	private String iaeDesc;
	
	@XStreamAlias("acctitlename")
	private String accTitleName;
	
	@XStreamAlias("occamt")
	private double occAmt;
	
	private double inAmt;
	
	private double outAmt;
	
	
	//下面为交收办理特有
	@XStreamAlias("dod")
	private String dod;
	
	@XStreamAlias("strikeno")
	private String strikeNo;
	
	@XStreamAlias("bsdirect")
	private String bsDirect;
	
	private String bsDesc;
	
	@XStreamAlias("status")
	private String status;
	
	private String statusDesc;
	
	@XStreamAlias("operdate")
	private String operDate;
	
	
	@XStreamAlias("fundacctname")
	private String fundAcctName;
	
	@XStreamAlias("remark")
	private String remark;
	

	public String getBsDesc() {
		return bsDesc;
	}

	public void setBsDesc(String bsDesc) {
		this.bsDesc = bsDesc;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAcNo() {
		return acNo;
	}

	public void setAcNo(String acNo) {
		this.acNo = acNo;
	}

	public String getIae() {
		return iae;
	}

	public void setIae(String iae) {
		this.iae = iae;
	}

	
	public String getIaeDesc() {
		return iaeDesc;
	}

	public void setIaeDesc(String iaeDesc) {
		this.iaeDesc = iaeDesc;
	}

	public String getAccTitleName() {
		return accTitleName;
	}

	public void setAccTitleName(String accTitleName) {
		this.accTitleName = accTitleName;
	}

	public double getOccAmt() {
		return occAmt;
	}

	public void setOccAmt(double occAmt) {
		this.occAmt = occAmt;
	}

	public double getInAmt() {
		return inAmt;
	}

	public void setInAmt(double inAmt) {
		this.inAmt = inAmt;
	}

	public double getOutAmt() {
		return outAmt;
	}

	public void setOutAmt(double outAmt) {
		this.outAmt = outAmt;
	}

	public String getDod() {
		return dod;
	}

	public void setDod(String dod) {
		this.dod = dod;
	}

	public String getBsDirect() {
		return bsDirect;
	}

	public void setBsDirect(String bsDirect) {
		this.bsDirect = bsDirect;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}



	public String getOperDate() {
		return operDate;
	}

	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	public String getFundAcctName() {
		return fundAcctName;
	}

	public void setFundAcctName(String fundAcctName) {
		this.fundAcctName = fundAcctName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStrikeNo() {
		return strikeNo;
	}

	public void setStrikeNo(String strikeNo) {
		this.strikeNo = strikeNo;
	}

	@Override
	public String toString() {
		return "Fund [date=" + date + ", acNo=" + acNo + ", iae=" + iae
				+ ", iaeDesc=" + iaeDesc + ", accTitleName=" + accTitleName
				+ ", occAmt=" + occAmt + ", inAmt=" + inAmt + ", outAmt="
				+ outAmt + ", dod=" + dod + ", strikeNo=" + strikeNo
				+ ", bsDirect=" + bsDirect + ", bsDesc=" + bsDesc + ", status="
				+ status + ", statusDesc=" + statusDesc + ", operDate="
				+ operDate + ", fundAcctName=" + fundAcctName + ", remark="
				+ remark + "]";
	}


	

	

}
