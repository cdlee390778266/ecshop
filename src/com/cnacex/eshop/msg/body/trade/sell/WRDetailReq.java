package com.cnacex.eshop.msg.body.trade.sell;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 1、协议：仓单详单查询（60003004）  
 * 2、类描述：仓单详单查询请求消息体
 */
public class WRDetailReq {
	//仓库编号
	@XStreamAlias("storeno")
	private String storeNo;
	
	//仓单编号
	@XStreamAlias("receiptno")
	private String receiptNo;

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
}
