package com.cnacex.eshop.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;


public class CommRspMsg extends AbstractRspMsg<String> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected String body;
	
	@XStreamAlias("fault")
	protected Fault fault;

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

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}
