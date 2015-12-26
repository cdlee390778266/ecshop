package com.cnacex.eshop.msg.xml.trade.delivery;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.delivery.AppealBillRsp;
import com.cnacex.eshop.msg.body.trade.delivery.BillRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;


public class AppealBillRspMsg extends AbstractRspMsg<AppealBillRsp> {
	
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected AppealBillRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public AppealBillRsp getBody() {
		return body;
	}

	public void setBody(AppealBillRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
	

}
