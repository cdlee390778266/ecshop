package com.cnacex.eshop.msg.body.trade.delivery;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单交易投诉请求
 * @author frog
 *
 */
public class WRAppealReq {

	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("strikeno")
	private String StrikeNo;
	
	@XStreamAlias("trdstatus")
	private String trdStatus;
	
	@XStreamAlias("comprlt")
	private String reason;

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

	public String getStrikeNo() {
		return StrikeNo;
	}

	public void setStrikeNo(String strikeNo) {
		StrikeNo = strikeNo;
	}

	public String getTrdStatus() {
		return trdStatus;
	}

	public void setTrdStatus(String trdStatus) {
		this.trdStatus = trdStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
