package com.cnacex.eshop.msg.body.member;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author 孙超
 * 操作员资料查询请求报文类*/
public class InfoQueryReq {

	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getOperID() {
		return operID;
	}

	public void setOperID(String operID) {
		this.operID = operID;
	}
}
