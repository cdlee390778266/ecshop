
package com.cnacex.eshop.msg.xml.fund;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.fund.QueryAccSReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
@XStreamAlias("cnacex:root")
public class QueryAccSReqMsg extends AbstractReqMsg<QueryAccSReq> {
	
	public static final String TX_CODE = "08003002";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected QueryAccSReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public QueryAccSReq getBody() {
		return body;
	}

	public void setBody(QueryAccSReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
