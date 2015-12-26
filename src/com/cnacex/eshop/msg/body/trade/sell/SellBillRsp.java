
package com.cnacex.eshop.msg.body.trade.sell;

import java.util.List;

import com.cnacex.eshop.msg.body.mall.Listed;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */
public class SellBillRsp {
	
	@XStreamAlias("nextstart")
	private String nextStart;
	
	@XStreamAlias("totalnum")
	private int	totalNum;
	
	@XStreamAlias("currnum")
	private int	currNum;
	
	@XStreamImplicit(itemFieldName = "listed")
	private List<Listed> listeds;
	
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

	public List<Listed> getListeds() {
		return listeds;
	}

	public void setListeds(List<Listed> listeds) {
		this.listeds = listeds;
	}	

}
