
package com.cnacex.eshop.msg.xml.contract;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.contract.QueryListReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
@XStreamAlias("cnacex:root")
public class QueryListReqMsg extends AbstractReqMsg<QueryListReq> {
	
	public static final String TX_CODE = "05101081";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected QueryListReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public QueryListReq getBody() {
		return body;
	}

	public void setBody(QueryListReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
