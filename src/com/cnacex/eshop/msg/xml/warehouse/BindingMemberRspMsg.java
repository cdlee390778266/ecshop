package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.BindingMemberRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 关联会员关联关系查询响应报文
 * 
 * @author frog
 *
 */
@XStreamAlias("cnacex:root")
public class BindingMemberRspMsg extends AbstractRspMsg<BindingMemberRsp> {

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected BindingMemberRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BindingMemberRsp getBody() {
		return body;
	}

	public void setBody(BindingMemberRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}
