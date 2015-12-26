package com.cnacex.eshop.msg.body.mall;

import com.thoughtworks.xstream.annotations.XStreamAlias;



/**
 * @author kereny
 *
 */
public class ListedDetailReq {

	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("listedno")
	private String listedNo;

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

	public String getListedNo() {
		return listedNo;
	}

	public void setListedNo(String listedNo) {
		this.listedNo = listedNo;
	}
	
}
