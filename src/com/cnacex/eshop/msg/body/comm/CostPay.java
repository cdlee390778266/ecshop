package com.cnacex.eshop.msg.body.comm;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Administrator
 *
 */
@XStreamAlias("costpay")
public class CostPay {
	
	/**
	 * 费用标识
	 */
	@XStreamAlias("flag")
	private int flag;
	
	private String flagDesc;
	
	/**
	 * 交易类型
	 */
	@XStreamAlias("trtype")
	private String trType;
	
	
	private String trTypeName;
	
	/**
	 * 费用代码
	 */
	@XStreamAlias("costcode")
	private String costCode;
	
	/**
	 * 费用金额
	 */
	@XStreamAlias("costamt")
	private double costAmt;
	
	private String costName;
	
	public String getCostCode() {
		return costCode;
	}

	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	public double getCostAmt() {
		return costAmt;
	}

	public void setCostAmt(double costAmt) {
		this.costAmt = costAmt;
	}

	public String getCostName() {
		return costName;
	}

	public void setCostName(String costName) {
		this.costName = costName;
	}

	public String getTrType() {
		return trType;
	}

	public void setTrType(String trType) {
		this.trType = trType;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getFlagDesc() {
		return flagDesc;
	}

	public void setFlagDesc(String flagDesc) {
		this.flagDesc = flagDesc;
	}

	public String getTrTypeName() {
		return trTypeName;
	}

	public void setTrTypeName(String trTypeName) {
		this.trTypeName = trTypeName;
	}



}
