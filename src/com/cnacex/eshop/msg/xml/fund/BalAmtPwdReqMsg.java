
package com.cnacex.eshop.msg.xml.fund;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.fund.BalAmtPwdReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
@XStreamAlias("cnacex:root")
public class BalAmtPwdReqMsg extends AbstractReqMsg<BalAmtPwdReq> {
	
	public static final String TX_CODE = "08001001";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected BalAmtPwdReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BalAmtPwdReq getBody() {
		return body;
	}

	public void setBody(BalAmtPwdReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
