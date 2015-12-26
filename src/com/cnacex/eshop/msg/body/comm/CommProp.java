package com.cnacex.eshop.msg.body.comm;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("commprops")
public class CommProp {
	
	@XStreamAlias("propidx")
	private String propIdx;
	
	@XStreamAlias("propname")
	private String propName;
	
	@XStreamAlias("valtype")
	private String valType;
	
	@XStreamAlias("valfmt")
	private String valFmt;
	
	@XStreamAlias("constype")
	private String consType;
	
	@XStreamAlias("consval")
	private String consVal;
	
	@XStreamAlias("viewidx")
	private String viewIdx;
	
	@XStreamAlias("sumidx")
	private String sumIdx;

	@XStreamAlias("condidx")
	private String condIdx;
	
	@XStreamAlias("condtype")
	private String condType;
	
	@XStreamAlias("condenum")
	private String condEnum;
	
	
	@XStreamAlias("forceinput")
	private String forceInput;
	
	public String getPropIdx() {
		return propIdx;
	}

	public void setPropIdx(String propIdx) {
		this.propIdx = propIdx;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getValType() {
		return valType;
	}

	public void setValType(String valType) {
		this.valType = valType;
	}

	public String getValFmt() {
		return valFmt;
	}

	public void setValFmt(String valFmt) {
		this.valFmt = valFmt;
	}

	public String getConsType() {
		return consType;
	}

	public void setConsType(String consType) {
		this.consType = consType;
	}

	public String getConsVal() {
		return consVal;
	}

	public void setConsVal(String consVal) {
		this.consVal = consVal;
	}

	public String getViewIdx() {
		return viewIdx;
	}

	public void setViewIdx(String viewIdx) {
		this.viewIdx = viewIdx;
	}

	public String getSumIdx() {
		return sumIdx;
	}

	public void setSumIdx(String sumIdx) {
		this.sumIdx = sumIdx;
	}

	public String getCondIdx() {
		return condIdx;
	}

	public void setCondIdx(String condIdx) {
		this.condIdx = condIdx;
	}

	public String getCondType() {
		return condType;
	}

	public void setCondType(String condType) {
		this.condType = condType;
	}

	public String getCondEnum() {
		return condEnum;
	}

	public void setCondEnum(String condEnum) {
		this.condEnum = condEnum;
	}

	public String getForceInput() {
		return forceInput;
	}

	public void setForceInput(String forceInput) {
		this.forceInput = forceInput;
	}

	@Override
	public String toString() {
		return "CommProp [propIdx=" + propIdx + ", propName=" + propName
				+ ", valType=" + valType + ", valFmt=" + valFmt + ", consType="
				+ consType + ", consVal=" + consVal + ", viewIdx=" + viewIdx
				+ ", sumIdx=" + sumIdx + ", condIdx=" + condIdx + ", condType="
				+ condType + ", condEnum=" + condEnum + ", forceInput="
				+ forceInput + "]";
	}

	
	
	
}
