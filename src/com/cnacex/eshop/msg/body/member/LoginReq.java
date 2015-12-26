package com.cnacex.eshop.msg.body.member;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
public class LoginReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	
	@XStreamAlias("operid")
	private String operID;
	
	
	@XStreamAlias("operpwd")
	private String operPwd;


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


	public String getOperPwd() {
		return operPwd;
	}


	public void setOperPwd(String operPwd) {
		this.operPwd = operPwd;
	}


	@Override
	public String toString() {
		return "LoginReq [mID=" + mID + ", operID=" + operID + ", operPwd="
				+ operPwd + "]";
	}



}
