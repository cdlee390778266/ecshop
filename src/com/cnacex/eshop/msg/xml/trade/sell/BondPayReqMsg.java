package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.sell.BondPayReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("cnacex:root")
public class BondPayReqMsg extends AbstractReqMsg<BondPayReq> {
	
	public static final String TX_CODE = "05101003";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected BondPayReq body;
	

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BondPayReq getBody() {
		return body;
	}

	public void setBody(BondPayReq body) {
		this.body = body;
	}

	public String getTxCode(){
		return TX_CODE;
	}

}
