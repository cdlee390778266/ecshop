package com.cnacex.eshop.msg.xml.member;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.PwdResetReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author 孙超
 *
 */

@XStreamAlias("cnacex:root")
public  class PwdResetReqMsg extends AbstractReqMsg<PwdResetReq> {
	
	public static final String TX_CODE = "01000007";
	

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected PwdResetReq body;
	

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public PwdResetReq getBody() {
		return body;
	}

	public void setBody(PwdResetReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}
}
