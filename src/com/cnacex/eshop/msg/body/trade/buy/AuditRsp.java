package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
public class AuditRsp {
	
	
	@XStreamAlias("delistno")
	private String delistNo;
	
	
	@XStreamAlias("status")
	private int	status;
	
	
	private String statusDesc;
	
	
	public String getDelistNo() {
		return delistNo;
	}


	public void setDelistNo(String delistNo) {
		this.delistNo = delistNo;
	}


	public String getStatusDesc() {
		return statusDesc;
	}


	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
}
