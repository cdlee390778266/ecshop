package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 商品摘牌申请请求
 * @author kereny
 *
 */

public class ApplyReq {
	
	/**
	 * 会员编号
	 */
	@XStreamAlias("mid")
	private String mID;
	
	/**
	 * 摘牌交易员 摘牌交易员（操作员）编号
	 */
	@XStreamAlias("txoperid")
	private String txOperID;
	
	/**
	 * 所摘挂牌编号
	 */
	@XStreamAlias("listedno")
	private String listedNO;
	
	/**
	 * 购买数量
	 */
	@XStreamAlias("vol")
	private int vol;
	
	/**
	 * 付款方式
	 */
	@XStreamAlias("top")
	private String	top;
	
	/**
	 * 
	 */
	@XStreamAlias("invoice")
	private String	invoice;
	
	/**
	 * 买方备注
	 */
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
