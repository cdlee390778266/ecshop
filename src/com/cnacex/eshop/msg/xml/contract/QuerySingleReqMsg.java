
package com.cnacex.eshop.msg.xml.contract;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.contract.QuerySingleReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
@XStreamAlias("cnacex:root")
public class QuerySingleReqMsg extends AbstractReqMsg<QuerySingleReq> {
	
	public static final String TX_CODE = "05101082";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected QuerySingleReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public QuerySingleReq getBody() {
		return body;
	}

	public void setBody(QuerySingleReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
