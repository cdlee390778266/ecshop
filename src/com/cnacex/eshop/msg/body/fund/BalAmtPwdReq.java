package com.cnacex.eshop.msg.body.fund;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */

public class BalAmtPwdReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	
	@XStreamAlias("fundpwd")
	private String fundPwd;


	public String getmID() {
		return mID;
	}


	public void setmID(String mID) {
		this.mID = mID;
	}


	public String getFundPwd() {
		return fundPwd;
	}


	public void setFundPwd(String fundPwd) {
		this.fundPwd = fundPwd;
	}


	
	
}
