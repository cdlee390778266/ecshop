
package com.cnacex.eshop.msg.body.mall;

import java.util.List;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */
public class MoreQueryReq {
	

	@XStreamImplicit
	private List<ReqComm> reqComms;
	

	@XStreamAlias("reqcomms")
	public static class ReqComm{
		
		@XStreamAlias("reqcode")
		private String reqCode;
		
		@XStreamAlias("reqtype")
		private String reqType;

		public String getReqCode() {
			return reqCode;
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
	}
	
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

	@XStreamAlias("doe")
	private String doe;
	
	@XStreamAlias("dol")
	private String dol;
	
	@XStreamAlias("edoe")
	private String edoe;
	
	@XStreamAlias("edol")
	private String edol;

	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("operid")
	private String operID;
	

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

	public List<ReqComm> getReqComms() {
		return reqComms;
	}

	public void setReqComms(List<ReqComm> reqComms) {
		this.reqComms = reqComms;
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
