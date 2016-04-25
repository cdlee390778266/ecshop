package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.TrigBindingMemberReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class TrigBindingMemberReqMsg extends AbstractReqMsg<TrigBindingMemberReq> {

public static final String TX_CODE = "60005009";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected TrigBindingMemberReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public TrigBindingMemberReq getBody() {
		return body;
	}

	public void setBody(TrigBindingMemberReq body) {
		this.body = body;
	}

	public String getTxCode() {
		return TX_CODE;
	}
}
