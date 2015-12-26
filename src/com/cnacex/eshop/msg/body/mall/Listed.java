package com.cnacex.eshop.msg.body.mall;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.Prop;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */

public class Listed {
	
	@XStreamAlias("listedno")
	private String listedNo;
	
	@XStreamAlias("listedtype")
	private String listedType;
	
	private String listedTypeName;

	@XStreamAlias("mid")
	private String mid;
	
	@XStreamAlias("memname")
	private String memName;
	
	@XStreamAlias("commcode")
	private String commCode;
	
	private String commName;
	
	private String clsCode;

	private String clsName;
	
	
	@XStreamAlias("title")
	private String title;
	
	@XStreamAlias("titlepic")
	private String titlePic;
	
	@XStreamAlias("up")
	private double up;
	
	private String uom;
	
	@XStreamAlias("qty")
	private int qty;
	
	@XStreamAlias("rem")
	private int rem;
	
	@XStreamAlias("storage")
	private String storage;
	
	@XStreamAlias("doe")
	private String doe;
	
	@XStreamAlias("dol")
	private String dol;
	
	@XStreamAlias("summary1")
	private String summary1;
	
	@XStreamAlias("summary2")
	private String summary2;
	
	@XStreamAlias("summary3")
	private String summary3;
	
	@XStreamAlias("summary4")
	private String summary4;

	@XStreamAlias("status")
	private int	status;
	
	private String statusDesc;
	
	@XStreamAlias("effrec")
	private int effRec;
	
	private String effRecDesc;
	
	//审核意见
	@XStreamAlias("auditcomment")
	private String auditComment;
	
	@XStreamImplicit
	private List<Prop> props;
	
	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getClsCode() {
		return clsCode;
	}


	public void setClsCode(String clsCode) {
		this.clsCode = clsCode;
	}


	public String getClsName() {
		return clsName;
	}


	public void setClsName(String clsName) {
		this.clsName = clsName;
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


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}


	public String getCommCode() {
		return commCode;
	}


	public void setCommCode(String commCode) {
		this.commCode = commCode;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public double getUp() {
		return up;
	}


	public void setUp(double up) {
		this.up = up;
	}

	

	public String getUom() {
		return uom;
	}


	public void setUom(String uom) {
		this.uom = uom;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public int getRem() {
		return rem;
	}


	public void setRem(int rem) {
		this.rem = rem;
	}


	public String getStorage() {
		return storage;
	}


	public void setStorage(String storage) {
		this.storage = storage;
	}


	public String getDoe() {
		return doe;
	}


	public void setDoe(String doe) {
		this.doe = doe;
	}

	

	public String getDol() {
		return dol;
	}

	public void setDol(String dol) {
		this.dol = dol;
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


	public String getCommName() {
		return commName;
	}


	public void setCommName(String commName) {
		this.commName = commName;
	}


	public String getTitlePic() {
		return titlePic;
	}


	public void setTitlePic(String titlePic) {
		this.titlePic = titlePic;
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

	public String getEffRecDesc() {
		return effRecDesc;
	}

	public void setEffRecDesc(String effRecDesc) {
		this.effRecDesc = effRecDesc;
	}

	public String getMemName() {
		return memName;
	}


	public void setMemName(String memName) {
		this.memName = memName;
	}
	
	public String getAuditComment() {
		return auditComment;
	}

	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
	}

	@Override
	public String toString() {
		return "Listed [listedNo=" + listedNo + ", listedType=" + listedType
				+ ", listedTypeName=" + listedTypeName + ", mid=" + mid
				+ ", memName=" + memName + ", commCode=" + commCode
				+ ", commName=" + commName + ", clsCode=" + clsCode
				+ ", clsName=" + clsName + ", title=" + title + ", titlePic="
				+ titlePic + ", up=" + up + ", uom=" + uom + ", qty=" + qty
				+ ", rem=" + rem + ", storage=" + storage + ", doe=" + doe
				+ ", dol=" + dol + ", summary1=" + summary1 + ", summary2="
				+ summary2 + ", summary3=" + summary3 + ", summary4="
				+ summary4 + ", status=" + status + ", statusDesc="
				+ statusDesc + ", effRec=" + effRec + ", effRecDesc="
				+ effRecDesc + ", props=" + props + "]";
	}
	
}
