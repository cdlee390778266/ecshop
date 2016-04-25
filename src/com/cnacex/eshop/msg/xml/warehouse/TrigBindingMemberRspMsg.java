package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.TrigBindingMemberRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class TrigBindingMemberRspMsg extends AbstractRspMsg<TrigBindingMemberRsp> {

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected TrigBindingMemberRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public TrigBindingMemberRsp getBody() {
		return body;
	}

	public void setBody(TrigBindingMemberRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}
