
package com.cnacex.eshop.msg.xml.member;
import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.InfoQueryRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author sunc
 *
 */
@XStreamAlias("cnacex:root")
public class InfoQueryRspMsg extends AbstractRspMsg<InfoQueryRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected InfoQueryRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public InfoQueryRsp getBody() {
		return body;
	}

	public void setBody(InfoQueryRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}
