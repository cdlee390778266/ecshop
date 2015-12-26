package com.cnacex.eshop.msg.body.trade.buy;


import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kereny
 *
 */

public class Delist {
	
	@XStreamAlias("delistno")
	private String delistNo;
	
	@XStreamAlias("listedtype")
	private String listedType;
	
	private String listedTypeName;
	
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
	
	@XStreamAlias("vol")
	private int vol;
	
	@XStreamAlias("storage")
	private String storage;
	
	@XStreamAlias("doe")
	private String doe;
	
	@XStreamAlias("dod")
	private String dod;
	
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
	
	//审核意见
	@XStreamAlias("auditcomment")
	private String auditComment;
	
	@XStreamAlias("effrec")
	private int effRec;
	
	private String effRecDesc;
	
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


	public String getDelistNo() {
		return delistNo;
	}

	public void setDelistNo(String delistNo) {
		this.delistNo = delistNo;
	}

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}

	public String getDod() {
		return dod;
	}

	public void setDod(String dod) {
		this.dod = dod;
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

	public String getAuditComment() {
		return auditComment;
	}

	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
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

}
