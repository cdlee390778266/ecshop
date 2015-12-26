package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class EditReq {
	
	@XStreamAlias("delistno")
	private String delistNo;
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("txoperid")
	private String txOperID;
	
	@XStreamAlias("listedno")
	private String listedNo;
	
	@XStreamAlias("vol")
	private int vol;
	
	@XStreamAlias("top")
	private String top;
	
	@XStreamAlias("remark")
	private String remark;

	public String getDelistNo() {
		return delistNo;
	}

	public void setDelistNo(String delistNo) {
		this.delistNo = delistNo;
	}

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

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



}
