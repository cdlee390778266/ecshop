package com.cnacex.eshop.msg.xml.trade.delivery;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.delivery.OrderDetailRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class OrderDetailRspMsg extends AbstractRspMsg<OrderDetailRsp> {
	
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected OrderDetailRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public OrderDetailRsp getBody() {
		return body;
	}

	public void setBody(OrderDetailRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
	

}
