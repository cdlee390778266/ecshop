
package com.cnacex.eshop.msg.xml.query;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.query.HisQueryReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
@XStreamAlias("cnacex:root")
public class SellHisQueryReqMsg extends AbstractReqMsg<HisQueryReq> {
	
	public static final String TX_CODE = "05101061";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected HisQueryReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public HisQueryReq getBody() {
		return body;
	}

	public void setBody(HisQueryReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
