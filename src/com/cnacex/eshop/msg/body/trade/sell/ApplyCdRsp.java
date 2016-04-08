package com.cnacex.eshop.msg.body.trade.sell;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.CostPay;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 注册仓单清单查询应答结构体
 * @author 文闻
 *
 */

public class ApplyCdRsp {
	
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
	@XStreamAlias("provid")
	private String provid;
	
	//供货商名称
	@XStreamAlias("memname")
	private String memname;
	
	@XStreamImplicit(itemFieldName="receipts")
	private List<Receipts> rseceipts;

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

	public List<Receipts> getrseceipts() {
		return rseceipts;
	}

	public void setrseceipts(List<Receipts> rseceipts) {
		this.rseceipts = rseceipts;
	}

	public String getProvid() {
		return provid;
	}

	public void setProvid(String provid) {
		this.provid = provid;
	}

	public String getMemname() {
		return memname;
	}

	public void setMemname(String memname) {
		this.memname = memname;
	}
	
}
