package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseCancelRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 待审核注册仓单应答XML（交易码60005012）XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class RegWarehouseCancelRspMsg extends AbstractRspMsg<RegWarehouseCancelRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected RegWarehouseCancelRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public RegWarehouseCancelRsp getBody() {
		return body;
	}

	public void setBody(RegWarehouseCancelRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}
