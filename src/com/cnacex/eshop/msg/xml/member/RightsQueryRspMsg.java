package com.cnacex.eshop.msg.xml.member;
import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.RightsQueryRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author sunc
 *
 */
@XStreamAlias("cnacex:root")
public class RightsQueryRspMsg extends AbstractRspMsg<RightsQueryRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected RightsQueryRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public RightsQueryRsp getBody() {
		return body;
	}

	public void setBody(RightsQueryRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}