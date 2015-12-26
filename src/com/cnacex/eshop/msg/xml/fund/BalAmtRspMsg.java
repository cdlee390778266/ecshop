package com.cnacex.eshop.msg.xml.fund;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.fund.BalAmtRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
@XStreamAlias("cnacex:root")
public class BalAmtRspMsg extends AbstractRspMsg<BalAmtRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected BalAmtRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BalAmtRsp getBody() {
		return body;
	}

	public void setBody(BalAmtRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
	

}
