package com.cnacex.eshop.msg.body.warehouse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 注册仓单转出应答结构体（交易码60005008）
 * @author 文闻
 *
 */
public class ROutWarehouseRsp {
	
	//签发仓单编号
	@XStreamAlias("receiptno")
	private String receiptno;

	public String getReceiptno() {
		return receiptno;
	}

	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}	
}
