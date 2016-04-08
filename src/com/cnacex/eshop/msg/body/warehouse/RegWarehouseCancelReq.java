package com.cnacex.eshop.msg.body.warehouse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 待审核的注册仓单查询结构体（交易码60005012）
 * @author 文闻
 *
 */
public class RegWarehouseCancelReq {

	//请求起始位置
	@XStreamAlias("reqstart")
	private Long reqStart;
	
	//请求条数
	@XStreamAlias("reqnum")
	private Long reqNum;
	
	//会员编号
	@XStreamAlias("mid")
	private String mid;
	
	//审核编码
	@XStreamAlias("auditno")
	private Long auditno;
	
	//仓库编号
	@XStreamAlias("storeno")
	private String storeno;
	
	//仓位编号
	@XStreamAlias("position")
	private Long position;
	
	//商品代码
	@XStreamAlias("commcode")
	private String commcode;
	
	//起始入库时间
	@XStreamAlias("sdate")
	private String sdate;
	
	//结束入库时间
	@XStreamAlias("edate")
	private String edate;
	
	//仓单状态
	@XStreamAlias("status")
	private Long status;

	public Long getReqStart() {
		return reqStart;
	}

	public void setReqStart(Long reqStart) {
		this.reqStart = reqStart;
	}

	public Long getReqNum() {
		return reqNum;
	}

	public void setReqNum(Long reqNum) {
		this.reqNum = reqNum;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getStoreno() {
		return storeno;
	}

	public void setStoreno(String storeno) {
		this.storeno = storeno;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public String getCommcode() {
		return commcode;
	}

	public void setCommcode(String commcode) {
		this.commcode = commcode;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getAuditno() {
		return auditno;
	}

	public void setAuditno(Long auditno) {
		this.auditno = auditno;
	}
}
