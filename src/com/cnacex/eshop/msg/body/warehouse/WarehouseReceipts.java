package com.cnacex.eshop.msg.body.warehouse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 签发仓单清单表返回结构体
 * @author 文闻
 *
 */

public class WarehouseReceipts {
	
	//仓库编号
	@XStreamAlias("storeno")
	private String storeno;
	
	//仓库名称
	@XStreamAlias("storename")
	private String storename;
	
	//仓单编号
	@XStreamAlias("receiptno")
	private String receiptno;
	
	//会员仓储账号
	@XStreamAlias("provmemid")
	private String provmemid;
	
	//商品代码
	@XStreamAlias("commcode")
	private String commcode;
		
	//品类代码
	@XStreamAlias("classcode")
	private String classcode;
	
	//市场代码
	@XStreamAlias("markcode")
	private String markcode;
	
	//商品种类名称
	@XStreamAlias("mdsename")
	private String mdsename;
	
	//货物数量
	@XStreamAlias("qty")
	private Long qty;
	
	//剩余数量
	@XStreamAlias("rem")
	private Long rem;
	
	//交易价格
	@XStreamAlias("price")
	private String price;
	
	//仓单状态
	@XStreamAlias("status")
	private Long status;
	
	//会员编号
	private String provid;
	
	//供货商名称
	private String memname;
	
	public String getStoreno() {
		return storeno;
	}

	public void setStoreno(String storeno) {
		this.storeno = storeno;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}


	public String getReceiptno() {
		return receiptno;
	}

	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}

	public String getProvmemid() {
		return provmemid;
	}

	public void setProvmemid(String provmemid) {
		this.provmemid = provmemid;
	}

	public String getCommcode() {
		return commcode;
	}



	public void setCommcode(String commcode) {
		this.commcode = commcode;
	}



	public String getClasscode() {
		return classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}

	public String getMarkcode() {
		return markcode;
	}

	public void setMarkcode(String markcode) {
		this.markcode = markcode;
	}

	public String getMdsename() {
		return mdsename;
	}

	public void setMdsename(String mdsename) {
		this.mdsename = mdsename;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getProvid() {
		return provid;
	}

	public void setProvid(String provid) {
		this.provid = provid;
	}

	public String getMemname() {
		return memname;
	}

	public void setMemname(String memname) {
		this.memname = memname;
	}

	public Long getRem() {
		return rem;
	}

	public void setRem(Long rem) {
		this.rem = rem;
	}

	@Override
	public String toString() {
		return "WarehouseReceipts [storeno=" + storeno + ", storename=" + storename
				+ ", receiptno=" + receiptno + ", provmemid=" + provmemid
				+ ", commcode=" + commcode +", classcode=" + classcode 
				+ ", markcode=" + markcode+ ", mdsename=" + mdsename+ ", qty=" + qty 
				+ ", price=" + price+"]";
	}
}	
