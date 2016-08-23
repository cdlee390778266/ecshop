package com.cnacex.eshop.msg.body.warehouse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 关联会员关联关系查询响应
 * @author frog
 *
 */
public class BindingMemberRsp {

	/**
	 * 仓库会员账号
	 */
	@XStreamAlias("provid")
	private String provId;

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}
}
