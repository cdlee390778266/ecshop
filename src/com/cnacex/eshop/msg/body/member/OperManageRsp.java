package com.cnacex.eshop.msg.body.member;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OperManageRsp {
	
	@XStreamAlias("operid")
	private String operID;

	public String getOperID() {
		return operID;
	}

	public void setOperID(String operID) {
		this.operID = operID;
	}

	
	
}
