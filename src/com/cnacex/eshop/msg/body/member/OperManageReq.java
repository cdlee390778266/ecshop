package com.cnacex.eshop.msg.body.member;

import java.util.List;

import com.cnacex.eshop.msg.body.comm.OperRight;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class OperManageReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	
	@XStreamAlias("adminid")
	private String adminID;
	
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("settype")
	private char setTpye;
	
	@XStreamAlias("opername")
	private String operName;
	
	@XStreamAlias("opersex")
	private char operSex;
	
	@XStreamAlias("operphoto")
	private String operPhoto;
	
	
	@XStreamAlias("resetpwd")
	private String resetPwd;


	@XStreamImplicit
	private List<OperRight> operRights;



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



	public char getSetTpye() {
		return setTpye;
	}



	public void setSetTpye(char setTpye) {
		this.setTpye = setTpye;
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



	public String getResetPwd() {
		return resetPwd;
	}



	public void setResetPwd(String resetPwd) {
		this.resetPwd = resetPwd;
	}



	public List<OperRight> getOperRights() {
		return operRights;
	}



	public void setOperRights(List<OperRight> operRights) {
		this.operRights = operRights;
	}
	

	
	
}
