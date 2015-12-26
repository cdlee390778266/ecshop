package com.cnacex.eshop.msg.body.fund;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class QueryAccSRsp {

	@XStreamAlias("mid")
	private String mID;
	
	@XStreamImplicit
	private List<Acct> accts;
	
	@XStreamAlias("accts")
	public static class Acct{
		
		@XStreamAlias("qryaccttype")
		private String qryAcctType;
		
		
		private String qryAcctTypeName;
		
		@XStreamImplicit
		private List<OccDetail> occDetails;
		
		@XStreamAlias("occdetail")
		public static class OccDetail{
			
			@XStreamAlias("acno")
			private String acNo;
			
			@XStreamAlias("iae")
			private String iae;
			
			private String iaeName;
			
			@XStreamAlias("fundcode")
			private String fundCode;
			
			private String fundName;
			
			@XStreamAlias("trtype")
			private String trType;
			
			private String trTypeName;
			
			@XStreamAlias("occtime")
			private String occTime;
			
			@XStreamAlias("befbal")
			private double befBal;
			
			@XStreamAlias("chgval")
			private double chgVal;
			
			@XStreamAlias("aftbal")
			private double aftBal;
			
			@XStreamAlias("befab")
			private double befAB;
			
			@XStreamAlias("chgabv")
			private double chgABV;
			
			@XStreamAlias("aftab")
			private double aftAB;
			
			@XStreamAlias("beffa")
			private double befFA;
			
			@XStreamAlias("chgfav")
			private double chgFAV;
			
			@XStreamAlias("aftfa")
			private double aftFA;
			
			@XStreamAlias("tsd")
			private String tsd;
			
			@XStreamAlias("remark")
			private String remark;
			
			
			@XStreamAlias("acctitle")
			private String accTitle;
			
			
			@XStreamAlias("acctitlename")
			private String accTitleName;

			@XStreamAlias("extno")
			private String extNo;

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

			public String getFundCode() {
				return fundCode;
			}

			public void setFundCode(String fundCode) {
				this.fundCode = fundCode;
			}

			public String getFundName() {
				return fundName;
			}

			public void setFundName(String fundName) {
				this.fundName = fundName;
			}

			public String getTrType() {
				return trType;
			}

			public void setTrType(String trType) {
				this.trType = trType;
			}

			public String getOccTime() {
				return occTime;
			}

			public void setOccTime(String occTime) {
				this.occTime = occTime;
			}

			public double getBefBal() {
				return befBal;
			}

			public void setBefBal(double befBal) {
				this.befBal = befBal;
			}

			public double getChgVal() {
				return chgVal;
			}

			public void setChgVal(double chgVal) {
				this.chgVal = chgVal;
			}

			public double getAftBal() {
				return aftBal;
			}

			public void setAftBal(double aftBal) {
				this.aftBal = aftBal;
			}

			public double getBefAB() {
				return befAB;
			}

			public void setBefAB(double befAB) {
				this.befAB = befAB;
			}

			public double getChgABV() {
				return chgABV;
			}

			public void setChgABV(double chgABV) {
				this.chgABV = chgABV;
			}

			public double getAftAB() {
				return aftAB;
			}

			public void setAftAB(double aftAB) {
				this.aftAB = aftAB;
			}

			public double getBefFA() {
				return befFA;
			}

			public void setBefFA(double befFA) {
				this.befFA = befFA;
			}

			public double getChgFAV() {
				return chgFAV;
			}

			public void setChgFAV(double chgFAV) {
				this.chgFAV = chgFAV;
			}

			public double getAftFA() {
				return aftFA;
			}

			public void setAftFA(double aftFA) {
				this.aftFA = aftFA;
			}

			public String getTsd() {
				return tsd;
			}

			public void setTsd(String tsd) {
				this.tsd = tsd;
			}

			public String getRemark() {
				return remark;
			}

			public void setRemark(String remark) {
				this.remark = remark;
			}

			public String getIaeName() {
				return iaeName;
			}

			public void setIaeName(String iaeName) {
				this.iaeName = iaeName;
			}

			public String getTrTypeName() {
				return trTypeName;
			}

			public void setTrTypeName(String trTypeName) {
				this.trTypeName = trTypeName;
			}

			public String getAccTitle() {
				return accTitle;
			}

			public void setAccTitle(String accTitle) {
				this.accTitle = accTitle;
			}

			public String getAccTitleName() {
				return accTitleName;
			}

			public void setAccTitleName(String accTitleName) {
				this.accTitleName = accTitleName;
			}

			public String getExtNo() {
				return extNo;
			}

			public void setExtNo(String extNo) {
				this.extNo = extNo;
			}
			
			
			
		}

		public String getQryAcctType() {
			return qryAcctType;
		}

		public void setQryAcctType(String qryAcctType) {
			this.qryAcctType = qryAcctType;
		}

		public List<OccDetail> getOccDetails() {
			return occDetails;
		}

		public void setOccDetails(List<OccDetail> occDetails) {
			this.occDetails = occDetails;
		}

		public String getQryAcctTypeName() {
			return qryAcctTypeName;
		}

		public void setQryAcctTypeName(String qryAcctTypeName) {
			this.qryAcctTypeName = qryAcctTypeName;
		}
		
		
		
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public List<Acct> getAccts() {
		return accts;
	}

	public void setAccts(List<Acct> accts) {
		this.accts = accts;
	}	
	
	
}
