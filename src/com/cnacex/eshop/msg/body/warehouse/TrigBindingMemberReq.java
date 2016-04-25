package com.cnacex.eshop.msg.body.warehouse;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 仓单关联会员请求
 * @author frog
 *
 */
public class TrigBindingMemberReq {

	/**
	 * 会员编号
	 */
	@XStreamAlias("mid")
	private String mid;
	
	/**
	 * 仓库会员账号
	 */
	@XStreamAlias("provid")
	private String provId;
	
	/**
	 * 仓库会员密码
	 */
	@XStreamAlias("passwd")
	private String passwd;
	
	/**
	 * 关联标志
	 */
	@XStreamAlias("flag")
	private long flag;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public long getFlag() {
		return flag;
	}

	public void setFlag(long flag) {
		this.flag = flag;
	}
}
