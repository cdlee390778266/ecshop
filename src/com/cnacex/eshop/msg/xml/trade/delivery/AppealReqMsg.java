package com.cnacex.eshop.msg.xml.trade.delivery;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.delivery.AppealReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class AppealReqMsg extends AbstractReqMsg<AppealReq> {
	
	
	public static final String TX_CODE = "05101046";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected AppealReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public AppealReq getBody() {
		return body;
	}

	public void setBody(AppealReq body) {
		this.body = body;
	}

	public  String getTxCode() {
		return TX_CODE;
	}
	
}
