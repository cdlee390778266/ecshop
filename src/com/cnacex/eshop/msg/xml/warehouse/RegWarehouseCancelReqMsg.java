package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseCancelReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 待审核注册仓单请求XML（交易码60005012）XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class RegWarehouseCancelReqMsg extends AbstractReqMsg<RegWarehouseCancelReq> {
	
	public static final String TX_CODE = "60005012";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected RegWarehouseCancelReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}
	
	public RegWarehouseCancelReq getBody() {
		return body;
	}

	public void setBody(RegWarehouseCancelReq body) {
		this.body = body;
	}

	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
