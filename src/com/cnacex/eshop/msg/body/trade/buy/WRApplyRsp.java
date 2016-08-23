package com.cnacex.eshop.msg.body.trade.buy;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.CostPay;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 仓单摘牌申请响应结果
 * @author frog
 *
 */
public class WRApplyRsp {

	/**
	 * 摘牌编号
	 */
	@XStreamAlias("delistno")
	private String delistNo;
	
	/**
	 * 摘牌审核编号
	 */
	@XStreamAlias("auditno")
	private String auditNo;
	
	/**
	 * 摘牌状态
	 */
	@XStreamAlias("status")
	private int status;
	
	/**
	 * 摘牌状态（报文未返回，页面需要）
	 */
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

	public String getAuditNo() {
		return auditNo;
	}

	public void setAuditNo(String auditNo) {
		this.auditNo = auditNo;
	}

	public List<CostPay> getCostPays() {
		return costPays;
	}

	public void setCostPays(List<CostPay> costPays) {
		this.costPays = costPays;
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
}
