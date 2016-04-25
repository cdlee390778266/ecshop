package com.cnacex.eshop.msg.body.mall;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.Prop;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 挂牌销售商品详细信息
 */
public class ListedDetailRsp {
	
	/**
	 * 挂牌编号
	 */
	@XStreamAlias("listedno")
	private String listedNo;
	
	/**
	 * 挂牌卖方
	 */
	@XStreamAlias("mid")
	private String mID;
	
	/**
	 * 挂牌卖方名称
	 */
	@XStreamAlias("memname")
	private String memName;
	
	/**
	 * 挂牌方式
	 */
	@XStreamAlias("listedtype")
	private String listedType;
	
	/**
	 * 挂牌方式名称
	 */
	private String listedTypeName;
	
	/**
	 * 仓单编号
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
	 * 单位
	 */
	private String uom;
	
	/**
	 * 品类代码
	 */
	@XStreamAlias("classcode")
	private String classCode;
	
	/**
	 * 品类名称
	 */
	private String className;
	
	/**
	 * 市场代码
	 */
	@XStreamAlias("markcode")
	private String markCode;
	
	/**
	 * 市场名称
	 */
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
	private int qty;
	
	/**
	 * 整单标识
	 */
	@XStreamAlias("wholeflg")
	private String wholeFlg;
	
	/**
	 * 起订量
	 */
	@XStreamAlias("moq")
	private int moq;
	
	/**
	 * 递增量
	 */
	@XStreamAlias("ic")
	private int ic;
	
	/**
	 * 剩余数量
	 */
	@XStreamAlias("rem")
	private int rem;
	
	/**
	 * 挂牌日期
	 */
	@XStreamAlias("dol")
	private String dol;
	
	/**
	 * 挂牌有效期
	 */
	@XStreamAlias("doe")
	private String doe;
	
	/**
	 * 发运方式
	 */
	@XStreamAlias("tos")
	private String tos;
	
	/**
	 * 交收日期
	 */
	@XStreamAlias("delidate")
	private String deliDate;
	
	/**
	 * 交货仓库
	 */
	@XStreamAlias("storage")
	private String storage;
	
	/**
	 * 最后付款日
	 */
	@XStreamAlias("lastpd")
	private String lastPD;
	
	/**
	 * 平台监管发票
	 */
	@XStreamAlias("invoice")
	private String invoice;

	/**
	 * 指定摘牌方
	 */
	@XStreamAlias("delist")
	private String delist;
	
	/**
	 * 
	 */
	@XStreamImplicit
	private List<DelistMem> delistMems;
	
	@XStreamAlias("delistmem")
	public static class DelistMem{
		
		/**
		 * 摘牌方会员编号
		 */
		@XStreamAlias("delistmid")
		private String delistMID;
		
		/**
		 * 摘牌方会员名称
		 */
		@XStreamAlias("delistmemname")
		private String delistMemName;

		public String getDelistMID() {
			return delistMID;
		}

		public void setDelistMID(String delistMID) {
			this.delistMID = delistMID;
		}

		public String getDelistMemName() {
			return delistMemName;
		}

		public void setDelistMemName(String delistMemName) {
			this.delistMemName = delistMemName;
		}
	}
	
	/**
	 * 商品标题
	 */
	@XStreamAlias("title")
	private String title;
	
	/**
	 * 标题配图
	 */
	@XStreamAlias("titlepic")
	private String titlePic;
	
	/**
	 * 商品描述
	 */
	@XStreamAlias("detail")
	private String detail;
	
	/**
	 * 内容图片1
	 */
	@XStreamAlias("ctxpic1")
	private String ctxPic1;
	
	/**
	 * 内容图片2
	 */
	@XStreamAlias("ctxpic2")
	private String ctxPic2;
	
	/**
	 * 内容图片3
	 */
	@XStreamAlias("ctxpic3")
	private String ctxPic3;
	
	/**
	 * 扩展图片标志
	 */
	@XStreamAlias("ctxpicex")
	private String ctxPicEx;
	
	/**
	 * 挂牌状态
	 */
	@XStreamAlias("status")
	private int status;
	
	/**
	 * 记录生效标志
	 */
	@XStreamAlias("effrec")
	private String effRec;
	
	/**
	 * 属性
	 */
	@XStreamImplicit
	private List<Prop> props;
	
	/**
	 * 卖家联系方式—非循环域
	 */
	@XStreamImplicit
	private List<LinkInfo> linkinfos;
	
	@XStreamAlias("linkinfo")
	public static class LinkInfo{
		
		public String getEname() {
			return ename;
		}

		public void setEname(String ename) {
			this.ename = ename;
		}

		public String getLegPer() {
			return legPer;
		}

		public void setLegPer(String legPer) {
			this.legPer = legPer;
		}

		public String getLinkTel() {
			return linkTel;
		}

		public void setLinkTel(String linkTel) {
			this.linkTel = linkTel;
		}

		public String getFax() {
			return fax;
		}

		public void setFax(String fax) {
			this.fax = fax;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getRegAddr() {
			return regAddr;
		}

		public void setRegAddr(String regAddr) {
			this.regAddr = regAddr;
		}

		public String getPc() {
			return pc;
		}

		public void setPc(String pc) {
			this.pc = pc;
		}

		/**
		 * 企业名称
		 */
		@XStreamAlias("ename")
		private String ename;
		
		/**
		 * 法定代表人
		 */
		@XStreamAlias("legpre")
		private String legPer;
		
		/**
		 * 联系电话
		 */
		@XStreamAlias("linktel")
		private String linkTel;
		
		/**
		 * 传真号码
		 */
		@XStreamAlias("fax")
		private String fax;
		
		/**
		 * 企业网址
		 */
		@XStreamAlias("url")
		private String url;
		
		/**
		 * E-mail
		 */
		@XStreamAlias("email")
		private String email;
		
		/**
		 * 注册住所
		 */
		@XStreamAlias("regaddr")
		private String regAddr;
		
		/**
		 * 邮政编码
		 */
		@XStreamAlias("pc")
		private String pc;
		
	}

	public String getListedNo() {
		return listedNo;
	}

	public void setListedNo(String listedNo) {
		this.listedNo = listedNo;
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getWholeFlg() {
		return wholeFlg;
	}

	public void setWholeFlg(String wholeFlg) {
		this.wholeFlg = wholeFlg;
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

	public String getDol() {
		return dol;
	}

	public void setDol(String dol) {
		this.dol = dol;
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

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public List<DelistMem> getDelistMems() {
		return delistMems;
	}

	public void setDelistMems(List<DelistMem> delistMems) {
		this.delistMems = delistMems;
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
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

	public String getCtxPicEx() {
		return ctxPicEx;
	}

	public void setCtxPicEx(String ctxPicEx) {
		this.ctxPicEx = ctxPicEx;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getEffRec() {
		return effRec;
	}

	public void setEffRec(String effRec) {
		this.effRec = effRec;
	}

	public List<Prop> getProps() {
		return props;
	}

	public void setProps(List<Prop> props) {
		this.props = props;
	}





	public List<LinkInfo> getLinkinfos() {
		return linkinfos;
	}

	public void setLinkinfos(List<LinkInfo> linkinfos) {
		this.linkinfos = linkinfos;
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

	public String getDelist() {
		return delist;
	}

	public void setDelist(String delist) {
		this.delist = delist;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}
	


}
