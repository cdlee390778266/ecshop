package com.cnacex.eshop.msg.body.member;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author 孙超
 *	操作员清单查询请求报文类
 */
public class InvQueryReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	
	@XStreamAlias("adminid")
	private String adminID;


	public String getmID() {
		return mID;
	}


	public void setmID(String mID) {
		this.mID = mID;
	}


	public String getAdminID() {
		return adminID;
	}


	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	
}
