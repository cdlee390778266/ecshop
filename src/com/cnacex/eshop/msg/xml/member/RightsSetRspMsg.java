package com.cnacex.eshop.msg.xml.member;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.InvQueryRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")

public class RightsSetRspMsg extends AbstractRspMsg<InvQueryRsp> {
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected InvQueryRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public InvQueryRsp getBody() {
		return body;
	}

	public void setBody(InvQueryRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}
