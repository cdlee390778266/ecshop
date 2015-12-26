package com.cnacex.eshop.msg.body.member;

import java.util.List;


import com.cnacex.eshop.msg.body.comm.OperRight;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author 孙超
 *操作员权限设置表
 */
public class RightsSetReq {
	
	@XStreamAlias("mid")
	private String mID;
	
	
	@XStreamAlias("adminid")
	private String adminID;
	
	
	@XStreamAlias("operid")
	private String operID;
	
	@XStreamAlias("setType")
	private char setTpye;
	
	@XStreamAlias("operName")
	private String operName;
	
	@XStreamAlias("operSex")
	private char operSex;
	
	@XStreamAlias("operPhoto")
	private String operPhoto;
	
	
	@XStreamAlias("resetPwd")
	private String resetPwd;



	@XStreamImplicit
	private List<OperRight> operRights;
	

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

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public List<OperRight> getOperRights() {
		return operRights;
	}
	
	public void setOperRights(List<OperRight> operRights) {
		this.operRights = operRights;
	}


	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}


	public char getSetTpye() {
		return setTpye;
	}

	public void setSetTpye(char setTpye) {
		this.setTpye = setTpye;
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
}
