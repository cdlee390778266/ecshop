
package com.cnacex.eshop.msg.xml.fund;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.fund.BalAmtReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
@XStreamAlias("cnacex:root")
public class BalAmtReqMsg extends AbstractReqMsg<BalAmtReq> {
	
	public static final String TX_CODE = "08001005";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected BalAmtReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BalAmtReq getBody() {
		return body;
	}

	public void setBody(BalAmtReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
