package com.cnacex.eshop.msg.body.comm;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("classprops")
public class ClassProp {

	@XStreamAlias("propidx")
	private String propIdx;

	@XStreamAlias("propname")
	private String propName;

	@XStreamAlias("valtype")
	private String valType;

	@XStreamAlias("valfmt")
	private String valFmt;

	@XStreamAlias("condidx")
	private String condIdx;

	@XStreamAlias("condtype")
	private String condType;

	@XStreamAlias("condenum")
	private String condEnum;

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

}
