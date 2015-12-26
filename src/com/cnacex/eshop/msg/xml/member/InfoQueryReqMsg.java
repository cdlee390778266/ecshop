package com.cnacex.eshop.msg.xml.member;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.InfoQueryReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * @author 孙超
 *操作员资料查询请求报文信息类
 */
@XStreamAlias("cnacex:root")
public class InfoQueryReqMsg extends AbstractReqMsg<InfoQueryReq>{
	public static final String TX_CODE = "01000002";

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected InfoQueryReq body;
	

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public InfoQueryReq getBody() {
		return body;
	}

	public void setBody(InfoQueryReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}
}
