package com.cnacex.eshop.msg.body.trade.delivery;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class BillRsp {
	
	@XStreamAlias("nextstart")
	private String nextStart;
	
	@XStreamAlias("totalnum")
	private int totalNum;
	
	@XStreamAlias("currnum")
	private int currNum;
	
	
	private int totalPage;
	
	@XStreamImplicit(itemFieldName = "delist")
	private List<Strike> strikes;

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

	public List<Strike> getStrikes() {
		return strikes;
	}

	public void setStrikes(List<Strike> strikes) {
		this.strikes = strikes;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	


}
