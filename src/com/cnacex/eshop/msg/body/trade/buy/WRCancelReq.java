package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单摘牌撤销请求
 * @author frog
 *
 */
public class WRCancelReq {

	/**
	 * 交易会员
	 */
	@XStreamAlias("mid")
	private String mid;
	
	/**
	 * 摘牌/成交编号
	 */
	@XStreamAlias("dsno")
	private String dsNO;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getDsNO() {
		return dsNO;
	}

	public void setDsNO(String dsNO) {
		this.dsNO = dsNO;
	}
	
	@Override
	public String toString() {
		return String.format("WRCancelReq [mid=%s, dsNO=%s]", mid, dsNO);
	}
}
