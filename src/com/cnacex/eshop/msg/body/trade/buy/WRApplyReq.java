package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单摘牌申请请求
 * @author frog
 *
 */
public class WRApplyReq {

	/**
	 * 交易会员
	 */
	@XStreamAlias("mid")
	private String mid;
	
	/**
	 * 挂牌仓单编号
	 */
	@XStreamAlias("listedno")
	private String listedNo;
	
	/**
	 * 付款方式
	 */
	@XStreamAlias("top")
	private String top;
	
	/**
	 * 摘牌附言
	 */
	@XStreamAlias("remark")
	private String remark;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getListedNo() {
		return listedNo;
	}

	public void setListedNo(String listedNo) {
		this.listedNo = listedNo;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return String.format("WRApplyReq[mid=%s, listedNo=%s, top=%s, remark=%s]", mid, listedNo, top, remark);
	}
}
