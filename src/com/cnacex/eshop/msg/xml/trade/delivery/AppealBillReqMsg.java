package com.cnacex.eshop.msg.xml.trade.delivery;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.delivery.AppealBillReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class AppealBillReqMsg extends AbstractReqMsg<AppealBillReq> {
	
	public static final String TX_CODE = "05101048";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected AppealBillReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public AppealBillReq getBody() {
		return body;
	}

	public void setBody(AppealBillReq body) {
		this.body = body;
	}

	public  String getTxCode() {
		return TX_CODE;
	}
	
}
