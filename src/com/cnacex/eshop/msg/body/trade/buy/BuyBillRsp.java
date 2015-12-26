
package com.cnacex.eshop.msg.body.trade.buy;

import java.util.List;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */
public class BuyBillRsp {
	
	@XStreamAlias("nextstart")
	private String nextStart;
	
	@XStreamAlias("totalnum")
	private int	totalNum;
	
	@XStreamAlias("currnum")
	private int	currNum;
	
	
	@XStreamImplicit(itemFieldName = "delist")
	private List<Delist> delists;

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

	public List<Delist> getDelists() {
		return delists;
	}

	public void setDelists(List<Delist> delists) {
		this.delists = delists;
	}

}
