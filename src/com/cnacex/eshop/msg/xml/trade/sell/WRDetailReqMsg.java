package com.cnacex.eshop.msg.xml.trade.sell;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.trade.sell.WRDetailReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单详情查询请求XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class WRDetailReqMsg extends AbstractReqMsg<WRDetailReq> {
	
	public static final String TX_CODE = "60003004";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected WRDetailReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}
	
	public WRDetailReq getBody() {
		return body;
	}

	public void setBody(WRDetailReq body) {
		this.body = body;
	}

	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
