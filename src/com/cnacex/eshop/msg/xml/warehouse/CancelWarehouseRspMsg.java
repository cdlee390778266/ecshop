package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 注册仓单撤销应答XML（交易码60005014）XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class CancelWarehouseRspMsg extends AbstractRspMsg<CommRspMsg> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected CommRspMsg body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public CommRspMsg getBody() {
		return body;
	}

	public void setBody(CommRspMsg body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}
