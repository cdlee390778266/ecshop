package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单摘牌付款请求
 * @author frog
 *
 */
public class WRBuyPayReq {

	/**
	 * 交易会员
	 */
	@XStreamAlias("mid")
	private String mid;
	
	/**
	 * 资金密码(支付密码)
	 */
	@XStreamAlias("fundPwd")
	private String fundPwd;
	
	/**
	 * 摘牌/成交编号
	 */
	@XStreamAlias("dsNo")
	private String dsNo;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getFundPwd() {
		return fundPwd;
	}

	public void setFundPwd(String fundPwd) {
		this.fundPwd = fundPwd;
	}

	public String getDsNo() {
		return dsNo;
	}

	public void setDsNo(String dsNo) {
		this.dsNo = dsNo;
	}
}
