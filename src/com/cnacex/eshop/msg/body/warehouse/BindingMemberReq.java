package com.cnacex.eshop.msg.body.warehouse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 关联会员关联关系查询请求
 * @author frog
 *
 */
public class BindingMemberReq {

	/**
	 * 会员编号
	 */
	@XStreamAlias("mid")
	private String mid;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
	
	@Override
	public String toString() {
		return String.format("BindingMemberReq [mid=%s]", mid);
	}
}
