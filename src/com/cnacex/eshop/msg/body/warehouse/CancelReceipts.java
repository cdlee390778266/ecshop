package com.cnacex.eshop.msg.body.warehouse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 待审核注册仓单清单表返回结构体
 * @author 文闻
 *
 */

public class CancelReceipts {
	
	//审核编号
	@XStreamAlias("auditno")
	private Long auditno;
	
	//仓库编号
	@XStreamAlias("storeno")
	private String storeno;
	
	//仓库名称
	@XStreamAlias("storename")
	private String storename;
	
	//仓单编号
	@XStreamAlias("registno")
	private String registno;
	
	//仓位编号
	@XStreamAlias("position")
	private String position;
	
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
	
	
	//会员编号
	private String mid;
	
	//会员名称
	private String memname;

	public Long getAuditno() {
		return auditno;
	}

	public void setAuditno(Long auditno) {
		this.auditno = auditno;
	}

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMemname() {
		return memname;
	}

	public void setMemname(String memname) {
		this.memname = memname;
	}
}	
