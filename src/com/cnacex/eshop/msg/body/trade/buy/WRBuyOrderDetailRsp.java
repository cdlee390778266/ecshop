package com.cnacex.eshop.msg.body.trade.buy;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.CostPay;
import com.cnacex.eshop.msg.body.comm.Prop;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 摘牌单详细信息查询响应
 * @author frog
 *
 */
public class WRBuyOrderDetailRsp {

	/**
	 * 摘牌编号
	 */
	@XStreamAlias("delistno")
	private String deListNo;
	
	/**
	 * 摘牌买方
	 */
	@XStreamAlias("mid")
	private String mid;
	
	/**
	 * 审核员
	 */
	@XStreamAlias("auoper")
	private String auOper;
	
	/**
	 * 挂牌编号
	 */
	@XStreamAlias("listedno")
	private String listedNo;
	
	/**
	 * 挂牌方式
	 */
	@XStreamAlias("listedtype")
	private String ListedType;
	
	private String ListedTypeName;
	
	/**
	 * 仓单编号
	 */
	@XStreamAlias("wrno")
	private String wrno;
	
	/**
	 * 商品代码
	 */
	@XStreamAlias("commcode")
	private String commCode;
	
	private String commName;
	
	/**
	 * 品类代码
	 */
	@XStreamAlias("classcode")
	private String classCode;
	
	private String className;
	
	/**
	 * 市场代码
	 */
	@XStreamAlias("markcode")
	private String markCode;
	
	private String markName;
	
	/**
	 * 单价
	 */
	@XStreamAlias("up")
	private String up;
	
	/**
	 * 一口价标识
	 */
	@XStreamAlias("fpflg")
	private String fpFlg;
	
	/**
	 * 商品总量
	 */
	@XStreamAlias("qty")
	private long qty;
	
	/**
	 * 整单标识
	 */
	@XStreamAlias("wholeflg")
	private String wholeFlg;
	
	/**
	 * 摘牌日期
	 */
	@XStreamAlias("dod")
	private String dod;
	
	/**
	 * 挂牌有效期
	 */
	@XStreamAlias("doe")
	private String doe;
	
	/**
	 * 交收日期
	 */
	@XStreamAlias("delidate")
	private String deliDate;
	
	/**
	 * 最后付款日
	 */
	@XStreamAlias("lastpd")
	private String lastPD;
	
	/**
	 * 商品标题
	 */
	@XStreamAlias("title")
	private String title;
	
	/**
	 * 付款方式
	 */
	@XStreamAlias("top")
	private String top;
	
	/**
	 * 平台监管发票
	 */
	@XStreamAlias("invoice")
	private String invoice;
	
	/**
	 * (买方)备注
	 */
	@XStreamAlias("remark")
	private String remark;
	
	/**
	 * 摘牌状态
	 */
	@XStreamAlias("status")
	private int status;
	
	private String statusDesc;
	
	/**
	 * 记录生效标志
	 */
	@XStreamAlias("effrec")
	private char effRec;
	
	private String effRecDesc;
	
	/**
	 * 当前审核编号
	 */
	@XStreamAlias("auditno")
	private String auditNo;
	
	/**
	 * 单位
	 */
	private String uom;
	
	/**
	 * 数量
	 */
	private String vol;
	
	/**
	 * 交货仓库
	 */
	private String storage;
	
	@XStreamImplicit
	private List<Prop> props;
	
	@XStreamImplicit
	private List<CostPay> costPays;

	public String getDeListNo() {
		return deListNo;
	}

	public void setDeListNo(String deListNo) {
		this.deListNo = deListNo;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getAuOper() {
		return auOper;
	}

	public void setAuOper(String auOper) {
		this.auOper = auOper;
	}

	public String getListedNo() {
		return listedNo;
	}

	public void setListedNo(String listedNo) {
		this.listedNo = listedNo;
	}

	public String getListedType() {
		return ListedType;
	}

	public void setListedType(String listedType) {
		ListedType = listedType;
	}
	
	public String getWrno() {
		return wrno;
	}

	public void setWrno(String wrno) {
		this.wrno = wrno;
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

	public String getUp() {
		return up;
	}

	public void setUp(String up) {
		this.up = up;
	}

	public String getFpFlg() {
		return fpFlg;
	}

	public void setFpFlg(String fpFlg) {
		this.fpFlg = fpFlg;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public String getWholeFlg() {
		return wholeFlg;
	}

	public void setWholeFlg(String wholeFlg) {
		this.wholeFlg = wholeFlg;
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

	public String getDeliDate() {
		return deliDate;
	}

	public void setDeliDate(String deliDate) {
		this.deliDate = deliDate;
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

	public char getEffRec() {
		return effRec;
	}

	public void setEffRec(char effRec) {
		this.effRec = effRec;
	}

	public String getEffRecDesc() {
		return effRecDesc;
	}

	public void setEffRecDesc(String effRecDesc) {
		this.effRecDesc = effRecDesc;
	}

	public String getAuditNo() {
		return auditNo;
	}

	public void setAuditNo(String auditNo) {
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
	
	public String getListedTypeName() {
		return ListedTypeName;
	}

	public void setListedTypeName(String listedTypeName) {
		ListedTypeName = listedTypeName;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
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

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getVol() {
		return vol;
	}

	public void setVol(String vol) {
		this.vol = vol;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	@Override
	public String toString() {
		return String.format("WRBuyOrderDetailRsp [delistNo=%s, mid=%s, auOper=%s, "
				+ "deListNo=%s, ListedType=%s, wrno=%s, "
				+ "commCode=%s, classCode=%s, markCode=%s, "
				+ "up=%s, fpFlg=%s, qty=%s, "
				+ "wholeFlg=%s, dod=%s, doe=%s, "
				+ "deliDate=%s, lastPD=%s, title=%s, "
				+ "top=%s, invoice=%s, remark=%s, "
				+ "status=%s, effRec=%s, auditNo=%s]", deListNo, mid, auOper,
				listedNo, ListedType, wrno,
				commCode, classCode, markCode,
				up, fpFlg, qty,
				wholeFlg, dod, doe,
				deliDate, lastPD, title,
				top, invoice, remark,
				status, effRec, auditNo);
	}
}
