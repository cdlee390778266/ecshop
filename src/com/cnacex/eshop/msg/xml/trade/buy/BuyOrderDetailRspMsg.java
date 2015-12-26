package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.BuyOrderDetailRsp;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class BuyOrderDetailRspMsg extends AbstractRspMsg<BuyOrderDetailRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected BuyOrderDetailRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BuyOrderDetailRsp getBody() {
		return body;
	}

	public void setBody(BuyOrderDetailRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
}
