package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
public class BuyOrderDetailReq {
	
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;
	
	
	@XStreamAlias("delistno")
	private String deListNo;


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


	public String getDeListNo() {
		return deListNo;
	}


	public void setDeListNo(String deListNo) {
		this.deListNo = deListNo;
	}


	
	
}
