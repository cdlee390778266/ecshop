package com.cnacex.eshop.msg.body.trade.sell;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 注册仓单挂牌请求应答结构体
 * @author 文闻
 *
 */

public class ApplyCdListedRsp {
	
	//挂牌编号
	@XStreamAlias("listedno")
	private String listedno;
	
	//挂牌状态
	@XStreamAlias("status")
	private Long status;
	
	private String statusDesc;

	public String getListedno() {
		return listedno;
	}

	public void setListedno(String listedno) {
		this.listedno = listedno;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
}
