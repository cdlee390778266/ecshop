
package com.cnacex.eshop.msg.body.member;

import java.util.List;

import com.cnacex.eshop.modul.MenuNode;
import com.cnacex.eshop.msg.body.comm.OperRight;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */
public class LoginRsp {
	
	@XStreamAlias("mid")
	private String mID;
	
	
	@XStreamAlias("operid")
	private String operID;
	
	
	@XStreamAlias("opertype")
	private char operType;
	
	
	@XStreamAlias("opername")
	private String operName;
	
	@XStreamAlias("opersex")
	private char operSex;

	
	@XStreamAlias("operphoto")
	private String operPhoto;
	
	private boolean sellEnable;
	
	private boolean buyEnable;
	
	@XStreamImplicit
	private List<OperRight> operRights;
	
	@XStreamAlias("mgrid")
	private char mgrID;

	@XStreamAlias("pwdstatus")
	private char pwdStatus;
	
	@XStreamAlias("memtype")
	private String memType;
	
	@XStreamAlias("memname")
	private String memName;
	
	@XStreamAlias("memlevel")
	private String memLevel;
	
	
	@XStreamImplicit
	private List<MemMarket> memMarkets;
	
	@XStreamAlias("memmarket")
	public static class MemMarket {
		
		@XStreamAlias("markcode")
		private String	markCode;
		
		@XStreamAlias("mgrmid")
		private String	mgrMID;
		
		@XStreamAlias("ismid")
		private String	isMID;

		public String getMarkCode() {
			return markCode;
		}

		public void setMarkCode(String markCode) {
			this.markCode = markCode;
		}

		public String getMgrMID() {
			return mgrMID;
		}

		public void setMgrMID(String mgrMID) {
			this.mgrMID = mgrMID;
		}

		public String getIsMID() {
			return isMID;
		}

		public void setIsMID(String isMID) {
			this.isMID = isMID;
		}


	}
	
	private List<MenuNode> tradeMenus;
	
	@XStreamImplicit
	private List<TxComm> txComms;
	
	@XStreamAlias("txcomm")
	public static class TxComm {
		
		@XStreamAlias("classcode")
		private String	classCode;
		
		@XStreamAlias("bsdirect")
		private String bsDirect;

		public String getClassCode() {
			return classCode;
		}

		public void setClassCode(String classCode) {
			this.classCode = classCode;
		}

		public String getBsDirect() {
			return bsDirect;
		}

		public void setBsDirect(String bsDirect) {
			this.bsDirect = bsDirect;
		}
	}

	public boolean isSellEnable() {
		return sellEnable;
	}

	public void setSellEnable(boolean sellEnable) {
		this.sellEnable = sellEnable;
	}

	public boolean isBuyEnable() {
		return buyEnable;
	}

	public void setBuyEnable(boolean buyEnable) {
		this.buyEnable = buyEnable;
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

	public char getOperType() {
		return operType;
	}

	public void setOperType(char operType) {
		this.operType = operType;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public char getOperSex() {
		return operSex;
	}

	public void setOperSex(char operSex) {
		this.operSex = operSex;
	}

	public String getOperPhoto() {
		return operPhoto;
	}

	public void setOperPhoto(String operPhoto) {
		this.operPhoto = operPhoto;
	}

	public List<OperRight> getOperRights() {
		return operRights;
	}

	public void setOperRights(List<OperRight> operRights) {
		this.operRights = operRights;
	}

	public char getMgrID() {
		return mgrID;
	}

	public void setMgrID(char mgrID) {
		this.mgrID = mgrID;
	}

	public char getPwdStatus() {
		return pwdStatus;
	}

	public void setPwdStatus(char pwdStatus) {
		this.pwdStatus = pwdStatus;
	}

	public String getMemType() {
		return memType;
	}

	public void setMemType(String memType) {
		this.memType = memType;
	}



	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemLevel() {
		return memLevel;
	}

	public void setMemLevel(String memLevel) {
		this.memLevel = memLevel;
	}

	public List<MemMarket> getMemMarkets() {
		return memMarkets;
	}

	public void setMemMarkets(List<MemMarket> memMarkets) {
		this.memMarkets = memMarkets;
	}

	public List<TxComm> getTxComms() {
		return txComms;
	}

	public void setTxComms(List<TxComm> txComms) {
		this.txComms = txComms;
	}

	public List<MenuNode> getTradeMenus() {
		return tradeMenus;
	}

	public void setTradeMenus(List<MenuNode> tradeMenus) {
		this.tradeMenus = tradeMenus;
	}
	
	
}
