package com.cnacex.eshop.msg.xml.mall;

import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.mall.QueryCommRsp;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
public class QueryCommRspMsg extends AbstractRspMsg<QueryCommRsp> {
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected QueryCommRsp body;
	
	@XStreamAlias("fault")
	protected Fault fault;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public QueryCommRsp getBody() {
		return body;
	}

	public void setBody(QueryCommRsp body) {
		this.body = body;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}
	
	

}
