package com.cnacex.eshop.msg.body.query;

import java.util.List;

import com.cnacex.eshop.msg.body.trade.delivery.Strike;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class HisQueryRsp {
	
	@XStreamAlias("nextstart")
	private String NextStart;
	
	@XStreamAlias("totalnum")
	private int totalNum;
	
	@XStreamAlias("currNum")
	private int currnum;
	
	@XStreamImplicit(itemFieldName = "strikelist")
	private List<Strike> hisStrikes;
	
	
	public String getNextStart() {
		return NextStart;
	}

	public void setNextStart(String nextStart) {
		NextStart = nextStart;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getCurrnum() {
		return currnum;
	}

	public void setCurrnum(int currnum) {
		this.currnum = currnum;
	}

	public List<Strike> getHisStrikes() {
		return hisStrikes;
	}

	public void setHisStrikes(List<Strike> hisStrikes) {
		this.hisStrikes = hisStrikes;
	}

}
