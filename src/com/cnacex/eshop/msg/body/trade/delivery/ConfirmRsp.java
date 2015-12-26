package com.cnacex.eshop.msg.body.trade.delivery;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ConfirmRsp {
	

	@XStreamAlias("strikeno")
	private String strikeNo;
	
	@XStreamAlias("status")
	private int status;
	
	
	private String statusDesc;


	public String getStrikeNo() {
		return strikeNo;
	}


	public void setStrikeNo(String strikeNo) {
		this.strikeNo = strikeNo;
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
