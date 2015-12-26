package com.cnacex.eshop.msg.xml.member;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.OperManageReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author 孙超
 *
 */

@XStreamAlias("cnacex:root")
public  class OperManageReqMsg extends AbstractReqMsg<OperManageReq> {
	
	public static final String TX_CODE = "01000005";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected OperManageReq body;
	

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public OperManageReq getBody() {
		return body;
	}

	public void setBody(OperManageReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}
}
