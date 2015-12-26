package com.cnacex.eshop.msg.xml.member;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.RightsQueryReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author 孙超
 *
 */

@XStreamAlias("cnacex:root")
public class RightsQueryReqMsg extends AbstractReqMsg<RightsQueryReq> {
	
	public static final String TX_CODE = "01000004";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected RightsQueryReq body;
	

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public RightsQueryReq getBody() {
		return body;
	}

	public void setBody(RightsQueryReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}
}
