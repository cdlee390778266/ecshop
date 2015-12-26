package com.cnacex.eshop.msg.xml.member;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.DivisMemberReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * @author kereny
 * 请求区域会员
 */
public class DivisMemberReqMsg extends AbstractReqMsg<DivisMemberReq>{
	public static final String TX_CODE = "01000009";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected DivisMemberReq body;
	

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public DivisMemberReq getBody() {
		return body;
	}

	public void setBody(DivisMemberReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}
}
