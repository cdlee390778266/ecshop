
package com.cnacex.eshop.msg.xml.mall;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.mall.MdseStaticReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
@XStreamAlias("cnacex:root")
public class MdseStaticReqMsg extends AbstractReqMsg<MdseStaticReq> {
	
	public static final String TX_CODE = "03001001";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected MdseStaticReq body;


	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public MdseStaticReq getBody() {
		return body;
	}

	public void setBody(MdseStaticReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}

}
