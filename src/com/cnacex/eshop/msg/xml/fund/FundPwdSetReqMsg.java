
package com.cnacex.eshop.msg.xml.fund;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.fund.FundPwdSetReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
@XStreamAlias("cnacex:root")
public class FundPwdSetReqMsg extends AbstractReqMsg<FundPwdSetReq> {
	
	public static final String TX_CODE = "08004002";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected FundPwdSetReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public FundPwdSetReq getBody() {
		return body;
	}

	public void setBody(FundPwdSetReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
