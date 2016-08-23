package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.sell.ApplyReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单修改请求XML
 * @author Administrator
 *
 */
@XStreamAlias("cnacex:root")
public class ApplyEditCdReqMsg extends AbstractReqMsg<ApplyReq> {
	
	public static final String TX_CODE = "60005013";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected ApplyReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public ApplyReq getBody() {
		return body;
	}

	public void setBody(ApplyReq body) {
		this.body = body;
	}


	
	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
