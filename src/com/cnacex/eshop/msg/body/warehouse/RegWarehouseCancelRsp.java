package com.cnacex.eshop.msg.body.warehouse;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 待审核的注册仓单查询应答结构体（交易码60005012）
 * @author 文闻
 *
 */
public class RegWarehouseCancelRsp {

	//下次请求位置
	@XStreamAlias("nextstart")
	private String nextstart;

	//总记录数
	@XStreamAlias("totalnum")
	private Integer totalnum;
	
	//本次返回数量
	@XStreamAlias("curnum")
	private Integer curnum;
	
	//会员编号
	@XStreamAlias("mid")
	private String mid;
	
	//会员名称
	@XStreamAlias("memname")
	private String memname;
	
	@XStreamImplicit(itemFieldName="receipts")
	private List<CancelReceipts> rseceipts;

	public String getNextstart() {
		return nextstart;
	}

	public void setNextstart(String nextstart) {
		this.nextstart = nextstart;
	}

	public Integer getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}

	public Integer getCurnum() {
		return curnum;
	}

	public void setCurnum(Integer curnum) {
		this.curnum = curnum;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMemname() {
		return memname;
	}

	public void setMemname(String memname) {
		this.memname = memname;
	}

	public List<CancelReceipts> getRseceipts() {
		return rseceipts;
	}

	public void setRseceipts(List<CancelReceipts> rseceipts) {
		this.rseceipts = rseceipts;
	}
}
