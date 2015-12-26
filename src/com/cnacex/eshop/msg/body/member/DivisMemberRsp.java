package com.cnacex.eshop.msg.body.member;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class DivisMemberRsp {
	
	@XStreamAlias("nextstart")
	private String nextStart;
	
	@XStreamAlias("totalnum")
	private int totalNum;
	
	@XStreamAlias("currnum")
	private int currNum;
	
	@XStreamImplicit
	private List<MemList> memList;
	
	@XStreamAlias("memlist")
	public static class MemList{
		
		@XStreamAlias("mid")
		private String mID;
		
		@XStreamAlias("memname")
		private String memName;

		public String getmID() {
			return mID;
		}

		public void setmID(String mID) {
			this.mID = mID;
		}

		public String getMemName() {
			return memName;
		}

		public void setMemName(String memName) {
			this.memName = memName;
		}
	}

	public String getNextStart() {
		return nextStart;
	}

	public void setNextStart(String nextStart) {
		this.nextStart = nextStart;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getCurrNum() {
		return currNum;
	}

	public void setCurrNum(int currNum) {
		this.currNum = currNum;
	}

	public List<MemList> getMemList() {
		return memList;
	}

	public void setMemList(List<MemList> memList) {
		this.memList = memList;
	}

}
