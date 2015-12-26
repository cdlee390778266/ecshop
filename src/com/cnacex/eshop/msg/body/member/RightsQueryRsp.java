package com.cnacex.eshop.msg.body.member;

import java.util.List;


import com.cnacex.eshop.msg.body.comm.OperRight;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class RightsQueryRsp {
	
	@XStreamImplicit
	private List<OperRight> operRights;

	public List<OperRight> getOperRights() {
		return operRights;
	}

	public void setOperRights(List<OperRight> operRights) {
		this.operRights = operRights;
	}
}
