package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 摘牌单详细信息查询请求
 * @author frog
 *
 */
public class WRBuyOrderDetailReq {

	/**
	 * 摘牌会员
	 */
	@XStreamAlias("mid")
	private String mid;
	
	/**
	 * 挂牌仓单编号
	 */
	@XStreamAlias("delistno")
	private String delistNo;
	
	/**
	 * 摘牌时间（开始）
	 */
	@XStreamAlias("datebegin")
	private String dateBegin;
	
	/**
	 * 摘牌时间（结束）
	 */
	@XStreamAlias("dateend")
	private String dateEnd;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getDelistNo() {
		return delistNo;
	}

	public void setDelistNo(String delistNo) {
		this.delistNo = delistNo;
	}

	public String getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
}
