
package com.cnacex.eshop.msg.body.mall;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */
public class SingleQueryReq {
	
	@XStreamAlias("reqcode")
	private String reqCode;
	
	@XStreamAlias("reqtype")
	private String reqType;
	
	@XStreamAlias("listedmid")
	private String listedMID;
	
	@XStreamAlias("reqstatus")
	private String reqStatus;
	
	@XStreamAlias("reqeffrec")
	private String reqEffRec;
	
	@XStreamAlias("reqstart")
	private String reqStart;
	
	@XStreamAlias("reqnum")
	private int	reqNum;
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("doe")
	private String doe;
	
	
	@XStreamAlias("dol")
	private String dol;
	
	@XStreamAlias("edoe")
	private String edoe;
	
	@XStreamAlias("edol")
	private String edol;

	public String getReqCode() {
		return reqCode;
	}

	
	public String getEdoe() {
		return edoe;
	}


	public void setEdoe(String edoe) {
		this.edoe = edoe;
	}


	public String getEdol() {
		return edol;
	}


	public void setEdol(String edol) {
		this.edol = edol;
	}


	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getListedMID() {
		return listedMID;
	}

	public void setListedMID(String listedMID) {
		this.listedMID = listedMID;
	}

	public String getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	
	

	public String getReqEffRec() {
		return reqEffRec;
	}

	public void setReqEffRec(String reqEffRec) {
		this.reqEffRec = reqEffRec;
	}

	public String getReqStart() {
		return reqStart;
	}

	public void setReqStart(String reqStart) {
		this.reqStart = reqStart;
	}

	public int getReqNum() {
		return reqNum;
	}

	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
	}


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

	public String getDoe() {
		return doe;
	}

	public void setDoe(String doe) {
		this.doe = doe;
	}

	public String getDol() {
		return dol;
	}

	public void setDol(String dol) {
		this.dol = dol;
	}

	

}
