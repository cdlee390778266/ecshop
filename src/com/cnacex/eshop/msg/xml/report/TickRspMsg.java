package com.cnacex.eshop.msg.xml.report;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.report.TickRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */

public class TickRspMsg extends AbstractRspMsg<TickRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected TickRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public TickRsp getBody() {
		return body;
	}

	public void setBody(TickRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
	

}
