package com.cnacex.eshop.msg.body.mall;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.Prop;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class ListedDetailRsp {
	
	@XStreamAlias("listedno")
	private String listedNo;
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("memname")
	private String memName;
	
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
	private String up;
	
	@XStreamAlias("fpflg")
	private String fpFlg;
	
	@XStreamAlias("qty")
	private int qty;
	
	@XStreamAlias("wholeflg")
	private String wholeFlg;
	
	@XStreamAlias("moq")
	private int moq;
	
	@XStreamAlias("ic")
	private int ic;
	
	@XStreamAlias("rem")
	private int rem;
	
	@XStreamAlias("dol")
	private String dol;
	
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
	
	@XStreamAlias("invoice")
	private String invoice;

	@XStreamAlias("delist")
	private String delist;
	
	
	@XStreamImplicit
	private List<DelistMem> delistMems;
	
	@XStreamAlias("delistmem")
	public static class DelistMem{
		
		@XStreamAlias("delistmid")
		private String delistMID;
		
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
	
	@XStreamAlias("title")
	private String title;
	
	@XStreamAlias("titlepic")
	private String titlePic;
	
	@XStreamAlias("detail")
	private String detail;
	
	@XStreamAlias("ctxpic1")
	private String ctxPic1;
	
	@XStreamAlias("ctxpic2")
	private String ctxPic2;
	
	@XStreamAlias("ctxpic3")
	private String ctxPic3;
	
	@XStreamAlias("ctxpicex")
	private String ctxPicEx;
	
	@XStreamAlias("status")
	private int status;
	
	@XStreamAlias("effrec")
	private String effRec;
	
	@XStreamImplicit
	private List<Prop> props;
	
	
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

		@XStreamAlias("ename")
		private String ename;
		
		@XStreamAlias("legpre")
		private String legPer;
		
		@XStreamAlias("linktel")
		private String linkTel;
		
		@XStreamAlias("fax")
		private String fax;
		
		@XStreamAlias("url")
		private String url;
		
		@XStreamAlias("email")
		private String email;
		
		@XStreamAlias("regaddr")
		private String regAddr;
		
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
