package com.cnacex.eshop.msg.body.warehouse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 待审核的注册仓单撤销结构体（交易码60005014）
 * @author 文闻
 *
 */
public class CancelWarehouseReq {
	
	//审核编码
	@XStreamAlias("auditno")
	private Long auditno;

	public Long getAuditno() {
		return auditno;
	}

	public void setAuditno(Long auditno) {
		this.auditno = auditno;
	}
	
}
