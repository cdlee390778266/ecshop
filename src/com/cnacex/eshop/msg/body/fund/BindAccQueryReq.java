package com.cnacex.eshop.msg.body.fund;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class BindAccQueryReq {
	
	
	@XStreamAlias("mid")
	private String mID;

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}
	
	

}
