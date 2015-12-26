package com.cnacex.eshop.msg;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Head {
	
	@XStreamAlias("busidate")
	private String busiDate;
	
	@XStreamAlias("billdate")
	private String billDate;
	
	@XStreamAlias("sysstatus")
	private String sysStatus;
	
	@XStreamAlias("syssubstatus")
	private String sysSubStatus;
	

	/**成功标志**/
	@XStreamAlias("succflag")
	private int succFlag;
	
	/** 响应码  **/
	@XStreamAlias("rspcode")
	private String rspCode;
	
	/** 响应信息  **/
	@XStreamAlias("rspmsg")
	private String txRspMsg;
	
	/** 流水号,请求与响应共用 ***/
	@XStreamAlias("txsn")
	private String txSN;
	
	private int reqno;
	
	private String rspMsg;

	public int getSuccFlag() {
		return succFlag;
	}
	public void setSuccFlag(int succFlag) {
		this.succFlag = succFlag;
	}
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}

	public String getTxSN() {
		return txSN;
	}
	
	public void setTxSN(String txSN) {
		this.txSN = txSN;
	}

	public int getReqno() {
		return reqno;
	}
	public void setReqno(int reqno) {
		this.reqno = reqno;
	}
	public String getTxRspMsg() {
		return txRspMsg;
	}
	public void setTxRspMsg(String txRspMsg) {
		this.txRspMsg = txRspMsg;
	}
	
	public String getRspMsg() {
		StringBuffer strbuf = new StringBuffer();
		if(!"0000".equalsIgnoreCase(rspCode)){
			strbuf.append("交易号:").append(reqno);
			strbuf.append("错误提示:").append(txRspMsg);			
			return strbuf.toString();
		}else{
			return txRspMsg;
		}
	}
	
	
	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}
	public String getBusiDate() {
		return busiDate;
	}
	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getSysStatus() {
		return sysStatus;
	}
	public void setSysStatus(String sysStatus) {
		this.sysStatus = sysStatus;
	}
	public String getSysSubStatus() {
		return sysSubStatus;
	}
	public void setSysSubStatus(String sysSubStatus) {
		this.sysSubStatus = sysSubStatus;
	}

	
	
	

}
