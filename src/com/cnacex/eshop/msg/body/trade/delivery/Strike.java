package com.cnacex.eshop.msg.body.trade.delivery;


import com.thoughtworks.xstream.annotations.XStreamAlias;



public class Strike {
	
	/**
	 * 成交编号
	 */
	@XStreamAlias("strikeno")
	private String strikeNo;
	
	/**
	 * 买方会员编号
	 */
	@XStreamAlias("bmid")
	private String bmID;
	
	/**
	 * 买方会员名称
	 */
	@XStreamAlias("bmemname")
	private String bmemName;
	
	/**
	 * 卖方会员编号
	 */
	@XStreamAlias("smid")
	private String smID;
	
	/**
	 * 卖方会员名称
	 */
	@XStreamAlias("smemname")
	private String smemName;
	
	/**
	 * 合同号
	 */
	@XStreamAlias("contno")
	private String contNo;
	
	/**
	 * 挂牌方式
	 */
	@XStreamAlias("listedtype")
	private String listedType;
	
	private String listedTypeName;
	
	/**
	 * 仓单号
	 */
	@XStreamAlias("wrno")
	private String wrNo;
	
	/**
	 * 商品代码
	 */
	@XStreamAlias("commcode")
	private String commCode;
	
	/**
	 * 商品名称
	 */
	private String commName;
	
	/**
	 * 
	 */
	private String clsName;
	
	/**
	 * 
	 */
	private String clsCode;
	
	/**
	 * 摘要信息1
	 */
	@XStreamAlias("summary1")
	private String summary1;
	
	/**
	 * 摘要信息2
	 */
	@XStreamAlias("summary2")
	private String summary2;
	
	/**
	 * 摘要信息3
	 */
	@XStreamAlias("summary3")
	private String summary3;
	
	/**
	 * 摘要信息4
	 */
	@XStreamAlias("summary4")
	private String summary4;
	
	/**
	 * 交收仓库
	 */
	@XStreamAlias("storage")
	private String storage;

	/**
	 * 单价
	 */
	@XStreamAlias("up")
	private double up;
	
	/**
	 * 成交数量
	 */
	@XStreamAlias("vol")
	private int vol;
	
	/**
	 * 单位
	 */
	private String uom;
	
	/**
	 * 最后付款日
	 */
	@XStreamAlias("lastpd")
	private String lastpd;
	
	private String contractName;
	
	/**
	 * 
	 */
	@XStreamAlias("conttime")
	private String contTime;
	
	/**
	 * 约定交收日期
	 */
	@XStreamAlias("delidate")
	private String delidate;
	
	/**
	 * 
	 */
	@XStreamAlias("strikedate")
	private String strikeDate;
	
	@XStreamAlias("contamt")
	private double contAmt;
	
	@XStreamAlias("title")
	private String title;
	
	@XStreamAlias("titlepic")
	private String titlePic;
	
	@XStreamAlias("dod")
	private String dod;
	
	@XStreamAlias("doe")
	private String doe;
	
	@XStreamAlias("lastcompno")
	private String lastCompNo;
	
	@XStreamAlias("status")
	private int status;
	
	private String statusDesc;
	
	@XStreamAlias("effrec")
	private int effRec;
	
	private String effRecDesc;
	
	private int enableT;
	
	private int enableP;
	
	public String getClsName() {
		return clsName;
	}

	public void setClsName(String clsName) {
		this.clsName = clsName;
	}

	public String getClsCode() {
		return clsCode;
	}

	public void setClsCode(String clsCode) {
		this.clsCode = clsCode;
	}

	public String getSummary1() {
		return summary1;
	}

	public void setSummary1(String summary1) {
		this.summary1 = summary1;
	}

	public String getSummary2() {
		return summary2;
	}

	public void setSummary2(String summary2) {
		this.summary2 = summary2;
	}

	public String getSummary3() {
		return summary3;
	}

	public void setSummary3(String summary3) {
		this.summary3 = summary3;
	}

	public String getSummary4() {
		return summary4;
	}

	public void setSummary4(String summary4) {
		this.summary4 = summary4;
	}

	public String getBmID() {
		return bmID;
	}

	public void setBmID(String bmID) {
		this.bmID = bmID;
	}
	
	public String getLastCompNo() {
		return lastCompNo;
	}

	public void setLastCompNo(String lastCompNo) {
		this.lastCompNo = lastCompNo;
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

	public String getBmemName() {
		return bmemName;
	}

	public void setBmemName(String bmemName) {
		this.bmemName = bmemName;
	}

	public String getSmemName() {
		return smemName;
	}

	public void setSmemName(String smemName) {
		this.smemName = smemName;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getListedType() {
		return listedType;
	}

	public void setListedType(String listedType) {
		this.listedType = listedType;
	}

	
	public String getListedTypeName() {
		return listedTypeName;
	}

	public void setListedTypeName(String listedTypeName) {
		this.listedTypeName = listedTypeName;
	}

	public String getWrNo() {
		return wrNo;
	}

	public void setWrNo(String wrNo) {
		this.wrNo = wrNo;
	}

	public String getCommCode() {
		return commCode;
	}

	public void setCommCode(String commCode) {
		this.commCode = commCode;
	}

	public double getUp() {
		return up;
	}

	public void setUp(double up) {
		this.up = up;
	}

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitlePic() {
		return titlePic;
	}

	public void setTitlePic(String titlePic) {
		this.titlePic = titlePic;
	}

	public String getDod() {
		return dod;
	}

	public void setDod(String dod) {
		this.dod = dod;
	}

	public String getDoe() {
		return doe;
	}

	public void setDoe(String doe) {
		this.doe = doe;
	}
	
	public String getLastpd() {
		return lastpd;
	}

	public void setLastpd(String lastpd) {
		this.lastpd = lastpd;
	}

	public String getDelidate() {
		return delidate;
	}

	public void setDelidate(String delidate) {
		this.delidate = delidate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

	public int getEffRec() {
		return effRec;
	}

	public void setEffRec(int effRec) {
		this.effRec = effRec;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getEffRecDesc() {
		return effRecDesc;
	}

	public void setEffRecDesc(String effRecDesc) {
		this.effRecDesc = effRecDesc;
	}

	public double getContAmt() {
		return contAmt;
	}

	public void setContAmt(double contAmt) {
		this.contAmt = contAmt;
	}

	public int getEnableT() {
		return enableT;
	}

	public void setEnableT(int enableT) {
		this.enableT = enableT;
	}

	public int getEnableP() {
		return enableP;
	}

	public void setEnableP(int enableP) {
		this.enableP = enableP;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getStrikeDate() {
		return strikeDate;
	}

	public void setStrikeDate(String strikeDate) {
		this.strikeDate = strikeDate;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContTime() {
		return contTime;
	}

	public void setContTime(String contTime) {
		this.contTime = contTime;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	
}
