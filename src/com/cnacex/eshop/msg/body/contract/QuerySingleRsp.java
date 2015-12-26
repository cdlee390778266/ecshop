package com.cnacex.eshop.msg.body.contract;

import java.util.List;

import com.cnacex.eshop.msg.body.trade.sell.SellOrderDetailRsp.delistMem;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */
public class QuerySingleRsp {
	
	
	@XStreamAlias("contno")
	private String contNo;
	
	@XStreamAlias("strikeno")
	private String strikeNo;
	
	@XStreamAlias("smid")
	private String smID;
	
	@XStreamAlias("smemname")
	private String smemName;
	
	@XStreamAlias("bmid")
	private String bmID;
	
	@XStreamAlias("bmemname")
	private String bmemName;
	
	@XStreamAlias("conttime")
	private String contTime;
	
	@XStreamAlias("commcode")
	private String commCode;
	
	@XStreamAlias("commname")
	private String commName;
	
	@XStreamAlias("uom")
	private String uom;
	
	@XStreamAlias("vol")
	private int	vol;
	
	@XStreamAlias("up")
	private double up;
	
	@XStreamAlias("contamt")
	private double contAmt;
	
	private String contAmtUpper;
	
	@XStreamAlias("remark")
	private String remark;
	
	@XStreamAlias("status")
	private int status;
	
	private String statusDesc;
	
	@XStreamAlias("dac")
	private String dac;
	
	@XStreamAlias("modurl")
	private String modURL;
	
	@XStreamImplicit
	private List<SignList> signLists;
	
	@XStreamAlias("signlist")
	public static class SignList{
		
		@XStreamAlias("signdir")
		private String signDir;
		
		@XStreamAlias("unitname")
		private String unitName;
		
		@XStreamAlias("unitaddr")
		private String unitAddr;
		
		@XStreamAlias("legper")
		private String legPer;
		
		@XStreamAlias("enproxy")
		private String enProxy;
		
		@XStreamAlias("tel")
		private String tel;
		
		@XStreamAlias("bank")
		private String bank;
		
		@XStreamAlias("account")
		private String account;

		public String getSignDir() {
			return signDir;
		}

		public void setSignDir(String signDir) {
			this.signDir = signDir;
		}

		public String getUnitName() {
			return unitName;
		}

		public void setUnitName(String unitName) {
			this.unitName = unitName;
		}

		public String getUnitAddr() {
			return unitAddr;
		}

		public void setUnitAddr(String unitAddr) {
			this.unitAddr = unitAddr;
		}

		public String getLegPer() {
			return legPer;
		}

		public void setLegPer(String legPer) {
			this.legPer = legPer;
		}

		public String getEnProxy() {
			return enProxy;
		}

		public void setEnProxy(String enProxy) {
			this.enProxy = enProxy;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getBank() {
			return bank;
		}

		public void setBank(String bank) {
			this.bank = bank;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}
		
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getStrikeNo() {
		return strikeNo;
	}

	public void setStrikeNo(String strikeNo) {
		this.strikeNo = strikeNo;
	}

	public String getSmID() {
		return smID;
	}

	public void setSmID(String smID) {
		this.smID = smID;
	}

	public String getSmemName() {
		return smemName;
	}

	public void setSmemName(String smemName) {
		this.smemName = smemName;
	}

	public String getBmID() {
		return bmID;
	}

	public void setBmID(String bmID) {
		this.bmID = bmID;
	}

	public String getBmemName() {
		return bmemName;
	}

	public void setBmemName(String bmemName) {
		this.bmemName = bmemName;
	}

	public String getContTime() {
		return contTime;
	}

	public void setContTime(String contTime) {
		this.contTime = contTime;
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

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDac() {
		return dac;
	}

	public void setDac(String dac) {
		this.dac = dac;
	}

	public String getModURL() {
		return modURL;
	}

	public void setModURL(String modURL) {
		this.modURL = modURL;
	}

	public String getContAmtUpper() {
		return contAmtUpper;
	}

	public void setContAmtUpper(String contAmtUpper) {
		this.contAmtUpper = contAmtUpper;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public List<SignList> getSignLists() {
		return signLists;
	}

	public void setSignLists(List<SignList> signLists) {
		this.signLists = signLists;
	}
	
	
	

}
