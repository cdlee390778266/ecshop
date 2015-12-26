
package com.cnacex.eshop.msg.xml.fund;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.fund.QueryTxSReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
@XStreamAlias("cnacex:root")
public class QueryTxSReqMsg extends AbstractReqMsg<QueryTxSReq> {
	
	public static final String TX_CODE = "08003001";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected QueryTxSReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public QueryTxSReq getBody() {
		return body;
	}

	public void setBody(QueryTxSReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
