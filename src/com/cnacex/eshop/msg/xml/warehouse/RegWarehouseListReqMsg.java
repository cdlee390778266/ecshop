package com.cnacex.eshop.msg.xml.warehouse;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseListReq;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 签发仓单注册请求XML（交易码60005001）XML
 * @author 文闻
 *
 */
@XStreamAlias("cnacex:root")
public class RegWarehouseListReqMsg extends AbstractReqMsg<RegWarehouseListReq> {
	
	public static final String TX_CODE = "60005001";
	
	@XStreamAlias("head")
	protected Head head;

	@XStreamAlias("body")
	protected RegWarehouseListReq body;
	
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}
	
	public RegWarehouseListReq getBody() {
		return body;
	}

	public void setBody(RegWarehouseListReq body) {
		this.body = body;
	}

	public String getTxCode(){
		return TX_CODE;
	}
	
	

}
