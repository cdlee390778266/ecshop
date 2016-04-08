package com.cnacex.eshop.msg.body.trade.sell;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 注册仓单清单查询请求结构体
 * @author 文闻
 *
 */

public class ApplyCdReq {
	
	//请求起始位置(第几条)
	@XStreamAlias("reqStart")
	private Long reqStart;

	//请求条数（分页条数）
	@XStreamAlias("reqNum")
	private Long reqNum;
	
	//会员编号
	@XStreamAlias("mid")
	private String mid;
	
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
	
	//注册清单状态
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



	@Override
	public String toString() {
		return "ApplyCdReq [reqStart=" + reqStart + ", reqNum=" + reqNum
				+ ", mid=" + mid + ", storeno=" + storeno + ", position=" + position
				+ ", commcode=" + commcode + ", sdate=" + sdate +", edate=" + edate + "]";
	}

	
	

}
