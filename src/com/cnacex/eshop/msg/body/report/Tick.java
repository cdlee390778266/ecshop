package com.cnacex.eshop.msg.body.report;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Tick {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("memname")
	private String memName;
	
	@XStreamAlias("date")
	private String date;
	
	@XStreamAlias("strikeno")
	private String strikeNo;
	
	@XStreamAlias("dod")
	private String dod;
	
	@XStreamAlias("contno")
	private String contNo;
	
	@XStreamAlias("commname")
	private String commName;
	
	@XStreamAlias("bsdirect")
	private String bsDirect;
	
	private String bsDesc;
	
	@XStreamAlias("vol")
	private long vol;
	
	@XStreamAlias("surevol")
	private long sureVol;
	
	@XStreamAlias("pog")
	private double pog;
	
	private double inPog;
	
	private double outPog;
	
	@XStreamAlias("up")
	private double up;
	
	@XStreamAlias("invoice")
	private String invoice;
	
	@XStreamAlias("invamt")
	private double invAmt;
	
	@XStreamAlias("invmargin")
	private double invMargin;
	
	@XStreamAlias("status")
	private String status;
	
	private String statusDesc;
	
	@XStreamAlias("listedtype")
	private String listedType;
	

	@XStreamAlias("contamt")
	private double contAmt;
	
	@XStreamAlias("trdcharge")
	private double trdCharge;
	
	@XStreamAlias("trdmargin")
	private double trdMargin;
	
	@XStreamAlias("commbrand")
	private String commBrand;
	
	@XStreamAlias("divname")
	private String divName;
	
	@XStreamAlias("store")
	private String store;
	

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStrikeNo() {
		return strikeNo;
	}

	public void setStrikeNo(String strikeNo) {
		this.strikeNo = strikeNo;
	}

	public String getDod() {
		return dod;
	}

	public void setDod(String dod) {
		this.dod = dod;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	public String getBsDirect() {
		return bsDirect;
	}

	public void setBsDirect(String bsDirect) {
		this.bsDirect = bsDirect;
	}

	public String getBsDesc() {
		return bsDesc;
	}

	public void setBsDesc(String bsDesc) {
		this.bsDesc = bsDesc;
	}



	public double getUp() {
		return up;
	}

	public void setUp(double up) {
		this.up = up;
	}

	public double getContAmt() {
		return contAmt;
	}

	public void setContAmt(double contAmt) {
		this.contAmt = contAmt;
	}

	public double getTrdCharge() {
		return trdCharge;
	}

	public void setTrdCharge(double trdCharge) {
		this.trdCharge = trdCharge;
	}

	public double getTrdMargin() {
		return trdMargin;
	}

	public void setTrdMargin(double trdMargin) {
		this.trdMargin = trdMargin;
	}

	public String getCommBrand() {
		return commBrand;
	}

	public void setCommBrand(String commBrand) {
		this.commBrand = commBrand;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}


	public long getVol() {
		return vol;
	}

	public void setVol(long vol) {
		this.vol = vol;
	}

	public long getSureVol() {
		return sureVol;
	}

	public void setSureVol(long sureVol) {
		this.sureVol = sureVol;
	}

	public double getPog() {
		return pog;
	}

	public void setPog(double pog) {
		this.pog = pog;
	}

	public double getInPog() {
		return inPog;
	}

	public void setInPog(double inPog) {
		this.inPog = inPog;
	}

	public double getOutPog() {
		return outPog;
	}

	public void setOutPog(double outPog) {
		this.outPog = outPog;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}


	public double getInvAmt() {
		return invAmt;
	}

	public void setInvAmt(double invAmt) {
		this.invAmt = invAmt;
	}

	public double getInvMargin() {
		return invMargin;
	}

	public void setInvMargin(double invMargin) {
		this.invMargin = invMargin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getListedType() {
		return listedType;
	}

	public void setListedType(String listedType) {
		this.listedType = listedType;
	}

	@Override
	public String toString() {
		return "Tick [mID=" + mID + ", memName=" + memName + ", date=" + date
				+ ", strikeNo=" + strikeNo + ", dod=" + dod + ", contNo="
				+ contNo + ", commName=" + commName + ", bsDirect=" + bsDirect
				+ ", bsDesc=" + bsDesc + ", vol=" + vol + ", sureVol="
				+ sureVol + ", pog=" + pog + ", inPog=" + inPog + ", outPog="
				+ outPog + ", up=" + up + ", invoice=" + invoice + ", invAmt="
				+ invAmt + ", invMargin=" + invMargin + ", status=" + status
				+ ", statusDesc=" + statusDesc + ", listedType=" + listedType
				+ ", contAmt=" + contAmt + ", trdCharge=" + trdCharge
				+ ", trdMargin=" + trdMargin + ", commBrand=" + commBrand
				+ ", divName=" + divName + ", store=" + store + "]";
	}



}
