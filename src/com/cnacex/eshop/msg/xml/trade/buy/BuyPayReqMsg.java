package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.BuyPayReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class BuyPayReqMsg extends AbstractReqMsg<BuyPayReq> {

	public static final String TX_CODE = "05101023";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected BuyPayReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BuyPayReq getBody() {
		return body;
	}

	public void setBody(BuyPayReq body) {
		this.body = body;
	}

	public  String getTxCode() {
		return TX_CODE;
	}
	

}
