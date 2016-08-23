package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单摘牌审核响应
 * @author frog
 *
 */
public class WRAuditRsp {

	/**
	 * 摘牌编号
	 */
	@XStreamAlias("delistno")
	private String delistNo;
	
	/**
	 * 摘牌状态
	 */
	@XStreamAlias("status")
	private int status;
	
	private String statusDesc;

	public String getDelistNo() {
		return delistNo;
	}

	public void setDelistNo(String delistNo) {
		this.delistNo = delistNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
}
