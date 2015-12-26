package com.cnacex.eshop.msg.body.fund;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */

public class QueryTxSRsp {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamImplicit
	private List<Acct> accts;
	
	@XStreamAlias("accts")
	public static class Acct{
		
		@XStreamAlias("qryacctype")
		private String qryAccType;
		
		@XStreamImplicit
		private List<OccDetail> occDetails;
		
		@XStreamAlias("occdetail")
		public static class OccDetail{
			
			@XStreamAlias("txsn")
			private String txSN;
			
			@XStreamAlias("txtime")
			private String txTime;
			
			@XStreamAlias("trtype")
			private String trType;
			
			@XStreamImplicit
			private List<FeeList> feeLists;
			
			@XStreamAlias("feelist")
			public static class FeeList{
				
				@XStreamAlias("fundcode")
				private String fundCode;
				
				private String fundName;
				
				@XStreamAlias("occamt")
				private double occAmt;

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

				public double getOccAmt() {
					return occAmt;
				}

				public void setOccAmt(double occAmt) {
					this.occAmt = occAmt;
				}
				
			}
			@XStreamAlias("flag")
			private int flag;
			
			@XStreamAlias("settflag")
			private int settFlag;

			public String getTxSN() {
				return txSN;
			}

			public void setTxSN(String txSN) {
				this.txSN = txSN;
			}

			public String getTxTime() {
				return txTime;
			}

			public void setTxTime(String txTime) {
				this.txTime = txTime;
			}

			public String getTrType() {
				return trType;
			}

			public void setTrType(String trType) {
				this.trType = trType;
			}

			public List<FeeList> getFeeLists() {
				return feeLists;
			}

			public void setFeeLists(List<FeeList> feeLists) {
				this.feeLists = feeLists;
			}

			public int getFlag() {
				return flag;
			}

			public void setFlag(int flag) {
				this.flag = flag;
			}

			public int getSettFlag() {
				return settFlag;
			}

			public void setSettFlag(int settFlag) {
				this.settFlag = settFlag;
			}
				
		}

		public String getQryAccType() {
			return qryAccType;
		}

		public void setQryAccType(String qryAccType) {
			this.qryAccType = qryAccType;
		}

		public List<OccDetail> getOccDetails() {
			return occDetails;
		}

		public void setOccDetails(List<OccDetail> occDetails) {
			this.occDetails = occDetails;
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
