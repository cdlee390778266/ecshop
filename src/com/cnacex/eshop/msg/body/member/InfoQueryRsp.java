package com.cnacex.eshop.msg.body.member;
import com.thoughtworks.xstream.annotations.XStreamAlias;


public class InfoQueryRsp {
	@XStreamAlias("mid")
	private String mID;
	
	
	@XStreamAlias("operid")
	private String operID;
	
	
	@XStreamAlias("opertype")
	private char operType;
	
	
	@XStreamAlias("opername")
	private String operName;
	
	@XStreamAlias("opersex")
	private char operSex;

	
	@XStreamAlias("operphoto")
	private String operPhoto;
	
	
	@XStreamAlias("memtype")
	private String memType;
	
	@XStreamAlias("memname")
	private String memName;

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getOperID() {
		return operID;
	}

	public void setOperID(String operID) {
		this.operID = operID;
	}

	public char getOperType() {
		return operType;
	}

	public void setOperType(char operType) {
		this.operType = operType;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public char getOperSex() {
		return operSex;
	}

	public void setOperSex(char operSex) {
		this.operSex = operSex;
	}

	public String getOperPhoto() {
		return operPhoto;
	}

	public void setOperPhoto(String operPhoto) {
		this.operPhoto = operPhoto;
	}

	public String getMemType() {
		return memType;
	}

	public void setMemType(String memType) {
		this.memType = memType;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}
}
