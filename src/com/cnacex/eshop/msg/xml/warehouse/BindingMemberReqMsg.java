package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.BindingMemberReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 关联会员关联关系查询请求报文
 * 
 * @author frog
 *
 */
@XStreamAlias("cnacex:root")
public class BindingMemberReqMsg extends AbstractReqMsg<BindingMemberReq> {

	public static final String TX_CODE = "60005015";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected BindingMemberReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BindingMemberReq getBody() {
		return body;
	}

	public void setBody(BindingMemberReq body) {
		this.body = body;
	}

	public String getTxCode() {
		return TX_CODE;
	}
}
