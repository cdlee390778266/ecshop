package com.cnacex.eshop.msg.body.trade.sell;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class StoreReq {
	
	@XStreamAlias("reqstart")
	private String reqStart;
	
	
	@XStreamAlias("reqnum")
	private String reqNum;
	
	@XStreamAlias("storeno")
	private String storeNo;
	
	@XStreamAlias("storename")
	private String storeName;
	
	@XStreamAlias("storetype")
	private String storeType;
	
	@XStreamAlias("storemid")
	private String storeMID;
	
	@XStreamAlias("commcode")
	private String commCode;
	
	@XStreamAlias("commname")
	private String commName;
	
	@XStreamAlias("status")
	private String status;

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

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getStoreMID() {
		return storeMID;
	}

	public void setStoreMID(String storeMID) {
		this.storeMID = storeMID;
	}

	public String getCommCode() {
		return commCode;
	}

	public void setCommCode(String commCode) {
		this.commCode = commCode;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	


}
