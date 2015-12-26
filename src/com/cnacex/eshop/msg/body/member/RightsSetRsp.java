package com.cnacex.eshop.msg.body.member;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class RightsSetRsp {
	
	@XStreamAlias("operID")
	private String	operID;

	public String getOperID() {
		return operID;
	}
	public void setOperID(String operID) {
		this.operID = operID;
	}
}
