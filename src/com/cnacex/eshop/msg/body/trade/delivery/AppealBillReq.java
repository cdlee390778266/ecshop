package com.cnacex.eshop.msg.body.trade.delivery;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class AppealBillReq {
	
	@XStreamAlias("compno")
	private String compNo;

	public String getCompNo() {
		return compNo;
	}

	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	
	
	
	

}
