
package com.cnacex.eshop.msg.xml.mall;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.mall.ListedDetailReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
@XStreamAlias("cnacex:root")
public class ListedDetailReqMsg extends AbstractReqMsg<ListedDetailReq> {
	
	public static final String TX_CODE = "03101003";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected ListedDetailReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public ListedDetailReq getBody() {
		return body;
	}

	public void setBody(ListedDetailReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
