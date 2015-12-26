package com.cnacex.eshop.msg.xml.member;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.LoginReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */

public class LoginReqMsg extends AbstractReqMsg<LoginReq> {
	
	public static final String TX_CODE = "01000001";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected LoginReq body;
	

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public LoginReq getBody() {
		return body;
	}

	public void setBody(LoginReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}



}
