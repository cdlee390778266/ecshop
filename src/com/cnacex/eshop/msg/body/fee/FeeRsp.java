package com.cnacex.eshop.msg.body.fee;

import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class FeeRsp {
	
	
	@XStreamImplicit
	private List<RspMemList> memLists;
	

	@XStreamAlias("memlist")
	public static class RspMemList{
		
		@XStreamAlias("mid")
		private String mID;
	
		
		@XStreamImplicit
		private List<RspFeeList> feelists;
		
		
		@XStreamAlias("feelist")
		public static class RspFeeList{
			
			@XStreamAlias("costcode")
			private String costCode;
			
			private String costName;
			
			@XStreamAlias("costamt")
			private double costAmt;

			public String getCostCode() {
				return costCode;
			}

			public void setCostCode(String costCode) {
				this.costCode = costCode;
			}

			public double getCostAmt() {
				return costAmt;
			}

			public void setCostAmt(double costAmt) {
				this.costAmt = costAmt;
			}

			public String getCostName() {
				return costName;
			}

			public void setCostName(String costName) {
				this.costName = costName;
			}
			
			
				
		}


		public String getmID() {
			return mID;
		}


		public void setmID(String mID) {
			this.mID = mID;
		}


		public List<RspFeeList> getFeelists() {
			return feelists;
		}


		public void setFeelists(List<RspFeeList> feelists) {
			this.feelists = feelists;
		}
	}


	public List<RspMemList> getMemLists() {
		return memLists;
	}


	public void setMemLists(List<RspMemList> memLists) {
		this.memLists = memLists;
	}



}
