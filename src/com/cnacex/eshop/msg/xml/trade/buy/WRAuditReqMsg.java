package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.WRAuditReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单摘牌审核请求报文
 * @author frog
 *
 */
@XStreamAlias("cnacex:root")
public class WRAuditReqMsg extends AbstractReqMsg<WRAuditReq> {

	public static final String TX_CODE = "60006002";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected WRAuditReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public WRAuditReq getBody() {
		return body;
	}

	public void setBody(WRAuditReq body) {
		this.body = body;
	}

	public String getTxCode() {
		return TX_CODE;
	}
}
