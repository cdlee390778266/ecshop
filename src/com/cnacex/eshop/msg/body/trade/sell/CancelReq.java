package com.cnacex.eshop.msg.body.trade.sell;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class CancelReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("txoperid")
	private String txOperID;
	
	@XStreamAlias("listedno")
	private String listedNo;

	@XStreamAlias("flag")
	private String flag;
	
	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getTxOperID() {
		return txOperID;
	}

	public void setTxOperID(String txOperID) {
		this.txOperID = txOperID;
	}

	public String getListedNo() {
		return listedNo;
	}

	public void setListedNo(String listedNo) {
		this.listedNo = listedNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	


}
