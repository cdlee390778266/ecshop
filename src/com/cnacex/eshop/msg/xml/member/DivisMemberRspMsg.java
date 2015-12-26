
package com.cnacex.eshop.msg.xml.member;
import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.DivisMemberRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author sunc
 *
 */
public class DivisMemberRspMsg extends AbstractRspMsg<DivisMemberRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected DivisMemberRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public DivisMemberRsp getBody() {
		return body;
	}

	public void setBody(DivisMemberRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}
