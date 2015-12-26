package com.cnacex.eshop.msg.xml.member;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.MemTradeInfoSetReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * @author 孙超
 *会员交易基本信息设置请求报文信息类
 */

public class MemTradeInfoSetReqMsg extends AbstractReqMsg<MemTradeInfoSetReq>{
	public static final String TX_CODE = "01000009";

	

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected MemTradeInfoSetReq body;
	

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public MemTradeInfoSetReq getBody() {
		return body;
	}

	public void setBody(MemTradeInfoSetReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}
}
