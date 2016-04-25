package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.WRBuyOrderDetailReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 摘牌单详细信息查询请求报文
 * @author frog
 *
 */
@XStreamAlias("cnacex:root")
public class WRBuyOrderDetailReqMsg extends AbstractReqMsg<WRBuyOrderDetailReq> {

	/**
	 * 摘牌单详细信息查询
	 */
	public static final String TX_CODE = "60006003";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected WRBuyOrderDetailReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public WRBuyOrderDetailReq getBody() {
		return body;
	}

	public void setBody(WRBuyOrderDetailReq body) {
		this.body = body;
	}

	public String getTxCode() {
		return TX_CODE;
	}
}
