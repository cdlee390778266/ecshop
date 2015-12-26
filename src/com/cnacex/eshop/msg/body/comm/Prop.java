package com.cnacex.eshop.msg.body.comm;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("props")
public class Prop {
	
	@XStreamAlias("propidx")
	private String	propIdx;
	
	private String	propName;
	
	@XStreamAlias("propval")
	private String	propVal;
	
	private String consType;
	
	private String consVal;
	
	private String valType;
	
	private String valFmt;
	
	private String forceInput;
	
	private String viewIdx;

	public String getPropIdx() {
		return propIdx;
	}

	public void setPropIdx(String propIdx) {
		this.propIdx = propIdx;
	}

	public String getPropVal() {
		return propVal;
	}

	public void setPropVal(String propVal) {
		this.propVal = propVal;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
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

	public String getForceInput() {
		return forceInput;
	}

	public void setForceInput(String forceInput) {
		this.forceInput = forceInput;
	}

	
	
	public String getViewIdx() {
		return viewIdx;
	}

	public void setViewIdx(String viewIdx) {
		this.viewIdx = viewIdx;
	}

	@Override
	public String toString() {
		return "Prop [propIdx=" + propIdx + ", propName=" + propName
				+ ", propVal=" + propVal + ", consType=" + consType
				+ ", consVal=" + consVal + ", valType=" + valType + ", valFmt="
				+ valFmt + ", forceInput=" + forceInput + "]";
	}


	
	

}
