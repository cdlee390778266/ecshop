package com.cnacex.eshop.msg.body.warehouse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 注册仓单转出请求结构体（交易码60005008）
 * @author 文闻
 *
 */
public class ROutWarehouseReq {
	
	//注册仓单编号
	@XStreamAlias("registno")
	private String registno;

	public String getRegistno() {
		return registno;
	}

	public void setRegistno(String registno) {
		this.registno = registno;
	}
	
}
