package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.BuyOrderDetailReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class BuyOrderDetailReqMsg extends AbstractReqMsg<BuyOrderDetailReq> {

	public static final String TX_CODE = "05101025";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected BuyOrderDetailReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BuyOrderDetailReq getBody() {
		return body;
	}

	public void setBody(BuyOrderDetailReq body) {
		this.body = body;
	}

	public  String getTxCode() {
		return TX_CODE;
	}
	

}
