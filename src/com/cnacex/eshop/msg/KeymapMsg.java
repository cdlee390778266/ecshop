package com.cnacex.eshop.msg;

import com.cnacex.eshop.modul.ItemKV;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class KeymapMsg {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected ItemKV body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public ItemKV getBody() {
		return body;
	}

	public void setBody(ItemKV body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
	

}
