package com.cnacex.eshop.msg.xml.trade.delivery;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.delivery.BillReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class SellBillReqMsg extends AbstractReqMsg<BillReq> {
	
	
	public static final String TX_CODE = "05101044";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected BillReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BillReq getBody() {
		return body;
	}

	public void setBody(BillReq body) {
		this.body = body;
	}

	public  String getTxCode() {
		return TX_CODE;
	}
	
}
