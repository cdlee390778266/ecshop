package com.cnacex.eshop.msg.body.comm;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("operright")
public class OperRight {
	
	@Override
	public String toString() {
		return "OperRight [rightType=" + rightType + ", classCode=" + classCode
				+ ", clsName=" + clsName;
	}

	@XStreamAlias("righttype")
	private  String rightType;
	
	@XStreamAlias("classcode")
	private String classCode;
	
	private String clsName;
	

	
	
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClsName() {
		return clsName;
	}

	public void setClsName(String clsName) {
		this.clsName = clsName;
	}

	public String getRightType() {
		return rightType;
	}

	public void setRightType(String rightType) {
		this.rightType = rightType;
	}



	
	

}
