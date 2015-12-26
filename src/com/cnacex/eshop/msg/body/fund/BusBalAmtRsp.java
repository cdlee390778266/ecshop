package com.cnacex.eshop.msg.body.fund;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class BusBalAmtRsp {

	
	@XStreamAlias("mid")
	private String mID;

	@XStreamAlias("totalbal")
	private double totalBal;
	
	@XStreamAlias("totalabal")
	private double totalABal;
	
	@XStreamImplicit
	private List<BusDetail> details;
	
	@XStreamAlias("detail")
	public static class BusDetail{
		
		@XStreamAlias("accttype")
		private String acctType;
		
		private String acctTypeName;
		
		@XStreamAlias("totalbal")
		private double totalBal;
		
		@XStreamAlias("avalbal")
		private double avalBal;
		
		@XStreamAlias("frzamt")
		private double frzAmt;
		
		@XStreamAlias("frzmargin")
		private double frzMargin;
		
		@XStreamAlias("frzinvmargin")
		private double frzInvMargin;
		
		@XStreamAlias("frzpog")
		private double frzPOG;
		
		@XStreamAlias("frzpond")
		private double frzPond;

		public String getAcctType() {
			return acctType;
		}

		public void setAcctType(String acctType) {
			this.acctType = acctType;
		}

		public String getAcctTypeName() {
			return acctTypeName;
		}

		public void setAcctTypeName(String acctTypeName) {
			this.acctTypeName = acctTypeName;
		}

		public double getTotalBal() {
			return totalBal;
		}

		public void setTotalBal(double totalBal) {
			this.totalBal = totalBal;
		}

		public double getAvalBal() {
			return avalBal;
		}

		public void setAvalBal(double avalBal) {
			this.avalBal = avalBal;
		}

		public double getFrzAmt() {
			return frzAmt;
		}

		public void setFrzAmt(double frzAmt) {
			this.frzAmt = frzAmt;
		}

		public double getFrzMargin() {
			return frzMargin;
		}

		public void setFrzMargin(double frzMargin) {
			this.frzMargin = frzMargin;
		}

		public double getFrzPOG() {
			return frzPOG;
		}

		public void setFrzPOG(double frzPOG) {
			this.frzPOG = frzPOG;
		}

		public double getFrzPond() {
			return frzPond;
		}

		public void setFrzPond(double frzPond) {
			this.frzPond = frzPond;
		}

		public double getFrzInvMargin() {
			return frzInvMargin;
		}

		public void setFrzInvMargin(double frzInvMargin) {
			this.frzInvMargin = frzInvMargin;
		}
		
		
		
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public double getTotalBal() {
		return totalBal;
	}

	public void setTotalBal(double totalBal) {
		this.totalBal = totalBal;
	}

	public double getTotalABal() {
		return totalABal;
	}

	public void setTotalABal(double totalABal) {
		this.totalABal = totalABal;
	}

	public List<BusDetail> getDetails() {
		return details;
	}

	public void setDetails(List<BusDetail> details) {
		this.details = details;
	}
	
	
	
	

}
