package com.cnacex.eshop.msg.body.trade.sell;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.Prop;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */
public class ApplyReq {
	
	@XStreamAlias("listedno")
	private String listedNo;
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("txoperid")
	private String txOperID;
	
	@XStreamAlias("listedtype")
	private char	listedType;
	
	@XStreamAlias("wrno")
	private String wrNO;
	
	@XStreamAlias("commcode")
	private String commCode;
	
	@XStreamAlias("classcode")
	private String classCode;
	
	@XStreamAlias("markcode")
	private String markCode;
	
	@XStreamAlias("unitprice")
	private double unitPrice;
	
	@XStreamAlias("fpflag")
	private char fpFlag;
	
	@XStreamAlias("qty")
	private int qty;
	

	@XStreamAlias("wholeflag")
	private char  wholeFlag;
	
	
	@XStreamAlias("moq")
	private int moq;
	
	@XStreamAlias("incrnum")
	private int incrNum;
	
	@XStreamAlias("doe")
	private String doe;
	
	@XStreamAlias("tos")
	private String  tos;
	
	@XStreamAlias("delidate")
	private String deliDate;
	
	@XStreamAlias("delidate2")
	private int deliDate2;
	
	@XStreamAlias("storage")
	private String storage;
	
	@XStreamAlias("lastpd")
	private String lastPD;
	
	@XStreamAlias("lastpd2")
	private int lastPD2;
	
	@XStreamAlias("invoice")
	private char invoice;

	@XStreamAlias("delist")
	private char delist;
	
	@XStreamAlias("mart")
	private char mart;
	
	
	@XStreamAlias("title")
	private String  title;
	
	
	@XStreamAlias("titlepic")
	private String  titlePic;
	
	@XStreamAlias("detail")
	private String  Detail;
	
	@XStreamAlias("ctxpic1")
	private String ctxPic1;
	
	@XStreamAlias("ctxpic2")
	private String ctxPic2;
	
	@XStreamAlias("ctxpic3")
	private String ctxPic3;
	
	@XStreamAlias("ctxpic4")
	private String ctxPic4;
	
	@XStreamAlias("ctxpic5")
	private String ctxPic5;
	
	
	@XStreamAlias("summary1")
	private String summary1;
	
	@XStreamAlias("summary2")
	private String summary2;
	
	@XStreamAlias("summary3")
	private String summary3;
	
	@XStreamAlias("summary4")
	private String summary4;

	
	@XStreamImplicit
	private List<Prop> props;
	
	@XStreamImplicit
	private List<DelistMem> delistMems;
	
	@XStreamAlias("delistmem")
	public static class DelistMem{
		
		@XStreamAlias("delistmid")
		private String delistMID;

		public String getDelistMID() {
			return delistMID;
		}

		public void setDelistMID(String delistMID) {
			this.delistMID = delistMID;
		}
		
	
	}


	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getTxOperID() {
		return txOperID;
	}

	public void setTxOperID(String txOperID) {
		this.txOperID = txOperID;
	}

	public char getListedType() {
		return listedType;
	}

	public void setListedType(char listedType) {
		this.listedType = listedType;
	}

	public String getWrNO() {
		return wrNO;
	}

	public void setWrNO(String wrNO) {
		this.wrNO = wrNO;
	}

	public String getCommCode() {
		return commCode;
	}

	public void setCommCode(String commCode) {
		this.commCode = commCode;
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



	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public char getFpFlag() {
		return fpFlag;
	}

	public void setFpFlag(char fpFlag) {
		this.fpFlag = fpFlag;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public char getWholeFlag() {
		return wholeFlag;
	}

	public void setWholeFlag(char wholeFlag) {
		this.wholeFlag = wholeFlag;
	}



	public int getMoq() {
		return moq;
	}

	public void setMoq(int moq) {
		this.moq = moq;
	}

	public int getIncrNum() {
		return incrNum;
	}

	public void setIncrNum(int incrNum) {
		this.incrNum = incrNum;
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

	public int getDeliDate2() {
		return deliDate2;
	}

	public void setDeliDate2(int deliDate2) {
		this.deliDate2 = deliDate2;
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

	public int getLastPD2() {
		return lastPD2;
	}

	public void setLastPD2(int lastPD2) {
		this.lastPD2 = lastPD2;
	}

	public char getDelist() {
		return delist;
	}

	public char getInvoice() {
		return invoice;
	}

	public void setInvoice(char invoice) {
		this.invoice = invoice;
	}

	public void setDelist(char delist) {
		this.delist = delist;
	}

	public char getMart() {
		return mart;
	}

	public void setMart(char mart) {
		this.mart = mart;
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

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}

	public String getCtxPic1() {
		return ctxPic1;
	}

	public void setCtxPic1(String ctxPic1) {
		this.ctxPic1 = ctxPic1;
	}

	public String getCtxPic2() {
		return ctxPic2;
	}

	public void setCtxPic2(String ctxPic2) {
		this.ctxPic2 = ctxPic2;
	}

	public String getCtxPic3() {
		return ctxPic3;
	}

	public void setCtxPic3(String ctxPic3) {
		this.ctxPic3 = ctxPic3;
	}

	public String getCtxPic4() {
		return ctxPic4;
	}

	public void setCtxPic4(String ctxPic4) {
		this.ctxPic4 = ctxPic4;
	}

	public String getCtxPic5() {
		return ctxPic5;
	}

	public void setCtxPic5(String ctxPic5) {
		this.ctxPic5 = ctxPic5;
	}

	public List<Prop> getProps() {
		return props;
	}

	public void setProps(List<Prop> props) {
		this.props = props;
	}

	public List<DelistMem> getDelistMems() {
		return delistMems;
	}

	public void setDelistMems(List<DelistMem> delistMems) {
		this.delistMems = delistMems;
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

	public String getListedNo() {
		return listedNo;
	}

	public void setListedNo(String listedNo) {
		this.listedNo = listedNo;
	}

	@Override
	public String toString() {
		return "ApplyReq [listedNo=" + listedNo + ", mID=" + mID
				+ ", txOperID=" + txOperID + ", listedType=" + listedType
				+ ", wrNO=" + wrNO + ", commCode=" + commCode + ", classCode="
				+ classCode + ", markCode=" + markCode + ", unitPrice="
				+ unitPrice + ", fpFlag=" + fpFlag + ", qty=" + qty
				+ ", wholeFlag=" + wholeFlag + ", moq=" + moq + ", incrNum="
				+ incrNum + ", doe=" + doe + ", tos=" + tos + ", deliDate="
				+ deliDate + ", deliDate2=" + deliDate2 + ", storage="
				+ storage + ", lastPD=" + lastPD + ", lastPD2=" + lastPD2
				+ ", invoice=" + invoice + ", delist=" + delist + ", mart="
				+ mart + ", title=" + title + ", titlePic=" + titlePic
				+ ", Detail=" + Detail + ", ctxPic1=" + ctxPic1 + ", ctxPic2="
				+ ctxPic2 + ", ctxPic3=" + ctxPic3 + ", ctxPic4=" + ctxPic4
				+ ", ctxPic5=" + ctxPic5 + ", summary1=" + summary1
				+ ", summary2=" + summary2 + ", summary3=" + summary3
				+ ", summary4=" + summary4 + ", props=" + props
				+ ", delistMems=" + delistMems + "]";
	}



	
	
	
	
}
