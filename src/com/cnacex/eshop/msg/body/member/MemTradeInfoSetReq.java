package com.cnacex.eshop.msg.body.member;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author 孙超
 *	会员基本信息设置请求报文类
 */
public class MemTradeInfoSetReq {
	
	@XStreamAlias("mid")
	private String mid;
	
	
	@XStreamAlias("adminid")
	private String adminid;
	
	
	@XStreamAlias("linkname")
	private String linkname;
	
	@XStreamAlias("linksex")
	private char linksex;
	
	
	@XStreamAlias("linktel")
	private String linktel;
	
	
	
	@XStreamAlias("linkemail")
	private String linkemail;
	
	
	@XStreamAlias("linkeaddr")
	private String linkaddr;
	
	@XStreamAlias("linkpost")
	private String linkpost;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getLinkname() {
		return linkname;
	}

	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}

	public char getLinksex() {
		return linksex;
	}

	public void setLinksex(char linksex) {
		this.linksex = linksex;
	}

	public String getLinktel() {
		return linktel;
	}

	public void setLinktel(String linktel) {
		this.linktel = linktel;
	}

	public String getLinkemail() {
		return linkemail;
	}

	public void setLinkemail(String linkemail) {
		this.linkemail = linkemail;
	}

	public String getLinkaddr() {
		return linkaddr;
	}

	public void setLinkaddr(String linkaddr) {
		this.linkaddr = linkaddr;
	}

	public String getLinkpost() {
		return linkpost;
	}

	public void setLinkpost(String linkpost) {
		this.linkpost = linkpost;
	}

	
}
