package com.cnacex.eshop.msg.body.trade.buy;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.CostPay;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author kereny
 *
 */
public class BuyPayRsp {
	
	
	@XStreamAlias("dsno")
	private String dsNO;
	
	
	@XStreamAlias("status")
	private int	status;

	
	private String statusDesc;
	

	@XStreamImplicit
	private List<CostPay> costPays;


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getDsNO() {
		return dsNO;
	}


	public void setDsNO(String dsNO) {
		this.dsNO = dsNO;
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
