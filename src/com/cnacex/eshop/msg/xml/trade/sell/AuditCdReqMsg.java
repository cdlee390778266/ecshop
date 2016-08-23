package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;

import com.cnacex.eshop.msg.body.trade.sell.AuditReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 注册仓单审核请求XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class AuditCdReqMsg extends AbstractReqMsg<AuditReq> {
	
	public static final String TX_CODE = "60005003";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected AuditReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public AuditReq getBody() {
		return body;
	}

	public void setBody(AuditReq body) {
		this.body = body;
	}

	public String getTxCode(){
		return TX_CODE;
	}
	

}
