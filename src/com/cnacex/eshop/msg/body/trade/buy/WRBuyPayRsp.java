package com.cnacex.eshop.msg.body.trade.buy;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.CostPay;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 仓单摘牌付款响应
 * @author frog
 *
 */
public class WRBuyPayRsp {

	/**
	 * 摘牌编号
	 */
	@XStreamAlias("dsNo")
	private String dsNo;
	
	/**
	 * 摘牌状态
	 */
	@XStreamAlias("status")
	private int status;
	
	private String statusDesc;
	
	@XStreamImplicit
	private List<CostPay> costPays;

	public String getDsNo() {
		return dsNo;
	}

	public void setDsNo(String dsNo) {
		this.dsNo = dsNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public List<CostPay> getCostPays() {
		return costPays;
	}

	public void setCostPays(List<CostPay> costPays) {
		this.costPays = costPays;
	}
}
