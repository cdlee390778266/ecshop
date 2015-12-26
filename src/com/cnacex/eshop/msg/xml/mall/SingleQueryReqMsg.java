
package com.cnacex.eshop.msg.xml.mall;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.mall.SingleQueryReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
//@XStreamAlias("cnacex:root")
public class SingleQueryReqMsg extends AbstractReqMsg<SingleQueryReq> {
	
	
	public static final String TX_CODE = "03101001";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected SingleQueryReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public SingleQueryReq getBody() {
		return body;
	}

	public void setBody(SingleQueryReq body) {
		this.body = body;
	}

	public String getTxCode(){
		return TX_CODE;
	}

	
}
