package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.sell.StoreReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;


public class StoreReqMsg extends AbstractReqMsg<StoreReq> {
	
	public static final String TX_CODE = "05001001";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected StoreReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public StoreReq getBody() {
		return body;
	}

	public void setBody(StoreReq body) {
		this.body = body;
	}


	
	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
