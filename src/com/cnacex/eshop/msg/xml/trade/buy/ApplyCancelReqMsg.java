package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.CancelReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class ApplyCancelReqMsg extends AbstractReqMsg<CancelReq> {
	
	public static final String TX_CODE = "05101027";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected CancelReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public CancelReq getBody() {
		return body;
	}

	public void setBody(CancelReq body) {
		this.body = body;
	}


	
	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
