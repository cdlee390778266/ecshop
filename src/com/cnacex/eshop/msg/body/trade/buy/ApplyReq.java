package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */

public class ApplyReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("txoperid")
	private String txOperID;
	
	@XStreamAlias("listedno")
	private String listedNO;
	
	@XStreamAlias("vol")
	private int vol;
	
	@XStreamAlias("top")
	private String	top;
	
	@XStreamAlias("invoice")
	private String	invoice;
	
	@XStreamAlias("remark")
	private String 	remark;

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

	public String getListedNO() {
		return listedNO;
	}

	public void setListedNO(String listedNO) {
		this.listedNO = listedNO;
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

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "ApplyReq [mID=" + mID + ", txOperID=" + txOperID
				+ ", listedNO=" + listedNO + ", Vol=" + vol + ", top=" + top
				+ ", invoice=" + invoice + ", remark=" + remark + "]";
	}

	
	

}
