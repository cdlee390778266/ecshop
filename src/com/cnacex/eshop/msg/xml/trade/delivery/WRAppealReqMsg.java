package com.cnacex.eshop.msg.xml.trade.delivery;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.delivery.WRAppealReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单交易投诉请求报文
 * @author frog
 *
 */
@XStreamAlias("cnacex:root")
public class WRAppealReqMsg extends AbstractReqMsg<WRAppealReq> {

	public static final String TX_CODE = "60006006";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected WRAppealReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public WRAppealReq getBody() {
		return body;
	}

	public void setBody(WRAppealReq body) {
		this.body = body;
	}

	public String getTxCode() {
		return TX_CODE;
	}
}
