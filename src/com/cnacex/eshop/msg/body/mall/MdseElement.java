package com.cnacex.eshop.msg.body.mall;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.CommProp;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@XStreamAlias("mdselist")
public class MdseElement {
	
	@XStreamAlias("mdsecode")
	private String mdseCode;
	
	@XStreamAlias("mdsename")
	private String mdseName;
	
	@XStreamAlias("classflg")
	private String classFlg;
	
	@XStreamAlias("pmdsecode")
	private String pmdseCode;
	
	@XStreamAlias("classcode")
	private String classCode;
	
	@XStreamAlias("markcode")
	private String markCode;
	
	@XStreamAlias("uom")
	private String  uom;
	
	@XStreamAlias("active")
	private int  active;
	
	private boolean haveLeaf;

	public String getMdseCode() {
		return mdseCode;
	}

	public void setMdseCode(String mdseCode) {
		this.mdseCode = mdseCode;
	}

	public String getMdseName() {
		return mdseName;
	}

	public void setMdseName(String mdseName) {
		this.mdseName = mdseName;
	}

	public String getClassFlg() {
		return classFlg;
	}

	public void setClassFlg(String classFlg) {
		this.classFlg = classFlg;
	}

	public String getPmdseCode() {
		return pmdseCode;
	}

	public void setPmdseCode(String pmdseCode) {
		this.pmdseCode = pmdseCode;
	}

	public String getMarkCode() {
		return markCode;
	}

	public void setMarkCode(String markCode) {
		this.markCode = markCode;
	}

	
	
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

//	@XStreamImplicit
//	private List<ClassProp> classProps;
	
	@XStreamImplicit
	private List<CommProp> commProps;
	

	public List<CommProp> getCommProps() {
		return commProps;
	}

	public void setCommProps(List<CommProp> commProps) {
		this.commProps = commProps;
	}

	public boolean isHaveLeaf() {
		return haveLeaf;
	}

	public void setHaveLeaf(boolean haveLeaf) {
		this.haveLeaf = haveLeaf;
	}
	
	

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "MdseElement [mdseCode=" + mdseCode + ", mdseName=" + mdseName
				+ ", classFlg=" + classFlg + ", pmdseCode=" + pmdseCode
				+ ", classCode=" + classCode + ", markCode=" + markCode
				+ ", uom=" + uom + ", haveLeaf=" + haveLeaf + ", commProps="
				+ commProps + "]";
	}
	

}
