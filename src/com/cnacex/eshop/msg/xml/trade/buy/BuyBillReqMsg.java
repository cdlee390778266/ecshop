package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.BuyBillReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class BuyBillReqMsg extends AbstractReqMsg<BuyBillReq> {

	public static final String TX_CODE = "05101024";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected BuyBillReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BuyBillReq getBody() {
		return body;
	}

	public void setBody(BuyBillReq body) {
		this.body = body;
	}

	public  String getTxCode() {
		return TX_CODE;
	}
	

}
