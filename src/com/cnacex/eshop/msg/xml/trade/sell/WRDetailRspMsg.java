package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.sell.WRDetailRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单详情查询应答XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class WRDetailRspMsg extends AbstractRspMsg<WRDetailRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected WRDetailRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public WRDetailRsp getBody() {
		return body;
	}

	public void setBody(WRDetailRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
}
