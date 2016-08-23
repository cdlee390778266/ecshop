package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.sell.ApplyCdReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 注册仓单查询请求XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class ApplyCdReqMsg extends AbstractReqMsg<ApplyCdReq> {
	
	public static final String TX_CODE = "60005011";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected ApplyCdReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}
	
	public ApplyCdReq getBody() {
		return body;
	}

	public void setBody(ApplyCdReq body) {
		this.body = body;
	}

	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
