
package com.cnacex.eshop.msg.xml.mall;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.mall.MoreQueryReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
//@XStreamAlias("cnacex:root")
public class MoreQueryReqMsg extends AbstractReqMsg<MoreQueryReq> {
	
	public static final String TX_CODE = "03101002";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected MoreQueryReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public MoreQueryReq getBody() {
		return body;
	}

	public void setBody(MoreQueryReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
