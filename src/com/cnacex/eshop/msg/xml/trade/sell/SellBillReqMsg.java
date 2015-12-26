package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.sell.SellBillReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class SellBillReqMsg extends AbstractReqMsg<SellBillReq> {
	
	public static final String TX_CODE = "05101004";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected SellBillReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public SellBillReq getBody() {
		return body;
	}

	public void setBody(SellBillReq body) {
		this.body = body;
	}
	
	
	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
