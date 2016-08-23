package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.ROutWarehouseRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 注册仓单撤销应答XML（交易码60005014）XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class ROutWarehouseRspMsg extends AbstractRspMsg<ROutWarehouseRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected ROutWarehouseRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public ROutWarehouseRsp getBody() {
		return body;
	}

	public void setBody(ROutWarehouseRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}
