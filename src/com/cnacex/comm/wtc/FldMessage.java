package com.cnacex.comm.wtc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("XML")
public class FldMessage {
	
	@XStreamAlias("PHF_VER")
	private int ver;
	
	@XStreamAlias("PHF_REQSYS")
	private String reqsys;
	
	@XStreamAlias("PHF_REQNODE")
	private String reqnode;
	
	@XStreamAlias("PHF_TXCODE")
	private String txcode;
	
	@XStreamAlias("PHF_REQNO")
	private int reqno;
	
	@XStreamAlias("PHF_ENCODE")
	private String encode;
	
	@XStreamAlias("PHF_DYNAUTH")
	private String dynauth;
	
	@XStreamAlias("PHF_MAC")
	private int mac;
	
	@XStreamAlias("PBF_DATA")
	private String data;
	
	@XStreamAlias("PHF_RSPSYS")
	private String rspsys;
	
	@XStreamAlias("PHF_RSPNODE")
	private String rspnode;
	
	@XStreamAlias("PHF_RSPNO")
	private int rspno;
	
	@XStreamAlias("PHF_RSPSTAT")
	private String rspstat;

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

	public String getReqsys() {
		return reqsys;
	}

	public void setReqsys(String reqsys) {
		this.reqsys = reqsys;
	}

	public String getReqnode() {
		return reqnode;
	}

	public void setReqnode(String reqnode) {
		this.reqnode = reqnode;
	}

	public String getTxcode() {
		return txcode;
	}

	public void setTxcode(String txcode) {
		this.txcode = txcode;
	}

	public int getReqno() {
		return reqno;
	}

	public void setReqno(int reqno) {
		this.reqno = reqno;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getDynauth() {
		return dynauth;
	}

	public void setDynauth(String dynauth) {
		this.dynauth = dynauth;
	}

	public int getMac() {
		return mac;
	}

	public void setMac(int mac) {
		this.mac = mac;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getRspsys() {
		return rspsys;
	}

	public void setRspsys(String rspsys) {
		this.rspsys = rspsys;
	}



	@Override
	public String toString() {
		return "FldMessage [ver=" + ver + ", reqsys=" + reqsys + ", reqnode="
				+ reqnode + ", txcode=" + txcode + ", reqno=" + reqno
				+ ", encode=" + encode + ", dynauth=" + dynauth + ", mac="
				+ mac + ", data=" + data + ", rspsys=" + rspsys + ", rspnode="
				+ rspnode + ", rspno=" + rspno + ", rspstat=" + rspstat + "]";
	}

	public String getRspnode() {
		return rspnode;
	}

	public void setRspnode(String rspnode) {
		this.rspnode = rspnode;
	}

	public int getRspno() {
		return rspno;
	}

	public void setRspno(int rspno) {
		this.rspno = rspno;
	}

	public String getRspstat() {
		return rspstat;
	}

	public void setRspstat(String rspstat) {
		this.rspstat = rspstat;
	}



	
	

}
