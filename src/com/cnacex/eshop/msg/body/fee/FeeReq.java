package com.cnacex.eshop.msg.body.fee;

import java.util.List;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class FeeReq {
	

	@XStreamImplicit
	private List<ReqMemList> memLists;
	

	@XStreamAlias("memlist")
	public static class ReqMemList{
		
		@XStreamAlias("mid")
		private String mID;
		
		@XStreamAlias("memlevel")
		private String memLevel;
		
		@XStreamAlias("markcode")
		private String markCode;
		
		@XStreamAlias("pog")
		private String pog;
		
		@XStreamAlias("wramt")
		private String wrAmt;
		
		
		@XStreamImplicit
		private List<ReqFeeList> feelists;
		
		
		@XStreamAlias("feelist")
		public static class ReqFeeList{
			
			@XStreamAlias("costcode")
			private String costCode;
			
			@XStreamAlias("spvalunit")
			private String spValUnit;

			public String getCostCode() {
				return costCode;
			}

			public void setCostCode(String costCode) {
				this.costCode = costCode;
			}

			public String getSpValUnit() {
				return spValUnit;
			}

			public void setSpValUnit(String spValUnit) {
				this.spValUnit = spValUnit;
			}
			
			
			
		}


		public String getmID() {
			return mID;
		}


		public void setmID(String mID) {
			this.mID = mID;
		}


		public String getMemLevel() {
			return memLevel;
		}


		public void setMemLevel(String memLevel) {
			this.memLevel = memLevel;
		}


		public String getMarkCode() {
			return markCode;
		}


		public void setMarkCode(String markCode) {
			this.markCode = markCode;
		}


		public String getPog() {
			return pog;
		}


		public void setPog(String pog) {
			this.pog = pog;
		}


		public String getWrAmt() {
			return wrAmt;
		}


		public void setWrAmt(String wrAmt) {
			this.wrAmt = wrAmt;
		}


		public List<ReqFeeList> getFeelists() {
			return feelists;
		}


		public void setFeelists(List<ReqFeeList> feelists) {
			this.feelists = feelists;
		}




	}


	public List<ReqMemList> getMemLists() {
		return memLists;
	}


	public void setMemLists(List<ReqMemList> memLists) {
		this.memLists = memLists;
	}



	
	
}
