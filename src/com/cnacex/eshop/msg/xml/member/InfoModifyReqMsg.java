package com.cnacex.eshop.msg.xml.member;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.InfoModifyReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * @author 孙超
 *操作员资料变更请求报文信息类
 */
@XStreamAlias("cnacex:root")
public class InfoModifyReqMsg extends AbstractReqMsg<InfoModifyReq>{
	public static final String TX_CODE = "01000003";

	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected InfoModifyReq body;
	

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public InfoModifyReq getBody() {
		return body;
	}

	public void setBody(InfoModifyReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}
}
