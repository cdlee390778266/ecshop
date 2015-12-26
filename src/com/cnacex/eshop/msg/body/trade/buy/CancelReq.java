package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class CancelReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("delistno")
	private String delistNo;
	

	@XStreamAlias("flag")
	private String flag;

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

	public String getDelistNo() {
		return delistNo;
	}

	public void setDelistNo(String delistNo) {
		this.delistNo = delistNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	
	


}
