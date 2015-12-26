package com.cnacex.eshop.msg.body.fund;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
public class FundPwdSetReq {

	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("oldfundpwd")
	private String oldFundPwd;
	
	@XStreamAlias("newfundpwd")
	private String newFundPwd;

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

	public String getOldFundPwd() {
		return oldFundPwd;
	}

	public void setOldFundPwd(String oldFundPwd) {
		this.oldFundPwd = oldFundPwd;
	}

	public String getNewFundPwd() {
		return newFundPwd;
	}

	public void setNewFundPwd(String newFundPwd) {
		this.newFundPwd = newFundPwd;
	}

	
	

}
