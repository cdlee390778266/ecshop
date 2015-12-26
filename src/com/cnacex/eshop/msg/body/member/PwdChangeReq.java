package com.cnacex.eshop.msg.body.member;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Administrator
 *
 */
public class PwdChangeReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("oldpwd")
	private String oldPwd;
	
	@XStreamAlias("newpwd")
	private String newPwd;

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

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}	
}
