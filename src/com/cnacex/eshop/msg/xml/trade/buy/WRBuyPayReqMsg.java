package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.WRBuyPayReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单摘牌付款请求报文
 * @author frog
 *
 */
@XStreamAlias("cnacex:root")
public class WRBuyPayReqMsg extends AbstractReqMsg<WRBuyPayReq> {

	public static final String TX_CODE = "60006004";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected WRBuyPayReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public WRBuyPayReq getBody() {
		return body;
	}

	public void setBody(WRBuyPayReq body) {
		this.body = body;
	}

	public String getTxCode() {
		return TX_CODE;
	}
}
