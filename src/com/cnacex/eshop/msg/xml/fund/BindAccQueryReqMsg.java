
package com.cnacex.eshop.msg.xml.fund;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.fund.BindAccQueryReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
@XStreamAlias("cnacex:root")
public class BindAccQueryReqMsg extends AbstractReqMsg<BindAccQueryReq> {
	
	public static final String TX_CODE = "08004001";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected BindAccQueryReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BindAccQueryReq getBody() {
		return body;
	}

	public void setBody(BindAccQueryReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
