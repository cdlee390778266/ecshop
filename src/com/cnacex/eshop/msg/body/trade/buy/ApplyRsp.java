package com.cnacex.eshop.msg.body.trade.buy;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.CostPay;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class ApplyRsp {
	
	/**
	 * 摘牌编号
	 */
	@XStreamAlias("delistno")
	private String delistNo;
	
	/**
	 * 摘牌状态
	 */
	@XStreamAlias("status")
	private int	status;
	
	private String statusDesc;
	
	/**
	 * 费用
	 */
	@XStreamImplicit
	private List<CostPay> costPays;
	
	public String getDelistNo() {
		return delistNo;
	}

	public void setDelistNo(String delistNo) {
		this.delistNo = delistNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<CostPay> getCostPays() {
		return costPays;
	}

	public void setCostPays(List<CostPay> costPays) {
		this.costPays = costPays;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	
	


}
