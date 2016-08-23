package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.IssueWarehouseListRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 签发仓单清单查询应答XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class IssueWarehouseListRspMsg extends AbstractRspMsg<IssueWarehouseListRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected IssueWarehouseListRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public IssueWarehouseListRsp getBody() {
		return body;
	}

	public void setBody(IssueWarehouseListRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
}
