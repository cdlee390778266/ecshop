
package com.cnacex.eshop.msg.body.trade.sell;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
public class SellBillReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("status")
	private String status;
	
	@XStreamAlias("reqstart")
	private String reqStart;
	
	@XStreamAlias("reqnum")
	private String reqNum;
	
	@XStreamAlias("commcode")
	private String commCode;

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



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReqStart() {
		return reqStart;
	}

	public void setReqStart(String reqStart) {
		this.reqStart = reqStart;
	}

	public String getReqNum() {
		return reqNum;
	}

	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}

	public String getCommCode() {
		return commCode;
	}

	public void setCommCode(String commCode) {
		this.commCode = commCode;
	}

	@Override
	public String toString() {
		return "SellBillReq [mID=" + mID + ", operID=" + operID + ", status="
				+ status + ", reqStart=" + reqStart + ", reqNum=" + reqNum
				+ ", commCode=" + commCode + "]";
	}



	
	


}
