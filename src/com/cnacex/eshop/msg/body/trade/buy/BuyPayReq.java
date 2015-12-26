package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class BuyPayReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("pyoperid")
	private String pyOperID;
	
	@XStreamAlias("fundpwd")
	private String fundPwd;
	
	@XStreamAlias("dsno")
	private String dsNo;

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getPyOperID() {
		return pyOperID;
	}

	public void setPyOperID(String pyOperID) {
		this.pyOperID = pyOperID;
	}

	public String getFundPwd() {
		return fundPwd;
	}

	public void setFundPwd(String fundPwd) {
		this.fundPwd = fundPwd;
	}

	public String getDsNo() {
		return dsNo;
	}

	public void setDsNo(String dsNo) {
		this.dsNo = dsNo;
	}
	
	



}
