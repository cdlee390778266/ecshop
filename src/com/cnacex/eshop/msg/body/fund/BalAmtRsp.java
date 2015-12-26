package com.cnacex.eshop.msg.body.fund;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */
public class BalAmtRsp {
	
	@XStreamAlias("mid")
	private String mID;

	@XStreamAlias("totalbal")
	private double totalBal;
	
	@XStreamAlias("totalabal")
	private double totalABal;
	
	@XStreamAlias("totalfamt")
	private double totalFAmt;
	
	@XStreamImplicit
	private List<Detail> details;
	
	@XStreamAlias("detail")
	public static class Detail{
		
		@XStreamAlias("accttype")
		private String acctType;
		
		
		private String acctTypeName;
		
		@XStreamAlias("bal")
		private double bal;
		
		@XStreamAlias("abal")
		private double abal;
		
		@XStreamAlias("famt")
		private double famt;
		
		@XStreamAlias("openbal")
		private double openBal;
		
		@XStreamAlias("closebal")
		private double closeBal;
		
		@XStreamAlias("openab")
		private double openAB;
		
		@XStreamAlias("closeab")
		private double closeAB;
		
		@XStreamAlias("openfa")
		private double openFA;
		
		@XStreamAlias("closefa")
		private double closeFA;
		
		@XStreamAlias("acdate")
		private String acDate;

		public String getAcctType() {
			return acctType;
		}

		public void setAcctType(String acctType) {
			this.acctType = acctType;
		}

		public double getBal() {
			return bal;
		}

		public void setBal(double bal) {
			this.bal = bal;
		}

		public double getAbal() {
			return abal;
		}

		public void setAbal(double abal) {
			this.abal = abal;
		}

		public double getFamt() {
			return famt;
		}

		public void setFamt(double famt) {
			this.famt = famt;
		}

		public double getOpenBal() {
			return openBal;
		}

		public void setOpenBal(double openBal) {
			this.openBal = openBal;
		}

		public double getCloseBal() {
			return closeBal;
		}

		public void setCloseBal(double closeBal) {
			this.closeBal = closeBal;
		}

		public double getOpenAB() {
			return openAB;
		}

		public void setOpenAB(double openAB) {
			this.openAB = openAB;
		}

		public double getCloseAB() {
			return closeAB;
		}

		public void setCloseAB(double closeAB) {
			this.closeAB = closeAB;
		}

		public double getOpenFA() {
			return openFA;
		}

		public void setOpenFA(double openFA) {
			this.openFA = openFA;
		}

		public double getCloseFA() {
			return closeFA;
		}

		public void setCloseFA(double closeFA) {
			this.closeFA = closeFA;
		}

		public String getAcDate() {
			return acDate;
		}

		public void setAcDate(String acDate) {
			this.acDate = acDate;
		}

		@Override
		public String toString() {
			return "Detail [acctType=" + acctType + ", bal=" + bal + ", abal="
					+ abal + ", famt=" + famt + ", openBal=" + openBal
					+ ", closeBal=" + closeBal + ", openAB=" + openAB
					+ ", closeAB=" + closeAB + ", openFA=" + openFA
					+ ", closeFA=" + closeFA + ", acDate=" + acDate + "]";
		}

		public String getAcctTypeName() {
			return acctTypeName;
		}

		public void setAcctTypeName(String acctTypeName) {
			this.acctTypeName = acctTypeName;
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

	public double getTotalFAmt() {
		return totalFAmt;
	}

	public void setTotalFAmt(double totalFAmt) {
		this.totalFAmt = totalFAmt;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "BalAmtRsp [mID=" + mID + ", totalBal=" + totalBal
				+ ", totalABal=" + totalABal + ", totalFAmt=" + totalFAmt
				+ ", details=" + details + "]";
	}
	
	
	

}
