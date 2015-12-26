package com.cnacex.eshop.msg.body.trade.sell;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.CostPay;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */
public class BondPayRsp {
	
	@XStreamAlias("listedno")
	private String listedNo;
	
	@XStreamAlias("status")
	private int	status;
	
	
	private String statusDesc;
	
	
	@XStreamImplicit
	private List<CostPay> costPays;
	
	public String getListedNo() {
		return listedNo;
	}

	public void setListedNo(String listedNo) {
		this.listedNo = listedNo;
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
