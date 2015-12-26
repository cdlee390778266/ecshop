package com.cnacex.eshop.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class CommReqMsg extends AbstractReqMsg<String> {

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected String body;
	
	
	protected String txCode;

	public void setTxCode(String txCode) {
		this.txCode = txCode;
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	


	public String getTxCode(){
		return txCode;
	}
	

}
