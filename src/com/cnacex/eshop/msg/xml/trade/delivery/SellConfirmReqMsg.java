package com.cnacex.eshop.msg.xml.trade.delivery;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.delivery.ConfirmReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class SellConfirmReqMsg extends AbstractReqMsg<ConfirmReq> {
	
	
	public static final String TX_CODE = "05101041";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected ConfirmReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public ConfirmReq getBody() {
		return body;
	}

	public void setBody(ConfirmReq body) {
		this.body = body;
	}

	public  String getTxCode() {
		return TX_CODE;
	}
	
}
