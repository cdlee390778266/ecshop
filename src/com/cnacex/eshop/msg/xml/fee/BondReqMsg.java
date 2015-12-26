
package com.cnacex.eshop.msg.xml.fee;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.fee.FeeReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */

public class BondReqMsg extends AbstractReqMsg<FeeReq> {
	
	public static final String TX_CODE = "07001001";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected FeeReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public FeeReq getBody() {
		return body;
	}

	public void setBody(FeeReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
