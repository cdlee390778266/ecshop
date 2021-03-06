package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.sell.CancelReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单挂牌下架请求XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class CancelCdSellReqMsg extends AbstractReqMsg<CancelReq> {
	
	public static final String TX_CODE = "60005006";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected CancelReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public CancelReq getBody() {
		return body;
	}

	public void setBody(CancelReq body) {
		this.body = body;
	}


	
	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
