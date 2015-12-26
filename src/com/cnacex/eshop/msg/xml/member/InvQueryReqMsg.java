
package com.cnacex.eshop.msg.xml.member;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.member.InvQueryReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * @author 孙超
 *操作员清单查询请求报文信息类
 */
@XStreamAlias("cnacex:root")
public class InvQueryReqMsg extends AbstractReqMsg<InvQueryReq>{
	public static final String TX_CODE = "01000008";
	

	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected InvQueryReq body;
	

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public InvQueryReq getBody() {
		return body;
	}

	public void setBody(InvQueryReq body) {
		this.body = body;
	}
	
	public String getTxCode(){
		return TX_CODE;
	}
}
