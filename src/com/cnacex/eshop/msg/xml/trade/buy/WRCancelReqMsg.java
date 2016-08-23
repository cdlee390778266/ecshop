package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.WRCancelReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单摘牌撤销请求报文
 * @author frog
 *
 */
@XStreamAlias("cnacex:root")
public class WRCancelReqMsg extends AbstractReqMsg<WRCancelReq> {

	/**
	 * 仓单摘牌撤销
	 */
	public static final String TX_CODE = "60006005";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected WRCancelReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public WRCancelReq getBody() {
		return body;
	}

	public void setBody(WRCancelReq body) {
		this.body = body;
	}

	public String getTxCode() {
		return TX_CODE;
	}
}
