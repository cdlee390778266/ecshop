package com.cnacex.eshop.msg.body.contract;

import java.util.List;

import com.cnacex.eshop.msg.body.trade.delivery.Strike;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */
public class QueryListRsp {
	
	@XStreamAlias("nextstart")
	private String nextStart;
	
	@XStreamAlias("totalnum")
	private int totalNum;
	
	@XStreamAlias("currnum")
	private int currNum;
	
	private int totalPage;
	
	@XStreamImplicit(itemFieldName = "contlist")
	private List<Strike> contracts;
	
	public String getNextStart() {
		return nextStart;
	}

	public void setNextStart(String nextStart) {
		this.nextStart = nextStart;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
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

	public List<Strike> getContracts() {
		return contracts;
	}

	public void setContracts(List<Strike> contracts) {
		this.contracts = contracts;
	}
	
	

}
