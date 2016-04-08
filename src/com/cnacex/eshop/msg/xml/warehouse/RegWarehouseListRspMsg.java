package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseListRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 签发仓单注册应答XML（交易码60005001）XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class RegWarehouseListRspMsg extends AbstractRspMsg<RegWarehouseListRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected RegWarehouseListRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public RegWarehouseListRsp getBody() {
		return body;
	}

	public void setBody(RegWarehouseListRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}
