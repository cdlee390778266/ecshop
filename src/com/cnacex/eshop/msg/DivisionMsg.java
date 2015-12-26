package com.cnacex.eshop.msg;

import com.cnacex.eshop.modul.Division;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class DivisionMsg {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected Division body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Division getBody() {
		return body;
	}

	public void setBody(Division body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}

}
