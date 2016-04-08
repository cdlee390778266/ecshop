package com.cnacex.eshop.msg.body.trade.sell;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.Prop;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 注册仓单挂牌请求结构体
 * @author 文闻
 *
 */

public class ApplyCdListedReq {
	
	//会员编号
	@XStreamAlias("mid")
	private String mid;
	
	//挂牌交易员
	@XStreamAlias("txoperid")
	private String txoperid;
	
	//挂牌方式
	@XStreamAlias("listedtype")
	private String listedtype;
	
	//注册仓单编号
	@XStreamAlias("wrno")
	private String wrno;
	
	//挂牌商品代码
	@XStreamAlias("commcode")
	private String commcode;
	
	//挂牌商品品类
	@XStreamAlias("classcode")
	private String classcode;
	
	//挂牌商品市场
	@XStreamAlias("markcode")
	private String markcode;
	
	//挂牌商品单价
	@XStreamAlias("unitprice")
	private Double unitprice;
	
	//挂牌商品单价
	@XStreamAlias("fpflag")
	private String fpflag;	
	
	//挂牌商品总数量
	@XStreamAlias("qty")
	private Long qty;	
	
	//挂牌商品整单交易标志
	@XStreamAlias("wholeflag")
	private String wholeflag;
	
	//挂牌商品交易起订量
	@XStreamAlias("moq")
	private Long moq;
	
	//挂牌商品交易递增量
	@XStreamAlias("incrnum")
	private Long incrnum;
	
	//挂牌有效期
	@XStreamAlias("doe")
	private String doe;
	
	//发运方式
	@XStreamAlias("tos")
	private String tos;
	
	//交收日期
	@XStreamAlias("delidate")
	private String delidate;
	
	//交货仓库
	@XStreamAlias("storage")
	private String storage;
	
	//最后付款日
	@XStreamAlias("lastpd")
	private String lastpd;
	
	//平台监管发票
	@XStreamAlias("invoice")
	private String invoice;
	
	//指定摘牌方
	@XStreamAlias("delist")
	private String delist;
	
	//商品卖场
	@XStreamAlias("mart")
	private String mart;
	
	//商品标题
	@XStreamAlias("title")
	private String title;
	
	//标题配图
	@XStreamAlias("titlepic")
	private String titlepic;
	
	//商品描述
	@XStreamAlias("detail")
	private String detail;
	
	//内容配图1
	@XStreamAlias("ctxpic1")
	private String ctxpic1;
	
	//内容配图2
	@XStreamAlias("ctxpic2")
	private String ctxpic2;
	
	//内容配图3
	@XStreamAlias("ctxpic3")
	private String ctxpic3;
	
	//扩展图片标志
	@XStreamAlias("ctxpicex")
	private String ctxpicex;
	
	//摘要信息1
	@XStreamAlias("summary1")
	private String summary1;
	
	//摘要信息2
	@XStreamAlias("summary2")
	private String summary2;
	
	//摘要信息3
	@XStreamAlias("summary3")
	private String summary3;
	
	//摘要信息4
	@XStreamAlias("summary4")
	private String summary4;
	
	@XStreamImplicit
	private List<Prop> props;
	
	@XStreamImplicit
	private List<DelistMem> delistMem;
	
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getTxoperid() {
		return txoperid;
	}

	public void setTxoperid(String txoperid) {
		this.txoperid = txoperid;
	}

	public String getListedtype() {
		return listedtype;
	}

	public void setListedtype(String listedtype) {
		this.listedtype = listedtype;
	}

	public String getWrno() {
		return wrno;
	}

	public void setWrno(String wrno) {
		this.wrno = wrno;
	}

	public String getCommcode() {
		return commcode;
	}

	public void setCommcode(String commcode) {
		this.commcode = commcode;
	}

	public String getClasscode() {
		return classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}

	public String getMarkcode() {
		return markcode;
	}

	public void setMarkcode(String markcode) {
		this.markcode = markcode;
	}

	public Double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}

	public String getFpflag() {
		return fpflag;
	}

	public void setFpflag(String fpflag) {
		this.fpflag = fpflag;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public String getWholeflag() {
		return wholeflag;
	}

	public void setWholeflag(String wholeflag) {
		this.wholeflag = wholeflag;
	}

	public Long getMoq() {
		return moq;
	}

	public void setMoq(Long moq) {
		this.moq = moq;
	}

	public Long getIncrnum() {
		return incrnum;
	}

	public void setIncrnum(Long incrnum) {
		this.incrnum = incrnum;
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

	public String getDelidate() {
		return delidate;
	}

	public void setDelidate(String delidate) {
		this.delidate = delidate;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getLastpd() {
		return lastpd;
	}

	public void setLastpd(String lastpd) {
		this.lastpd = lastpd;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getDelist() {
		return delist;
	}

	public void setDelist(String delist) {
		this.delist = delist;
	}

	public String getMart() {
		return mart;
	}

	public void setMart(String mart) {
		this.mart = mart;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitlepic() {
		return titlepic;
	}

	public void setTitlepic(String titlepic) {
		this.titlepic = titlepic;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCtxpic1() {
		return ctxpic1;
	}

	public void setCtxpic1(String ctxpic1) {
		this.ctxpic1 = ctxpic1;
	}

	public String getCtxpic2() {
		return ctxpic2;
	}

	public void setCtxpic2(String ctxpic2) {
		this.ctxpic2 = ctxpic2;
	}

	public String getCtxpic3() {
		return ctxpic3;
	}

	public void setCtxpic3(String ctxpic3) {
		this.ctxpic3 = ctxpic3;
	}

	public String getCtxpicex() {
		return ctxpicex;
	}

	public void setCtxpicex(String ctxpicex) {
		this.ctxpicex = ctxpicex;
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

	public List<Prop> getProps() {
		return props;
	}

	public void setProps(List<Prop> props) {
		this.props = props;
	}

	public List<DelistMem> getDelistMem() {
		return delistMem;
	}

	public void setDelistMem(List<DelistMem> delistMem) {
		this.delistMem = delistMem;
	}

	@Override
	public String toString() {
		return "ApplyCdListedReq [mid=" + mid + ", txoperid=" + txoperid
				+ ", listedtype=" + listedtype + ", wrno=" + wrno + ", commcode=" + commcode
				+ ", classcode=" + classcode + ", markcode=" + markcode +", unitprice=" + unitprice
				+ ", fpflag=" + fpflag +", qty=" + qty +", wholeflag=" + wholeflag 
				+ ", moq=" + moq +", incrnum=" + incrnum +", doe=" + doe 
				+ ", tos=" + tos +", delidate=" + delidate +", storage=" + storage 
				+", lastpd=" + lastpd+", invoice=" + invoice+", delist=" + delist
				+", mart=" + mart+", title=" + title+", titlepic=" + titlepic
				+", detail=" + detail+", ctxpic1=" + ctxpic1+", ctxpic2=" + ctxpic2
				+", ctxpic3=" + ctxpic3+", ctxpicex=" + ctxpicex+", summary1=" + summary1
				+", summary2=" + summary2+", summary3=" + summary3+", summary4=" + summary4
				+", Props=" + props+", delistMem=" + delistMem+"]";
	}

	
	

}
