package com.cnacex.eshop.msg.body.trade.sell;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 注册仓单清单表返回结构体
 * @author 文闻
 *
 */

public class Receipts {
	
	//仓库编号
	@XStreamAlias("storeno")
	private String storeno;
	
	//仓库名称
	@XStreamAlias("storename")
	private String storename;
	
	//仓单编号
	@XStreamAlias("registno")
	private String registno;
	
	//会员仓储账号
	@XStreamAlias("provtrdid")
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

	public String getRegistno() {
		return registno;
	}

	public void setRegistno(String registno) {
		this.registno = registno;
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

	@Override
	public String toString() {
		return "Receipts [storeno=" + storeno + ", storename=" + storename
				+ ", registno=" + registno + ", provmemid=" + provmemid
				+ ", commcode=" + commcode +", classcode=" + classcode 
				+ ", markcode=" + markcode+ ", mdsename=" + mdsename+ ", qty=" + qty 
				+ ", price=" + price+"]";
	}
}	
