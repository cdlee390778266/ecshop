package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.WRAuditRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单摘牌审核响应报文
 * @author frog
 *
 */
@XStreamAlias("cnacex:root")
public class WRAuditRspMsg extends AbstractRspMsg<WRAuditRsp> {

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected WRAuditRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public WRAuditRsp getBody() {
		return body;
	}

	public void setBody(WRAuditRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}
