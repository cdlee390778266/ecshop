package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.sell.ApplyCdListedReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 注册仓单挂牌请求XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class ApplyCdListedReqMsg extends AbstractReqMsg<ApplyCdListedReq> {
	
	public static final String TX_CODE = "60005002";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected ApplyCdListedReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}
	
	public ApplyCdListedReq getBody() {
		return body;
	}

	public void setBody(ApplyCdListedReq body) {
		this.body = body;
	}

	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
