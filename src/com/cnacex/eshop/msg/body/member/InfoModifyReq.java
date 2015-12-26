package com.cnacex.eshop.msg.body.member;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author 孙超
 *	操作员资料变更
 */
public class InfoModifyReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("opername")
	private String operName;
	
	@XStreamAlias("opersex")
	private char operSex;
	
	@XStreamAlias("operphoto")
	private String operPhoto;
	


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
}
