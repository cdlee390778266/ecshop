package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.sell.SellOrderDetailReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class SellOrderDetailReqMsg extends AbstractReqMsg<SellOrderDetailReq> {
	
public static final String TX_CODE = "05101005";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected SellOrderDetailReq body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public SellOrderDetailReq getBody() {
		return body;
	}

	public void setBody(SellOrderDetailReq body) {
		this.body = body;
	}
	
	
	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
