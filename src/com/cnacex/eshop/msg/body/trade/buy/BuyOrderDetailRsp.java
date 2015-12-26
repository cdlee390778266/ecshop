package com.cnacex.eshop.msg.body.trade.buy;

import java.util.List;


import com.cnacex.eshop.msg.body.comm.CostPay;
import com.cnacex.eshop.msg.body.comm.Prop;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */
public class BuyOrderDetailRsp {
	
	@XStreamAlias("delistno")
	private String deListNo;
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("txoper")
	private String txOper;
	
	@XStreamAlias("auoper")
	private String auOper;
	
	@XStreamAlias("pyoper")
	private String pyOper;
	
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
	
	private String uom;
	
	@XStreamAlias("classcode")
	private String classCode;
	
	private String className;
	
	
	@XStreamAlias("markcode")
	private String markCode;
	
	private String markName;
	
	@XStreamAlias("up")
	private double	up;
	
	@XStreamAlias("fpflg")
	private char	fpFlg;
	
	@XStreamAlias("qty")
	private int		qty;
	
	@XStreamAlias("wholeflg")
	private char	wholeFlg;
	
	@XStreamAlias("moq")
	private int moq;
	
	@XStreamAlias("ic")
	private int ic;
	
	@XStreamAlias("rem")
	private int rem;
	
	@XStreamAlias("dod")
	private String dod;
	
	@XStreamAlias("doe")
	private String doe;
	
	@XStreamAlias("tos")
	private String tos;
	
	@XStreamAlias("delidate")
	private String deliDate;
	
	@XStreamAlias("storage")
	private String storage;
	
	@XStreamAlias("lastpd")
	private String lastPD;


	@XStreamAlias("title")
	private String title;
	
	@XStreamAlias("vol")
	private int vol;
	
	@XStreamAlias("top")
	private String top;
	
	@XStreamAlias("invoice")
	private String invoice;
	
	@XStreamAlias("remark")
	private String reMark;
	
	
	@XStreamAlias("status")
	private int status;
	
	private String statusDesc;
	
	@XStreamAlias("effrec")
	private char effRec;
	
	private String effRecDesc;
	
	@XStreamAlias("auditno")
	private int auditNo;
	

	@XStreamImplicit
	private List<Prop> props;
	
	@XStreamImplicit
	private List<CostPay> costPays;



	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getTxOper() {
		return txOper;
	}

	public void setTxOper(String txOper) {
		this.txOper = txOper;
	}

	public String getAuOper() {
		return auOper;
	}

	public void setAuOper(String auOper) {
		this.auOper = auOper;
	}

	public String getPyOper() {
		return pyOper;
	}

	public void setPyOper(String pyOper) {
		this.pyOper = pyOper;
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



	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}





	public int getMoq() {
		return moq;
	}

	public void setMoq(int moq) {
		this.moq = moq;
	}

	public int getIc() {
		return ic;
	}

	public void setIc(int ic) {
		this.ic = ic;
	}

	public int getRem() {
		return rem;
	}

	public void setRem(int rem) {
		this.rem = rem;
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

	public String getLastPD() {
		return lastPD;
	}

	public void setLastPD(String lastPD) {
		this.lastPD = lastPD;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public String getDeListNo() {
		return deListNo;
	}

	public void setDeListNo(String deListNo) {
		this.deListNo = deListNo;
	}

	public String getListedNo() {
		return listedNo;
	}

	public void setListedNo(String listedNo) {
		this.listedNo = listedNo;
	}

	public char getFpFlg() {
		return fpFlg;
	}

	public void setFpFlg(char fpFlg) {
		this.fpFlg = fpFlg;
	}

	public char getWholeFlg() {
		return wholeFlg;
	}

	public void setWholeFlg(char wholeFlg) {
		this.wholeFlg = wholeFlg;
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

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getReMark() {
		return reMark;
	}

	public void setReMark(String reMark) {
		this.reMark = reMark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public char getEffRec() {
		return effRec;
	}

	public void setEffRec(char effRec) {
		this.effRec = effRec;
	}

	public int getAuditNo() {
		return auditNo;
	}

	public void setAuditNo(int auditNo) {
		this.auditNo = auditNo;
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

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getMarkCode() {
		return markCode;
	}

	public void setMarkCode(String markCode) {
		this.markCode = markCode;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMarkName() {
		return markName;
	}

	public void setMarkName(String markName) {
		this.markName = markName;
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

	@Override
	public String toString() {
		return "BuyOrderDetailRsp [deListNo=" + deListNo + ", mID=" + mID
				+ ", txOper=" + txOper + ", auOper=" + auOper + ", pyOper="
				+ pyOper + ", listedNo=" + listedNo + ", listedType="
				+ listedType + ", wrNo=" + wrNo + ", commCode=" + commCode
				+ ", classCode=" + classCode + ", markCode=" + markCode
				+ ", up=" + up + ", fpFlg=" + fpFlg + ", qty=" + qty
				+ ", wholeFlg=" + wholeFlg + ", moq=" + moq + ", ic=" + ic
				+ ", rem=" + rem + ", dod=" + dod + ", doe=" + doe + ", tos="
				+ tos + ", deliDate=" + deliDate + ", storage=" + storage
				+ ", lastPD=" + lastPD + ", title=" + title + ", vol=" + vol
				+ ", top=" + top + ", invoice=" + invoice + ", reMark="
				+ reMark + ", status=" + status + ", effRec=" + effRec
				+ ", auditNo=" + auditNo + ", props=" + props + ", costPays="
				+ costPays + "]";
	}
	
	
	
}
