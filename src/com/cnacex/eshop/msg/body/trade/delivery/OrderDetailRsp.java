package com.cnacex.eshop.msg.body.trade.delivery;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.CostPay;
import com.cnacex.eshop.msg.body.comm.Prop;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class OrderDetailRsp {
	
	@XStreamAlias("strikeno")
	private String strikeNo;
	
	@XStreamAlias("contno")
	private String contNo;
	
	@XStreamAlias("lsmid")
	private String lsmID;

	@XStreamAlias("stxoper")
	private String stxOper;
	
	@XStreamAlias("sauoper")
	private String sauOper;
	
	@XStreamAlias("spyoper")
	private String spyOper;
	
	private String deListNo;
	
	@XStreamAlias("dbmid")
	private String dbmID;
	
	@XStreamAlias("btxoper")
	private String btxOper;
	
	@XStreamAlias("bauoper")
	private String bauOper;
	
	@XStreamAlias("bpyoper")
	private String bpyOper;
	
	@XStreamAlias("listedno")
	private String listedNo;
	
	@XStreamAlias("listedtype")
	private String listedType;
	
	private String listedTypeName;
	
	@XStreamAlias("wrno")
	private String wrNo;
	
	@XStreamAlias("commcode")
	private String commCode;
	
	private String commName;
	
	@XStreamAlias("classcode")
	private String classCode;
	
	private String className;
	
	@XStreamAlias("markcode")
	private String markCode;
	
	private String markName;
	
	@XStreamAlias("up")
	private double up;
	
	@XStreamAlias("dod")
	private String dod;
	
	@XStreamAlias("doe")
	private String doe;
	
	@XStreamAlias("tos")
	private String tos;
	
	
	private String uom;
	
	@XStreamAlias("delidate")
	private String deliDate;
	
	@XStreamAlias("storage")
	private String storage;
	
	@XStreamAlias("title")
	private String title;
	
	@XStreamAlias("titlepic")
	private String titlePic;
	
	@XStreamAlias("vol")
	private int vol;
	
	@XStreamAlias("top")
	private String top;
	
	@XStreamAlias("lastpd")
	private String lastPD;
	
	@XStreamAlias("invoice")
	private String invoice;
	
	@XStreamAlias("invdate")
	private String InvDate;
	
	@XStreamAlias("remark")
	private String remark;
	
	@XStreamAlias("status")
	private int status;
	
	private String statusDesc;
	
	@XStreamAlias("effrec")
	private int effRec;
	
	private String effRecDesc;
	
	@XStreamAlias("txsn")
	private String txSN;
	
	@XStreamAlias("txsubsn")
	private String txSubSN;
	
	@XStreamAlias("realpd")
	private String realPD;
	
	@XStreamAlias("realdgd")
	private String realDGD;
	
	@XStreamAlias("realtgd")
	private String realTGD;
	
	@XStreamAlias("realid")
	private String realID;
	
	@XStreamImplicit
	private List<Prop> props;

	@XStreamImplicit
	private List<CostPay> costPays;

	public String getStrikeNo() {
		return strikeNo;
	}

	public void setStrikeNo(String strikeNo) {
		this.strikeNo = strikeNo;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getLsmID() {
		return lsmID;
	}

	public void setLsmID(String lsmID) {
		this.lsmID = lsmID;
	}

	public String getStxOper() {
		return stxOper;
	}

	public void setStxOper(String stxOper) {
		this.stxOper = stxOper;
	}

	public String getSauOper() {
		return sauOper;
	}

	public void setSauOper(String sauOper) {
		this.sauOper = sauOper;
	}



	public String getSpyOper() {
		return spyOper;
	}

	public void setSpyOper(String spyOper) {
		this.spyOper = spyOper;
	}

	public String getDbmID() {
		return dbmID;
	}

	public void setDbmID(String dbmID) {
		this.dbmID = dbmID;
	}

	public String getBtxOper() {
		return btxOper;
	}

	public void setBtxOper(String btxOper) {
		this.btxOper = btxOper;
	}

	public String getBauOper() {
		return bauOper;
	}

	public void setBauOper(String bauOper) {
		this.bauOper = bauOper;
	}

	public String getBpyOper() {
		return bpyOper;
	}

	public void setBpyOper(String bpyOper) {
		this.bpyOper = bpyOper;
	}

	public String getListedNo() {
		return listedNo;
	}

	public void setListedNo(String listedNo) {
		this.listedNo = listedNo;
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

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMarkCode() {
		return markCode;
	}

	public void setMarkCode(String markCode) {
		this.markCode = markCode;
	}

	public String getMarkName() {
		return markName;
	}

	public void setMarkName(String markName) {
		this.markName = markName;
	}

	public double getUp() {
		return up;
	}

	public void setUp(double up) {
		this.up = up;
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

	public String getTos() {
		return tos;
	}

	public void setTos(String tos) {
		this.tos = tos;
	}

	public String getDeliDate() {
		return deliDate;
	}

	public void setDeliDate(String deliDate) {
		this.deliDate = deliDate;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
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

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getLastPD() {
		return lastPD;
	}

	public void setLastPD(String lastPD) {
		this.lastPD = lastPD;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getInvDate() {
		return InvDate;
	}

	public void setInvDate(String invDate) {
		InvDate = invDate;
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

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public int getEffRec() {
		return effRec;
	}

	public void setEffRec(int effRec) {
		this.effRec = effRec;
	}

	public String getEffRecDesc() {
		return effRecDesc;
	}

	public void setEffRecDesc(String effRecDesc) {
		this.effRecDesc = effRecDesc;
	}

	public String getTxSN() {
		return txSN;
	}

	public void setTxSN(String txSN) {
		this.txSN = txSN;
	}

	public String getTxSubSN() {
		return txSubSN;
	}

	public void setTxSubSN(String txSubSN) {
		this.txSubSN = txSubSN;
	}

	public String getRealPD() {
		return realPD;
	}

	public void setRealPD(String realPD) {
		this.realPD = realPD;
	}

	public String getRealDGD() {
		return realDGD;
	}

	public void setRealDGD(String realDGD) {
		this.realDGD = realDGD;
	}

	public String getRealTGD() {
		return realTGD;
	}

	public void setRealTGD(String realTGD) {
		this.realTGD = realTGD;
	}

	public String getRealID() {
		return realID;
	}

	public void setRealID(String realID) {
		this.realID = realID;
	}

	public List<Prop> getProps() {
		return props;
	}

	public void setProps(List<Prop> props) {
		this.props = props;
	}

	public List<CostPay> getCostPays() {
		return costPays;
	}

	public void setCostPays(List<CostPay> costPays) {
		this.costPays = costPays;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getDeListNo() {
		return deListNo;
	}

	public void setDeListNo(String deListNo) {
		this.deListNo = deListNo;
	}




}
