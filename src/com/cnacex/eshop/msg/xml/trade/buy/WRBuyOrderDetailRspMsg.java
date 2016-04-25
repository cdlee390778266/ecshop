package com.cnacex.eshop.msg.xml.trade.buy;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.buy.WRBuyOrderDetailRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 摘牌单详细信息查询响应报文
 * @author frog
 *
 */
@XStreamAlias("cnacex:root")
public class WRBuyOrderDetailRspMsg extends AbstractRspMsg<WRBuyOrderDetailRsp> {

	@XStreamAlias("head")
	protected Head head;
	
	@XStreamAlias("body")
	protected WRBuyOrderDetailRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public WRBuyOrderDetailRsp getBody() {
		return body;
	}

	public void setBody(WRBuyOrderDetailRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
}
