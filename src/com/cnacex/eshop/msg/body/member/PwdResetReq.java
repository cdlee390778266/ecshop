package com.cnacex.eshop.msg.body.member;
import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * @author 孙超
 *操作员密码重置请求报文类
 */
public class PwdResetReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	@XStreamAlias("adminid")
	private String adminID;
	
	@XStreamAlias("operid")
	private String operID;

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getOperID() {
		return operID;
	}

	public void setOperID(String operID) {
		this.operID = operID;
	}
}
