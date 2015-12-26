package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;

import com.cnacex.eshop.msg.body.trade.sell.AuditReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class AuditReqMsg extends AbstractReqMsg<AuditReq> {
	
	public static final String TX_CODE = "05101002";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected AuditReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public AuditReq getBody() {
		return body;
	}

	public void setBody(AuditReq body) {
		this.body = body;
	}

	public String getTxCode(){
		return TX_CODE;
	}
	

}
