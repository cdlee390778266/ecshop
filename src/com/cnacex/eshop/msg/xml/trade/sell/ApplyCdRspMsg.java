package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.sell.ApplyCdRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 注册仓单查询应答XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class ApplyCdRspMsg extends AbstractRspMsg<ApplyCdRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected ApplyCdRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public ApplyCdRsp getBody() {
		return body;
	}

	public void setBody(ApplyCdRsp	 body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
}
