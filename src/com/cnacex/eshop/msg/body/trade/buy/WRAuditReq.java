package com.cnacex.eshop.msg.body.trade.buy;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单摘牌审核请求
 * @author frog
 *
 */
public class WRAuditReq {

	/**
	 * 会员编号
	 */
	@XStreamAlias("mid")
	private String mid;
	
	/**
	 * 审核操作员
	 */
	@XStreamAlias("auoperid")
	private String auoperId;
	
	/**
	 * 审核编号
	 */
	@XStreamAlias("auditno")
	private String auditNo;
	
	/**
	 * 审核结果
	 */
	@XStreamAlias("auditret")
	private String auditRet;
	
	/**
	 * 审核意见
	 */
	@XStreamAlias("aduitremark")
	private String aduitRemark;
	
	/**
	 * 
	 */
	private String delistNo;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getAuoperId() {
		return auoperId;
	}

	public void setAuoperId(String auoperId) {
		this.auoperId = auoperId;
	}

	public String getAuditNo() {
		return auditNo;
	}

	public void setAuditNo(String auditNo) {
		this.auditNo = auditNo;
	}

	public String getAuditRet() {
		return auditRet;
	}

	public void setAuditRet(String auditRet) {
		this.auditRet = auditRet;
	}

	public String getAduitRemark() {
		return aduitRemark;
	}

	public void setAduitRemark(String aduitRemark) {
		this.aduitRemark = aduitRemark;
	}
	
	public String getDelistNo() {
		return delistNo;
	}

	public void setDelistNo(String delistNo) {
		this.delistNo = delistNo;
	}

	@Override
	public String toString() {
		return String.format("WRAuditReq [mid=%s, auoperId=%s, auditNo=%s, auditRet=%s, aduitRemark=%s]", 
				mid, auoperId, auditNo, auditRet, aduitRemark);
	}
}
