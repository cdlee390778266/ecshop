package com.cnacex.eshop.msg.body.fund;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class BindAccQueryRsp {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamImplicit
	private List<PayDetail> payDetails;
	
	@XStreamAlias("paydetail")
	public static class PayDetail{
		
		@XStreamAlias("pbacct")
		private String pbAcct;
		
		@XStreamAlias("pbatype")
		private String pbAType;
		
		
		private String pbATypeName;
		
		@XStreamAlias("pbastatus")
		private String pbAStatus;
		
		private String pbAStatusDesc;
		
		@XStreamAlias("status")
		private String status;

		
		private String statusDesc;
		
		
		public String getPbAcct() {
			return pbAcct;
		}

		public void setPbAcct(String pbAcct) {
			this.pbAcct = pbAcct;
		}

		public String getPbAType() {
			return pbAType;
		}

		public void setPbAType(String pbAType) {
			this.pbAType = pbAType;
		}

		public String getPbAStatus() {
			return pbAStatus;
		}

		public void setPbAStatus(String pbAStatus) {
			this.pbAStatus = pbAStatus;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getPbATypeName() {
			return pbATypeName;
		}

		public void setPbATypeName(String pbATypeName) {
			this.pbATypeName = pbATypeName;
		}

		public String getPbAStatusDesc() {
			return pbAStatusDesc;
		}

		public void setPbAStatusDesc(String pbAStatusDesc) {
			this.pbAStatusDesc = pbAStatusDesc;
		}

		public String getStatusDesc() {
			return statusDesc;
		}

		public void setStatusDesc(String statusDesc) {
			this.statusDesc = statusDesc;
		}
		
		
		
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public List<PayDetail> getPayDetails() {
		return payDetails;
	}

	public void setPayDetails(List<PayDetail> payDetails) {
		this.payDetails = payDetails;
	}



}
