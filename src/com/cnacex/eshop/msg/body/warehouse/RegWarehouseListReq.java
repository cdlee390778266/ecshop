package com.cnacex.eshop.msg.body.warehouse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 签发仓单注册请求结构体（交易码60005001）
 * @author 文闻
 *
 */
public class RegWarehouseListReq {

	//会员编号
	@XStreamAlias("mid")
	private String mid;
	
	//仓库编号
	@XStreamAlias("storeno")
	private String storeno;
	
	//仓单编号
	@XStreamAlias("receiptno")
	private String receiptno;
	
	//货物总量
	@XStreamAlias("qty")
	private String qty;
	
	//交易价格
	@XStreamAlias("price")
	private Double price;

	public String getStoreno() {
		return storeno;
	}

	public void setStoreno(String storeno) {
		this.storeno = storeno;
	}

	public String getReceiptno() {
		return receiptno;
	}

	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
}
