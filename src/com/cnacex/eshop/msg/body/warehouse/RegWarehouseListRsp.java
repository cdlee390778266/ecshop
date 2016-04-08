package com.cnacex.eshop.msg.body.warehouse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 签发仓单注册应答结构体（交易码60005001）
 * @author 文闻
 *
 */
public class RegWarehouseListRsp {

	
	//注册仓单编号
	@XStreamAlias("registno")
	private String registno;
	
	//审核编号
	@XStreamAlias("auditno")
	private Long auditno;

	public String getRegistno() {
		return registno;
	}

	public void setRegistno(String registno) {
		this.registno = registno;
	}

	public Long getAuditno() {
		return auditno;
	}

	public void setAuditno(Long auditno) {
		this.auditno = auditno;
	}
	
}
