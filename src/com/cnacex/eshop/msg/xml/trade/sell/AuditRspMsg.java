package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.sell.AuditRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class AuditRspMsg extends AbstractRspMsg<AuditRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected AuditRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public AuditRsp getBody() {
		return body;
	}

	public void setBody(AuditRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
}
