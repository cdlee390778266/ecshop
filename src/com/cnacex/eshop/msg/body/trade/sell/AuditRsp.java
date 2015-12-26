package com.cnacex.eshop.msg.body.trade.sell;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
public class AuditRsp {
	
	
	@XStreamAlias("listedno")
	private String listedNO;
	
	
	@XStreamAlias("status")
	private int	status;

	
	private String statusDesc;

	public String getListedNO() {
		return listedNO;
	}


	public void setListedNO(String listedNO) {
		this.listedNO = listedNO;
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
