package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.ROutWarehouseReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 注册仓单撤销请求XML（交易码60005014）XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class ROutWarehouseReqMsg extends AbstractReqMsg<ROutWarehouseReq> {
	
	public static final String TX_CODE = "60005008";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected ROutWarehouseReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}
	
	public ROutWarehouseReq getBody() {
		return body;
	}

	public void setBody(ROutWarehouseReq body) {
		this.body = body;
	}

	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
