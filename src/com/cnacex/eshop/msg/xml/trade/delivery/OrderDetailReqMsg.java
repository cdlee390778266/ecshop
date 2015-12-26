package com.cnacex.eshop.msg.xml.trade.delivery;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.delivery.OrderDetailReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class OrderDetailReqMsg extends AbstractReqMsg<OrderDetailReq> {
	
	
	public static final String TX_CODE = "05101047";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected OrderDetailReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public OrderDetailReq getBody() {
		return body;
	}

	public void setBody(OrderDetailReq body) {
		this.body = body;
	}

	public  String getTxCode() {
		return TX_CODE;
	}
	
}
