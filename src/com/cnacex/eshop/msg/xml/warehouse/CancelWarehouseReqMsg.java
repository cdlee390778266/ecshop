package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.CancelWarehouseReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 注册仓单撤销请求XML（交易码60005014）XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class CancelWarehouseReqMsg extends AbstractReqMsg<CancelWarehouseReq> {
	
	public static final String TX_CODE = "60005014";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected CancelWarehouseReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}
	
	public CancelWarehouseReq getBody() {
		return body;
	}

	public void setBody(CancelWarehouseReq body) {
		this.body = body;
	}

	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
